package br.org.ala.api.model;

import java.math.BigDecimal;
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
@Table(name = "pretensao_mensalidade")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PretensaoMensalidade {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @OneToOne
    @ToString.Exclude
    private PessoaFisica pessoaFisica;

}
