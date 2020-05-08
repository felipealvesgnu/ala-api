package br.org.ala.api.controller;

import br.org.ala.api.dto.PessoaDTO;
import br.org.ala.api.dto.input.PessoaInputDTO;
import br.org.ala.api.event.RecursoCriadoEvent;
import br.org.ala.api.mapper.PessoaMapper;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.repository.PessoaRepository;
import br.org.ala.api.service.PessoaService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    public Iterable<Pessoa> listar() {
        return pessoaService.listar();
    }

    @GetMapping("/por-nome")
    public ResponseEntity<Optional<List<Pessoa>>> listar(@RequestParam String nome) {
        Optional<List<Pessoa>> optionalPessoas = pessoaService.listarPorNome(nome);

        return optionalPessoas.isPresent()
                ? ResponseEntity.ok(optionalPessoas)
                : ResponseEntity.notFound().build();
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
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id,
                                            @Valid @RequestBody PessoaInputDTO pessoaInputDTO) {
        pessoaService.buscarPeloId(id);
        Pessoa pessoa = pessoaMapper.convertToEntity(pessoaInputDTO);
        Pessoa pessoaSalva = pessoaService.atualizar(pessoa);

        return ResponseEntity.ok(pessoaSalva);
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