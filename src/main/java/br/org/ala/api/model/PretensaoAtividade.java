package br.org.ala.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @Column(name = "area_palestra")
    private String areaPalestra;

    @OneToOne
    @ToString.Exclude
    @JsonBackReference
    @JoinColumn(name = "pessoa_fisica_id")
    private Pessoa pessoa;

}
