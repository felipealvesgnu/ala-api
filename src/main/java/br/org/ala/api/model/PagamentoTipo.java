package br.org.ala.api.model;

public enum PagamentoTipo {

    CREDITO(1), BOLETO(2), DEPOSITO(3), CONSIGNADO(4);

    private Integer value;

    PagamentoTipo(Integer valor) {
        this.value = valor;
    }

    public Integer getValue() {
        return value;
    }
}
