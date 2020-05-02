package br.org.ala.api.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "pessoa_fisica")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PessoaFisica {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String telefone;

    private String profissao;

    @NotNull
    @Column(name = "tipo_pessoa")
    @Enumerated(value = EnumType.STRING)
    private PessoaTipo tipo;

    @Embedded
    private Rg rg;

    private String cpf;

    @NotNull
    private Boolean ativo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "pessoa_fisica_endereco",
            joinColumns = @JoinColumn(name = "pessoa_fisica_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<Endereco> enderecos;

    @OneToOne(mappedBy = "pessoaFisica", cascade = CascadeType.ALL)
    private Atividade atividade;

    @OneToOne(mappedBy = "pessoaFisica", cascade = CascadeType.ALL)
    private Mensalidade mensalidade;

    @OneToOne(mappedBy = "pessoaFisica", cascade = CascadeType.ALL)
    private PretensaoAtividade pretensaoAtividade;

    @OneToOne(mappedBy = "pessoaFisica", cascade = CascadeType.ALL)
    private PretensaoMensalidade pretensaoMensalidade;

    //wiring mapped side
    public void setAtividade(Atividade atividade){
        this.atividade = atividade;
        atividade.setPessoaFisica(this);
    }

    //Wiring mapped side
    public void setPretensaoMensalidade(PretensaoMensalidade pretensaoMensalidade) {
        this.pretensaoMensalidade = pretensaoMensalidade;
        pretensaoMensalidade.setPessoaFisica(this);
    }

    //Wiring mapped side
    public void setPretensaoAtividade(PretensaoAtividade pretensaoAtividade){
        this.pretensaoAtividade = pretensaoAtividade;
        pretensaoAtividade.setPessoaFisica(this);
    }

    //Wiring mapped side
    public void setMensalidade(Mensalidade mensalidade){
        this.mensalidade = mensalidade;
        mensalidade.setPessoaFisica(this);
    }
}
