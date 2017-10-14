package br.pucrs.ages.adocoes.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by matheusvaccaro on 05/10/17.
 */

public class MenorMidia implements Serializable {

    @SerializedName("_id")
    private String id;
    private String conteudo;

    public MenorMidia() {

    }

    public MenorMidia(String id, String conteudo) {
        this.id = id;
        this.conteudo = conteudo;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConteudo() { return this.conteudo; }

    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
}
