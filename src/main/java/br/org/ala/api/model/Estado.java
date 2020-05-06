package br.org.ala.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Estado {

    @Id
    private Integer id;
    private String nome;
    private String uf;

}
