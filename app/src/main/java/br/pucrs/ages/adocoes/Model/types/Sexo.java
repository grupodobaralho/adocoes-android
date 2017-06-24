package br.pucrs.ages.adocoes.Model.types;

/**
 * Created by kuquert on 03/06/17.
 */

public enum Sexo {

    Masculino("Masculino"),
    Feminino("Feminino");

    private String value;

    Sexo(String value) {
        this.value = value;
    }
}
