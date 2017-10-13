package br.pucrs.ages.adocoes.Model;

import java.io.Serializable;

/**
 * Created by matheusvaccaro on 05/10/17.
 */

public class MenorMidia implements Serializable {

    private String midia;
    private String type;

    public MenorMidia(String midia, String type) {
        this.midia = midia;
        this.type = type;
    }

    public String getMidia() {
        return this.midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
