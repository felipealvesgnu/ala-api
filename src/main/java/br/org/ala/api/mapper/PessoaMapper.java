package br.org.ala.api.mapper;

import br.org.ala.api.dto.CidadeDTO;
import br.org.ala.api.dto.input.PessoaInputDTO;
import br.org.ala.api.dto.PessoaDTO;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.model.Pessoa;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    @Autowired
    ModelMapper modelMapper;

    public PessoaDTO convertToDto(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public Pessoa convertToEntity(PessoaDTO pessoaDTO) {
        return modelMapper.map(pessoaDTO, Pessoa.class);
    }

    public Pessoa convertToEntity(PessoaInputDTO pessoaInputDTO) {
        return modelMapper.map(pessoaInputDTO, Pessoa.class);
    }

    @PostConstruct
    public void addMapping() {
        modelMapper.typeMap(Pessoa.class, PessoaDTO.class)
                .addMapping(src -> src.getRg().getNumero(), PessoaDTO::setRg);

        modelMapper.typeMap(Cidade.class, CidadeDTO.class)
                .addMapping(src -> src.getEstado().getUf(), CidadeDTO::setUf);
    }
}
