package br.org.ala.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtividadeDTO {
    private Long id;
    private String descricao;
    private Boolean palestrante;
    private String areaPalestra;
}
