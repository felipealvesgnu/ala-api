package br.org.ala.api.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PretensaoMensalidadeDTO {
    private Long id;
    private BigDecimal valor;
}
