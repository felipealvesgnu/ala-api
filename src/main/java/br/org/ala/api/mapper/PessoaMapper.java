package br.org.ala.api.mapper;

import br.org.ala.api.dto.CidadeDTO;
import br.org.ala.api.dto.PessoaDTO;
import br.org.ala.api.dto.input.PessoaInputDTO;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.model.Pessoa;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    @Autowired
    ModelMapper modelMapper;

    public PessoaDTO convertToDto(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public Pessoa convertToEntity(PessoaInputDTO pessoaInputDTO) {
        return modelMapper.map(pessoaInputDTO, Pessoa.class);
    }

    public void convertToEntity(PessoaInputDTO pessoaInputDTO, Pessoa pessoaSalva) {
        modelMapper.map(pessoaInputDTO, pessoaSalva);
    }

    public List<PessoaDTO> convertToDto(List<Pessoa> pessoas) {
        Type pessoasDTOtype = new TypeToken<List<PessoaDTO>>() {}.getType();

        return modelMapper.map(pessoas, pessoasDTOtype);
    }

    @PostConstruct
    public void addMapping() {
        modelMapper.typeMap(Pessoa.class, PessoaDTO.class)
                .addMapping(src -> src.getRg().getNumero(), PessoaDTO::setRg);
        modelMapper.typeMap(Cidade.class, CidadeDTO.class)
                .addMapping(src -> src.getEstado().getUf(), CidadeDTO::setUf);

        modelMapper.typeMap(PessoaInputDTO.class, Pessoa.class)
                .addMapping(PessoaInputDTO::getRg, (Pessoa destination, String value) -> destination.getRg().setNumero(value));

        modelMapper.typeMap(PessoaInputDTO.class, Pessoa.class)
                .addMapping(src -> src, (Pessoa destination, Pessoa value) -> destination.getAtividade().setPessoa(value));
        modelMapper.typeMap(PessoaInputDTO.class, Pessoa.class)
                .addMapping(src -> src, (Pessoa destination, Pessoa value) -> destination.getPretensaoAtividade().setPessoa(value));
    }

}
