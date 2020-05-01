package br.org.ala.api.model.jpa;

import br.org.ala.api.AlaApiApplication;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.model.Endereco;
import br.org.ala.api.model.Estado;
import br.org.ala.api.model.PessoaFisica;
import br.org.ala.api.model.PessoaTipo;
import br.org.ala.api.model.PretensaoContribuicao;
import br.org.ala.api.model.Rg;
import br.org.ala.api.repository.CidadeRepository;
import br.org.ala.api.repository.EstadoRepository;
import br.org.ala.api.repository.PessoaFisicaRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
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
//        deletar(pessoaFisicaRepository, 18L);

    }

    private static void cadatrar(PessoaFisicaRepository pessoaFisicaRepository,
                                 CidadeRepository cidadeRepository,
                                 EstadoRepository estadoRepository) {

        Optional<Estado> optionalEstado = estadoRepository.findById(26);
        Estado estado = optionalEstado.get();

        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setNome("Felipe");
        pessoa.setEmail("felipealves.gnu@gmail.com");
        pessoa.setTelefone("242342423");
        pessoa.setProfissao("Developer");
        pessoa.setCpf("33854304889");

        Rg rg = new Rg();
        rg.setNumero("13241341");
        rg.setOrgEmissor("SSP");
        rg.setEstado(estado);
        pessoa.setRg(rg);

        pessoa.setTipo(PessoaTipo.FUNDADOR);
        pessoa.setAtivo(true);

        Optional<Cidade> optionalCidade = cidadeRepository.findById(470L);
        Cidade cidade = optionalCidade.get();

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Av Dr. Joaquim da Silva");
        endereco.setNumero("101");
        endereco.setComplemento("Cond. Marbelo");
        endereco.setBairro("Itararé");
        endereco.setCep("11320-400");
        endereco.setCidade(cidade);

        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);
        pessoa.setEnderecos(enderecos);

        PretensaoContribuicao pretensaoContribuicao = new PretensaoContribuicao();
        pretensaoContribuicao.setValor(new BigDecimal(150));

        pessoa.setPretensaoContribuicao(pretensaoContribuicao);


        pessoaFisicaRepository.save(pessoa);

    }


    private static void deletar(PessoaFisicaRepository pessoaFisicaRepository, Long id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
