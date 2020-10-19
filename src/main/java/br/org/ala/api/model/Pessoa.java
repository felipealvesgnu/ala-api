package br.org.ala.api.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
public abstract class Pessoa {

    private String nome;
    private String nomeConjuge;
    private String email;
    private String telefone;
    private String profissao;
    private String facebook;
    private String instagram;

    @NotNull
    @Column(name = "tipo_pessoa")
    @Enumerated(value = EnumType.STRING)
    private PessoaTipo tipo;

    @Embedded
    private Rg rg;

    private LocalDate dataNasc;
    private LocalDate dataNascConjuge;
    private String cpf;
    private Boolean ativo;

    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "pessoa_fisica_endereco",
            joinColumns = @JoinColumn(name = "pessoa_fisica_id")
    )
    private List<Endereco> enderecos;

}

