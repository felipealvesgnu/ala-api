package br.org.ala.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
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
@Table(name = "pessoa_fisica")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Associado extends Pessoa {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToOne(mappedBy = "associado", cascade = CascadeType.ALL)
    private Atividade atividade;

    @JsonManagedReference
    @OneToOne(mappedBy = "associado", cascade = CascadeType.ALL)
    private PretensaoAtividade pretensaoAtividade;

    @JsonManagedReference
    @OneToOne(mappedBy = "associado", cascade = CascadeType.ALL)
    private Mensalidade mensalidade;

    @JsonManagedReference
    @OneToOne(mappedBy = "associado", cascade = CascadeType.ALL)
    private PretensaoMensalidade pretensaoMensalidade;

    //wiring mapped side
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
        atividade.setAssociado(this);
    }

    //Wiring mapped side
    public void setPretensaoAtividade(PretensaoAtividade pretensaoAtividade) {
        this.pretensaoAtividade = pretensaoAtividade;
        pretensaoAtividade.setAssociado(this);
    }

    //Wiring mapped side
    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
        mensalidade.setAssociado(this);
    }

    //Wiring mapped side
    public void setPretensaoMensalidade(PretensaoMensalidade pretensaoMensalidade) {
        this.pretensaoMensalidade = pretensaoMensalidade;
        pretensaoMensalidade.setAssociado(this);
    }
}
