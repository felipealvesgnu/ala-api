//package br.org.ala.api.resource;
//
//import br.org.ala.api.event.RecursoCriadoEvent;
//import br.org.ala.api.model.Pessoa;
//import br.org.ala.api.repository.PessoaFisicaRepository;
//import br.org.ala.api.service.PessoaService;
//import java.util.Optional;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/pessoas")
//public class PessoaResource {
//
//    @Autowired
//    private PessoaFisicaRepository pessoaRepository;
//
//    @Autowired
//    private PessoaService pessoaService;
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
//    @GetMapping
//    public Iterable<Pessoa> listar() {
//        return pessoaRepository.findAll();
//    }
//
//    @PostMapping
//    public ResponseEntity<Pessoa> criar(@Valid
//                                        @RequestBody Pessoa pessoa, HttpServletResponse response) {
//        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
//        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Pessoa>> buscarId(@PathVariable Long id) {
//        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
//        return pessoa.isPresent() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long id){
//        pessoaRepository.deleteById(id);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa){
//        Pessoa pessoaSalva = pessoaService.atualizar(id, pessoa);
//
//        return ResponseEntity.ok(pessoaSalva);
//    }
//
//    @PutMapping("/{id}/ativo")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo){
//        pessoaService.atualizarPropriedadeAtivo(id, ativo);
//    }
//}
