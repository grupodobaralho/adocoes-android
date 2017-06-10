package br.pucrs.ages.adocoes.model;

import java.io.Serializable;

import br.pucrs.ages.adocoes.model.types.Perfis;

/**
 * Created by kuquert on 03/06/17.
 */

public class Usuario implements Serializable {
    private String email;
    private String senha;
    private String nome;
    private Perfis perfis;
    private String refPerfilAdministrador;
    private String refPerfilInteressado;
    private Boolean ativo;
}
