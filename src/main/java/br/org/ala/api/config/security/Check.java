package br.org.ala.api.config.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface Check {

    @interface Associados {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ASSOCIADO')")
        @interface Cadastrar {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("hasAuthority('ROLE_PESQUISAR_ASSOCIADO')")
        @interface Pesquisar {
        }

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("hasAuthority('ROLE_REMOVER_ASSOCIADO')")
        @interface Remover {
        }
    }
}
