package br.pucrs.ages.adocoes.model.types;

/**
 * Created by kuquert on 03/06/17.
 */

public enum Sexo {

    Masculino("Maslino"),
    Feminino("Feminino");

    private String value;

    Sexo(String value) {
        this.value = value;
    }
}
