package br.pucrs.ages.adocoes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by kuquert on 03/06/17.
 */

public class Conteudo implements Serializable {
    private String nome;
    private String pagina;
    private List<String> midia;
    private Date timestampCriacao;
    private Date timestampInicio;
    private Date timestampFim;
    private boolean ativo;
}