package br.org.ala.api.model.jpa;

import br.org.ala.api.AlaApiApplication;
import br.org.ala.api.model.Atividade;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.model.Endereco;
import br.org.ala.api.model.Estado;
import br.org.ala.api.model.Mensalidade;
import br.org.ala.api.model.PagamentoTipo;
import br.org.ala.api.model.PessoaFisica;
import br.org.ala.api.model.PessoaTipo;
import br.org.ala.api.model.PretensaoAtividade;
import br.org.ala.api.model.PretensaoMensalidade;
import br.org.ala.api.model.Rg;
import br.org.ala.api.repository.CidadeRepository;
import br.org.ala.api.repository.EstadoRepository;
import br.org.ala.api.repository.PessoaFisicaRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class PessoaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlaApiApplication.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);

        PessoaFisicaRepository pessoaFisicaRepository = applicationContext.getBean(PessoaFisicaRepository.class);
        CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
        cadatrar(pessoaFisicaRepository, cidadeRepository, estadoRepository);
//        deletar(pessoaFisicaRepository, 67L);
//        deletar(pessoaFisicaRepository, 74L);

    }

    private static void cadatrar(PessoaFisicaRepository pessoaFisicaRepository,
                                 CidadeRepository cidadeRepository,
                                 EstadoRepository estadoRepository) {


        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setNome("Felipe");
        pessoa.setEmail("felipealves.gnu@gmail.com");
        pessoa.setTelefone("242342423");
        pessoa.setProfissao("Developer");
        pessoa.setCpf("33854304889");

        Estado estado = estadoRepository.findById(26).get();
        Rg rg = new Rg();
        rg.setNumero("13241341");
        rg.setOrgEmissor("SSP");
        rg.setEstado(estado);
        pessoa.setRg(rg);
        pessoa.setTipo(PessoaTipo.FUNDADOR);
        pessoa.setAtivo(true);

        Cidade cidade = cidadeRepository.findById(470L).get();

        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Av Dr. Joaquim da Silva");
        endereco1.setNumero("101");
        endereco1.setComplemento("Cond. Marbelo");
        endereco1.setBairro("Itararé");
        endereco1.setCep("11320-400");
        endereco1.setCidade(cidade);

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Av Dr. Joaquim da Silva 2");
        endereco2.setNumero("102");
        endereco2.setComplemento("Cond. Marbelo 2");
        endereco2.setBairro("Itararé 2");
        endereco2.setCep("11320-402");
        endereco2.setCidade(cidade);

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco1);
        enderecos.add(endereco2);
        pessoa.setEnderecos(enderecos);

        PretensaoMensalidade pretensaoMensalidade = new PretensaoMensalidade();
        pretensaoMensalidade.setValor(new BigDecimal(150));
        pessoa.setPretensaoMensalidade(pretensaoMensalidade);

        PretensaoAtividade pretensaoAtividade = new PretensaoAtividade();
        pretensaoAtividade.setDescricao("Educação Financeira");
        pretensaoAtividade.setInteressePalestrar(true);
        pretensaoAtividade.setPalestraArea("Finanças, Gestão do Tempo");
        pessoa.setPretensaoAtividade(pretensaoAtividade);

        Atividade atividade = new Atividade();
        atividade.setDescricao("Trabalhos administrativos na sede");
        atividade.setPalestrante(true);
        atividade.setAreaPalestra("Gestão de Pessoas");
        pessoa.setAtividade(atividade);

        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setTipoPagamento(PagamentoTipo.CREDITO);
        mensalidade.setValor(new BigDecimal(300));
        pessoa.setMensalidade(mensalidade);

        pessoaFisicaRepository.save(pessoa);

    }


    private static void deletar(PessoaFisicaRepository pessoaFisicaRepository, Long id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
