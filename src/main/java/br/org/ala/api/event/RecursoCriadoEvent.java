package br.org.ala.api.event;

import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {

    @Getter
    private HttpServletResponse response;

    @Getter
    private Long id;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

}
