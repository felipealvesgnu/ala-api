package br.org.ala.api.controller;

import br.org.ala.api.dto.CidadeDTO;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.repository.CidadeRepository;
import br.org.ala.api.repository.EstadoRepository;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    private Iterable<CidadeDTO> listarPorEstado(@RequestParam Integer estadoId) {
        List<Cidade> cidades = cidadeRepository.findByEstadoId(estadoId);
        Type cidadesDTO = new TypeToken<List<CidadeDTO>>() {}.getType();

        return modelMapper.map(cidades, cidadesDTO);
    }

}
