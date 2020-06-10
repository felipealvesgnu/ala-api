package br.org.ala.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO {
    private Integer id;
    private String nome;
    private String uf;
}
