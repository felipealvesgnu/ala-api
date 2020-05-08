package br.org.ala.api.dto;

import br.org.ala.api.model.PessoaTipo;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nome;
    private String email;
    private String telefone;
    private String profissao;
    private PessoaTipo tipo;
    private String rg;
    private String rgOrgEmissor;
    private String rgUf;
    private String cpf;
    private Boolean ativo;

    private List<EnderecoDTO> enderecos;
    private MensalidadeDTO mensalidade;
    private AtividadeDTO atividade;
    private PretensaoAtividadeDTO pretensaoAtividade;
    private Long pretensaoMensalidadeId;
    private BigDecimal pretensaoMensalidadeValor;
}
