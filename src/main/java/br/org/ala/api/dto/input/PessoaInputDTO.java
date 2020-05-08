package br.org.ala.api.dto.input;

import br.org.ala.api.dto.AtividadeDTO;
import br.org.ala.api.dto.EnderecoDTO;
import br.org.ala.api.dto.MensalidadeDTO;
import br.org.ala.api.dto.PretensaoAtividadeDTO;
import br.org.ala.api.model.PessoaTipo;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInputDTO {

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
    private String rgUf;

    @NotNull
    private String cpf;

    @NotNull
    private Boolean ativo;

    private List<EnderecoDTO> enderecos;

    private MensalidadeDTO mensalidade;
    private AtividadeDTO atividade;
    private PretensaoAtividadeDTO pretensaoAtividade;
    private BigDecimal pretensaoMensalidadeValor;

}
