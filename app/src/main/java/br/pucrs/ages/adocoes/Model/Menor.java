package br.pucrs.ages.adocoes.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.pucrs.ages.adocoes.Model.types.Sexo;

/**
 * Created by kuquert on 03/06/17.
 */

public class Menor implements Serializable {

    @SerializedName("_id")
    private String id;
    private String nome;
    private Sexo sexo;
    private String certidaoNascimento;
    private Date dataNascimento;
    private String familiares;
    private List<Menor> menoresVinculados;
    private String refRaca;
    private boolean saudavel;
    private String descricaoSaude;
    private boolean curavel;
    private boolean deficienciaFisica;
    private boolean deficienciaMental;
    private String guiaAcolhimento;
    private String refCidade;
    private String refAbrigo;
    private String processoPoderFamiliar;
    private List<String> interessados;
    private boolean ativo;

    public String getNome() {
        return nome;
    }
    public String getId() { return this.id; }

    public Menor(String nome) {
        this.nome = nome;
    }
}
