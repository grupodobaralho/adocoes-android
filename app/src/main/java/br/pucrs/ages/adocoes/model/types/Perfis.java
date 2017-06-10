package br.pucrs.ages.adocoes.model.types;

/**
 * Created by kuquert on 03/06/17.
 */

public enum Perfis {

    USUARIO("usuario"),
    MASTER("master"),
    ADMINISTRADOR("administrador"),
    INTERESSADO("interessado");

    private String value;

    Perfis(String value) {
        this.value = value;
    }
}