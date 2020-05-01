//package br.org.ala.api.resource;
//
//import br.org.ala.api.event.RecursoCriadoEvent;
//import br.org.ala.api.model.Categoria;
//import br.org.ala.api.repository.CategoriaRepository;
//import java.util.Optional;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/categorias")
//public class CategoriaResource {
//
//    @Autowired
//    private CategoriaRepository categoriaRepository;
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
//    @GetMapping
//    public Iterable<Categoria> listar() {
//        return categoriaRepository.findAll();
//    }
//
//    @PostMapping
//    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria,
//                                           HttpServletResponse response) {
//        Categoria categoriaSalva = categoriaRepository.save(categoria);
//        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Categoria>> buscarId(@PathVariable Long id) {
//        Optional<Categoria> categoria = categoriaRepository.findById(id);
//        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
//    }
//}
