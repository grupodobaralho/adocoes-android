package br.pucrs.ages.adocoes.Model.types;

/**
 * Created by kuquert on 03/06/17.
 */

public enum TipoDocumento {

    RG("rg"),
    CNH("cnh");

    private String value;

    TipoDocumento(String value) {
        this.value = value;
    }
}
