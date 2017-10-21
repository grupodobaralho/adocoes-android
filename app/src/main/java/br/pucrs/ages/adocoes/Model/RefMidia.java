package br.pucrs.ages.adocoes.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by matheusvaccaro on 13/10/17.
 */

public class RefMidia implements Serializable {

    @SerializedName("_id")
    private String id;
    private String type;
    private String descricao;
    private boolean principal;

    public RefMidia() {
    }

    public RefMidia(String id, String type, String descricao, boolean principal) {
        this.id = id;
        this.type = type;
        this.descricao = descricao;
        this.principal = principal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
