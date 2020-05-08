package br.org.ala.api.model;

import br.org.ala.api.model.converter.PagamentoTipoConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "mensalidade")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mensalidade {

    @Id
//    @JsonIgnore
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @Column(name = "tipo_pagamento_id")
    @Convert(converter = PagamentoTipoConverter.class)
    private PagamentoTipo tipoPagamento;

    @OneToOne
    @ToString.Exclude
    @JsonBackReference
    @JoinColumn(name = "pessoa_fisica_id")
    private Pessoa pessoa;

}
