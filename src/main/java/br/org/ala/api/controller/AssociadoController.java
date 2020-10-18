package br.org.ala.api.controller;

import br.org.ala.api.config.security.Check;
import br.org.ala.api.dto.AssociadoDTO;
import br.org.ala.api.dto.input.AssociadoInputDTO;
import br.org.ala.api.event.RecursoCriadoEvent;
import br.org.ala.api.mapper.AssociadoMapper;
import br.org.ala.api.model.Associado;
import br.org.ala.api.service.AssociadoService;
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
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private AssociadoMapper associadoMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @Check.Associados.Pesquisar
    public Page<AssociadoDTO> listar(@PageableDefault(size = 15) Pageable pageable) {
        Page<Associado> associadosPage = associadoService.listar(pageable);
        List<AssociadoDTO> associadosDTO = associadoMapper.convertToDto(associadosPage.getContent());

        return new PageImpl<>(associadosDTO, pageable, associadosPage.getTotalElements());
    }

    @GetMapping(params = "incluirInativos")
    @Check.Associados.Pesquisar
    public Iterable<Associado> listar(@RequestParam(required = false) Boolean incluirInativos) {
        if (incluirInativos){
            return associadoService.listarInativos();
        }
        return associadoService.listarAtivos();
    }

    @GetMapping(params = "nome")
    @Check.Associados.Pesquisar
    public ResponseEntity<Page<AssociadoDTO>> listarPorNome(@RequestParam String nome, Pageable pageable) {
        Page<Associado> associadosPage = associadoService.listarPorNome(nome, pageable);
        Page<AssociadoDTO> associadosPageDTO = associadosPage.map(associado -> associadoMapper.convertToDto(associado));

        return associadosPageDTO.getContent().isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(associadosPageDTO);
    }

    @GetMapping("/{id}")
    @Check.Associados.Pesquisar
    public ResponseEntity<AssociadoDTO> buscar(@PathVariable Long id) {
        Associado associado = associadoService.buscarPeloId(id);
        AssociadoDTO associadoDTO = associadoMapper.convertToDto(associado);

        return ResponseEntity.ok(associadoDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Check.Associados.Cadastrar
    public Associado adicionar(@Valid @RequestBody AssociadoInputDTO associadoInputDTO, HttpServletResponse response) {
        Associado associado = associadoMapper.convertToEntity(associadoInputDTO);
        Associado associadoSalvo = associadoService.salvar(associado);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, associadoSalvo.getId()));
        return associadoSalvo;
    }

    @PutMapping("/{id}")
    @Check.Associados.Cadastrar
    public ResponseEntity<AssociadoDTO> atualizar(@PathVariable Long id,
                                                  @Valid @RequestBody AssociadoInputDTO associadoInputDTO) {
        Associado associadoSalvo = associadoService.buscarPeloId(id);
        associadoSalvo.getEnderecos().clear();
        associadoMapper.convertToEntity(associadoInputDTO, associadoSalvo);
        associadoService.salvar(associadoSalvo);
        AssociadoDTO associadoDTO = associadoMapper.convertToDto(associadoSalvo);

        return ResponseEntity.ok(associadoDTO);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Check.Associados.Cadastrar
    public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
        associadoService.atualizarPropriedadeAtivo(id, ativo);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Check.Associados.Remover
    public void remover(@PathVariable Long id) {
        associadoService.excluir(id);
    }
}