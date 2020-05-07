package br.org.ala.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "pessoa_fisica")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String profissao;

    @NotNull
    @Column(name = "tipo_pessoa")
    @Enumerated(value = EnumType.STRING)
    private PessoaTipo tipo;

    @Embedded
    private Rg rg;

    private String cpf;

    private Boolean ativo;

    @NotNull
    @ElementCollection
    @CollectionTable(
            name = "pessoa_fisica_endereco",
            joinColumns = @JoinColumn(name = "pessoa_fisica_id")
    )
    private List<Endereco> enderecos;

    @JsonManagedReference
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Atividade atividade;

    @JsonManagedReference
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Mensalidade mensalidade;

    @JsonManagedReference
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PretensaoAtividade pretensaoAtividade;

    @JsonManagedReference
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PretensaoMensalidade pretensaoMensalidade;

    //wiring mapped side
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
        atividade.setPessoa(this);
    }

    //Wiring mapped side
    public void setPretensaoMensalidade(PretensaoMensalidade pretensaoMensalidade) {
        this.pretensaoMensalidade = pretensaoMensalidade;
        pretensaoMensalidade.setPessoa(this);
    }

    //Wiring mapped side
    public void setPretensaoAtividade(PretensaoAtividade pretensaoAtividade) {
        this.pretensaoAtividade = pretensaoAtividade;
        pretensaoAtividade.setPessoa(this);
    }

    //Wiring mapped side
    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
        mensalidade.setPessoa(this);
    }
}

