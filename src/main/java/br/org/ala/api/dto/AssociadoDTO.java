package br.org.ala.api.dto;

import br.org.ala.api.model.PessoaTipo;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociadoDTO {

    private Long id;
    private String nome;
    private String nomeConjuge;
    private String email;
    private String telefone;
    private String profissao;
    private PessoaTipo tipo;
    private String rg;
    private String rgOrgEmissor;
    private String rgUf;
    private String dataNasc;
    private String dataNascConjuge;
    private String cpf;
    private String facebook;
    private String instagram;
    private Boolean ativo;

    private List<EnderecoDTO> enderecos;
    private AtividadeDTO atividade;
    private PretensaoAtividadeDTO pretensaoAtividade;
    private MensalidadeDTO mensalidade;
//    private Long pretensaoMensalidadeId;
//    private BigDecimal pretensaoMensalidadeValor;
    private PretensaoMensalidadeDTO pretensaoMensalidade;
}
