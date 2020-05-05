package br.org.ala.api.model.jpa;

import br.org.ala.api.AlaApiApplication;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.repository.CidadeRepository;
import br.org.ala.api.repository.EstadoRepository;
import br.org.ala.api.repository.PessoaRepository;
import java.util.Optional;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultasMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlaApiApplication.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);

        PessoaRepository pessoaRepository = applicationContext.getBean(PessoaRepository.class);

        Optional<Pessoa> byId = pessoaRepository.findById(94L);

        Pessoa pessoa = byId.get();

        System.out.println(pessoa.getNome());

    }
}
