package br.org.ala.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "pretensao_atividade")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PretensaoAtividade {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Boolean interessePalestrar;

    private String palestraArea;

    @OneToOne
    @ToString.Exclude
    private PessoaFisica pessoaFisica;
}
