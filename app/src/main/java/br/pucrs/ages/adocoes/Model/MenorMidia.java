package br.pucrs.ages.adocoes.Model;

import java.io.Serializable;

/**
 * Created by matheusvaccaro on 05/10/17.
 */

public class MenorMidia implements Serializable {

    private String type;
    private String conteudo;
    private String descricao;

    public MenorMidia(String type, String descricao, String conteudo) {
        this.descricao = descricao;
        this.type = type;
        this.conteudo = conteudo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConteudo() { return this.conteudo; }

    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
}
