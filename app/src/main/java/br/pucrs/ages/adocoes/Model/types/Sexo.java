package br.pucrs.ages.adocoes.Model.types;

import java.io.Serializable;

/**
 * Created by kuquert on 03/06/17.
 */

public enum Sexo implements Serializable {

    Masculino("Masculino"),
    Feminino("Feminino");

    private String value;

    Sexo(String value) {
        this.value = value;
    }
}
