package br.org.ala.api.mapper;

import br.org.ala.api.dto.AtividadeDTO;
import br.org.ala.api.dto.EnderecoDTO;
import br.org.ala.api.dto.MensalidadeDTO;
import br.org.ala.api.dto.AssociadoDTO;
import br.org.ala.api.dto.PretensaoAtividadeDTO;
import br.org.ala.api.dto.PretensaoMensalidadeDTO;
import br.org.ala.api.dto.input.AssociadoInputDTO;
import br.org.ala.api.model.Associado;
import br.org.ala.api.model.Atividade;
import br.org.ala.api.model.Endereco;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.model.PretensaoAtividade;
import br.org.ala.api.model.PretensaoMensalidade;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssociadoMapper {

    @Autowired
    ModelMapper modelMapper;

    public AssociadoDTO convertToDto(Associado associado) {
        return modelMapper.map(associado, AssociadoDTO.class);
    }

    public Associado convertToEntity(AssociadoInputDTO associadoInputDTO) {
        return modelMapper.map(associadoInputDTO, Associado.class);
    }

    public void convertToEntity(AssociadoInputDTO associadoInputDTO, Associado associado) {
        modelMapper.map(associadoInputDTO, associado);
    }

    public List<AssociadoDTO> convertToDto(List<Associado> associados) {
        Type associadosDTOtype = new TypeToken<List<AssociadoDTO>>() {
        }.getType();
        return modelMapper.map(associados, associadosDTOtype);
    }

    @Autowired
    public void addMapping() {

        modelMapper.typeMap(Associado.class, AssociadoDTO.class).addMapping(associado -> associado.getRg().getNumero(), AssociadoDTO::setRg);

        modelMapper.typeMap(AtividadeDTO.class, Associado.class).addMappings(map -> map.skip(Associado::setId));
        modelMapper.typeMap(MensalidadeDTO.class, Associado.class).addMappings(map -> map.skip(Associado::setId));
        modelMapper.typeMap(PretensaoAtividadeDTO.class, Associado.class).addMappings(map -> map.skip(Associado::setId));
        modelMapper.typeMap(PretensaoMensalidadeDTO.class, Associado.class).addMappings(map -> map.skip(Associado::setId));

        modelMapper.typeMap(AtividadeDTO.class, Atividade.class).addMapping(AtividadeDTO::getId, Atividade::setId);
        modelMapper.typeMap(PretensaoAtividadeDTO.class, PretensaoAtividade.class).addMapping(PretensaoAtividadeDTO::getId, PretensaoAtividade::setId);
        modelMapper.typeMap(PretensaoMensalidadeDTO.class, PretensaoMensalidade.class).addMapping(PretensaoMensalidadeDTO::getId, PretensaoMensalidade::setId);

        modelMapper.typeMap(AssociadoInputDTO.class, Associado.class)
                .addMapping(AssociadoInputDTO::getRg, (Associado destination, String value) -> destination.getRg().setNumero(value))
                .addMapping(AssociadoInputDTO::getRgOrgEmissor, (Associado dest, String value) -> dest.getRg().setOrgEmissor(value));

        addConverters();

    }

    public void addConverters() {
        Converter<String, String> onlyNumbersConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().replaceAll("[\\s().-]", "");

        modelMapper.typeMap(AssociadoInputDTO.class, Associado.class)
                .addMappings(mapper -> mapper.using(onlyNumbersConverter).map(AssociadoInputDTO::getCpf, Associado::setCpf))
                .addMappings(mapper -> mapper.using(onlyNumbersConverter).map(AssociadoInputDTO::getTelefone, Associado::setTelefone));
        modelMapper.typeMap(EnderecoDTO.class, Endereco.class)
                .addMappings(mapper -> mapper.using(onlyNumbersConverter).map(EnderecoDTO::getCep, Endereco::setCep));
    }

}
