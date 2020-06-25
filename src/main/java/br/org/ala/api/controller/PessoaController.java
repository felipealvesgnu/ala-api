package br.org.ala.api.controller;

import br.org.ala.api.dto.PessoaDTO;
import br.org.ala.api.dto.input.PessoaInputDTO;
import br.org.ala.api.event.RecursoCriadoEvent;
import br.org.ala.api.mapper.PessoaMapper;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.service.PessoaService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaMapper pessoaMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<PessoaDTO> listar(@PageableDefault(size = 15) Pageable pageable) {
        Page<Pessoa> pessoasPage = pessoaService.listar(pageable);
        List<PessoaDTO> pessoasDTO = pessoaMapper.convertToDto(pessoasPage.getContent());

        return new PageImpl<>(pessoasDTO, pageable, pessoasPage.getTotalElements());
    }

    @GetMapping(params = "incluirInativos")
    public Iterable<Pessoa> listar(@RequestParam(required = false) Boolean incluirInativos) {
        if (incluirInativos){
            return pessoaService.listarInativos();
        }
        return pessoaService.listarAtivos();
    }

    @GetMapping(params = "nome")
    public ResponseEntity<Page<PessoaDTO>> listarPorNome(@RequestParam String nome, Pageable pageable) {
        Page<Pessoa> pessoasPage = pessoaService.listarPorNome(nome, pageable);
        Page<PessoaDTO> pessoasPageDTO = pessoasPage.map(pessoa -> pessoaMapper.convertToDto(pessoa));

        return pessoasPageDTO.getContent().isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pessoasPageDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscar(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPeloId(id);
        PessoaDTO pessoaDTO = pessoaMapper.convertToDto(pessoa);

        return ResponseEntity.ok(pessoaDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa adicionar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO, HttpServletResponse response) {
        Pessoa pessoa = pessoaMapper.convertToEntity(pessoaInputDTO);
        Pessoa pessoaSalva = pessoaService.salvar(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return pessoaSalva;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long id,
                                               @Valid @RequestBody PessoaInputDTO pessoaInputDTO) {
        Pessoa pessoaSalva = pessoaService.buscarPeloId(id);
        pessoaSalva.getEnderecos().clear();
        pessoaMapper.convertToEntity(pessoaInputDTO, pessoaSalva);
        pessoaService.salvar(pessoaSalva);
        PessoaDTO pessoaDTO = pessoaMapper.convertToDto(pessoaSalva);

        return ResponseEntity.ok(pessoaDTO);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
        pessoaService.atualizarPropriedadeAtivo(id, ativo);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        pessoaService.excluir(id);
    }
}