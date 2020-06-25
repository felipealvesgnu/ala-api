package br.org.ala.api.mapper;

import br.org.ala.api.dto.AtividadeDTO;
import br.org.ala.api.dto.MensalidadeDTO;
import br.org.ala.api.dto.PessoaDTO;
import br.org.ala.api.dto.PretensaoAtividadeDTO;
import br.org.ala.api.dto.PretensaoMensalidadeDTO;
import br.org.ala.api.dto.input.PessoaInputDTO;
import br.org.ala.api.model.Atividade;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.model.PretensaoAtividade;
import br.org.ala.api.model.PretensaoMensalidade;
import br.org.ala.api.model.Rg;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
        Type pessoasDTOtype = new TypeToken<List<PessoaDTO>>() {
        }.getType();

        return modelMapper.map(pessoas, pessoasDTOtype);
    }

    @Autowired
    public void addMapping() {
        modelMapper.typeMap(Pessoa.class, PessoaDTO.class).addMapping(pessoa -> pessoa.getRg().getNumero(), PessoaDTO::setRg);
//        modelMapper.typeMap(Pessoa.class, PessoaDTO.class).addMapping(pessoa -> pessoa.getRg().getOrgEmissor(), PessoaDTO::setRgOrgEmissor);

        modelMapper.typeMap(PretensaoAtividadeDTO.class, Pessoa.class).addMappings(map -> map.skip(Pessoa::setId));
        modelMapper.typeMap(PretensaoMensalidadeDTO.class, Pessoa.class).addMappings(map -> map.skip(Pessoa::setId));
        modelMapper.typeMap(AtividadeDTO.class, Pessoa.class).addMappings(map -> map.skip(Pessoa::setId));
        modelMapper.typeMap(MensalidadeDTO.class, Pessoa.class).addMappings(map -> map.skip(Pessoa::setId));

        modelMapper.typeMap(PretensaoAtividadeDTO.class, PretensaoAtividade.class).addMapping(PretensaoAtividadeDTO::getId, PretensaoAtividade::setId);
        modelMapper.typeMap(PretensaoMensalidadeDTO.class, PretensaoMensalidade.class).addMapping(PretensaoMensalidadeDTO::getId, PretensaoMensalidade::setId);


        modelMapper.typeMap(PessoaInputDTO.class, Pessoa.class)
                .addMapping(PessoaInputDTO::getRg, (Pessoa destination, String value) -> destination.getRg().setNumero(value))
                .addMapping(PessoaInputDTO::getRgOrgEmissor, (Pessoa dest, String value) -> dest.getRg().setOrgEmissor(value));
    }

}
