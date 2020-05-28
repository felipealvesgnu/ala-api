package br.org.ala.api.controller;

import br.org.ala.api.model.Estado;
import br.org.ala.api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public Iterable<Estado> listar(){
        return estadoRepository.findAll();
    }
}
