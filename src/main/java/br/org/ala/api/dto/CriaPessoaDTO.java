package br.org.ala.api.dto;

import br.org.ala.api.model.Atividade;
import br.org.ala.api.model.Mensalidade;
import br.org.ala.api.model.PessoaTipo;
import br.org.ala.api.model.PretensaoAtividade;
import br.org.ala.api.model.PretensaoMensalidade;
import br.org.ala.api.model.Rg;
import java.util.List;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriaPessoaDTO {

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String telefone;

    private String profissao;

    @NotNull
    private PessoaTipo tipo;

    private String rg;
    private String rgOrgEmissor;
    private Integer rgEstado;

    @NotNull
    private String cpf;

    @NotNull
    private Boolean ativo;

    private List<EnderecoDTO> enderecos;

    private Atividade atividade;

    private Mensalidade mensalidade;

    private PretensaoAtividade pretensaoAtividade;

    private PretensaoMensalidade pretensaoMensalidade;

}
