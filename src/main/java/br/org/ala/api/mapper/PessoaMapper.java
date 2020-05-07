package br.org.ala.api.mapper;

import br.org.ala.api.dto.CriaPessoaDTO;
import br.org.ala.api.dto.PessoaDTO;
import br.org.ala.api.model.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    @Autowired
    ModelMapper modelMapper;

    public PessoaDTO convertToDto(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public Pessoa convertToEntity(CriaPessoaDTO criaPessoaDTO){
        return modelMapper.map(criaPessoaDTO, Pessoa.class);
    }

    public Pessoa convertToEntity(PessoaDTO pessoaDTO){
        return modelMapper.map(pessoaDTO, Pessoa.class);
    }
}
