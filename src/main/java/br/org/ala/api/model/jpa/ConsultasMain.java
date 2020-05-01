package br.org.ala.api.model.jpa;

import br.org.ala.api.AlaApiApplication;
import br.org.ala.api.model.Cidade;
import br.org.ala.api.repository.CidadeRepository;
import br.org.ala.api.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultasMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlaApiApplication.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);

        CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

        Iterable<Cidade> cidades = cidadeRepository.findAll();

        for (Cidade cidade : cidades) {
            System.out.println(cidade);
        }
    }
}
