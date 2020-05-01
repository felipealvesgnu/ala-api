package br.org.ala.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Embeddable
public class Rg {

    @Column(name = "rg")
    private String numero;

    @Column(name = "rg_org_emisor")
    private String orgEmissor;

    @OneToOne
    @JoinColumn(name = "rg_uf_id")
    private Estado estado;
}
