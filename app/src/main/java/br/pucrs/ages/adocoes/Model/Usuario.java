package br.pucrs.ages.adocoes.Model;

import java.io.Serializable;

import br.pucrs.ages.adocoes.Model.types.Perfis;

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
