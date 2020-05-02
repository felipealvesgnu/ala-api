package br.org.ala.api.model;

public enum PagamentoTipo {

    DEBITO(1), CREDITO(2), CONSIGNADO(3);

    private Integer value;

    PagamentoTipo(Integer valor) {
        this.value = valor;
    }

    public Integer getValue() {
        return value;
    }
}
