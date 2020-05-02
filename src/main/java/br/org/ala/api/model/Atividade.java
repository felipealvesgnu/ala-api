package br.org.ala.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "atividade")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atividade {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Boolean palestrante;

    @Column(name = "area_palestra")
    private String areaPalestra;

    @OneToOne
    private PessoaFisica pessoaFisica;
}
