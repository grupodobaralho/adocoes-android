package br.pucrs.ages.adocoes.model;

import java.io.Serializable;

/**
 * Created by kuquert on 03/06/17.
 */

public class Endereco implements Serializable{
    private String glogradouro;
    private String gnumero;
    private String complemento;
    private String bairro;
    private String cep;
    private Cidade refCidade;
}
