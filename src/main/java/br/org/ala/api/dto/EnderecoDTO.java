package br.org.ala.api.dto;

import br.org.ala.api.model.EnderecoTipo;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private EnderecoTipo tipo;

    @NotNull
    private CidadeDTO cidade;

}
