package br.org.ala.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    private Long id;
    private String nome;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Estado estado;

}
