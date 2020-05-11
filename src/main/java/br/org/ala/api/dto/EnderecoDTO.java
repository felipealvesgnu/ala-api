package br.org.ala.api.dto;

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
    private String tipo;

    @NotNull
    private CidadeDTO cidade;

}
