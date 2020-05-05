package br.org.ala.api.event.listener;

import br.org.ala.api.event.RecursoCriadoEvent;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    /**
     * Adiciona URI Location ao response
     *
     */
    @Override
    public void onApplicationEvent(RecursoCriadoEvent event) {
        HttpServletResponse response = event.getResponse();
        Long id = event.getId();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
