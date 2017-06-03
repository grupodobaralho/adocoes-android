package br.pucrs.ages.adocoes.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kuquert on 03/06/17.
 */

public class Menor implements Serializable {

    @SerializedName("_id")
    private String id;
    private String nome;
    private String sexo;
    private String certidaoNascimento;
    private String dataNascimento;
    private String familiares;
    private String menoresVinculados;
    private String refRaca;
    private String saudavel;
    private String descricaoSaude;
    private String curavel;
    private String deficienciaFisica;
    private String deficienciaMental;
    private String guiaAcolhimento;
    private String refCidade;
    private String refAbrigo;
    private String processoPoderFamiliar;
    private String interessados;
    private String ativo;

}
