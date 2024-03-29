package br.org.ala.api.exceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlaExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String msgUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

        return handleExceptionInternal(ex, new Erro(msgUsuario, msgDev), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
                                                                       WebRequest request) {
        String msgUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        String msgDev = ex.toString();

        return handleExceptionInternal(ex, new Erro(msgUsuario, msgDev), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bndResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bndResult.getFieldErrors()) {
            String msgUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Erro(msgUsuario, msgDesenvolvedor));
        }

        return erros;
    }

    public static class Erro {
        @Getter
        private String msgUsuario;

        @Getter
        private String msgDev;

        public Erro(String msgUsuario, String msgDev) {
            this.msgUsuario = msgUsuario;
            this.msgDev = msgDev;
        }
    }
}
