package br.org.ala.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PretensaoAtividadeDTO {
    private Long id;
    private String descricao;
    private Boolean interessePalestrar;
    private String areaPalestra;
}
