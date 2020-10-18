package br.org.ala.api.model.jpa;

import br.org.ala.api.AlaApiApplication;
import br.org.ala.api.model.Associado;
import br.org.ala.api.model.Pessoa;
import br.org.ala.api.repository.AssociadoRepository;
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

        AssociadoRepository associadoRepository = applicationContext.getBean(AssociadoRepository.class);

        Optional<Associado> byId = associadoRepository.findById(94L);

        Associado associado = byId.get();

        System.out.println(associado.getNome());

    }
}
