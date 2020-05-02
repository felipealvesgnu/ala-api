package br.org.ala.api.model.converter;

import br.org.ala.api.model.PagamentoTipo;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PagamentoTipoConverter implements AttributeConverter<PagamentoTipo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PagamentoTipo pagamentoTipo) {
        return pagamentoTipo.getValue();
    }

    @Override
    public PagamentoTipo convertToEntityAttribute(Integer dbValue) {
        for (PagamentoTipo tipo : PagamentoTipo.values()) {
            if (tipo.getValue().equals(dbValue)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Unknown database value:" + dbValue);
    }
}
