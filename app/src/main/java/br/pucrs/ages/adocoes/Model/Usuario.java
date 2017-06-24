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
    private String cpf;
    private Perfis perfis;
    private String refPerfilAdministrador;
    private String refPerfilInteressado;
    private Boolean ativo;


    public String getNome() {
        return this.nome;
    }
    public String setNome(String nome) {
        return this.nome = nome;
    }
    public String getEmail() {
        return this.email;
    }
    public String setEmail(String email) {
        return this.email = email;
    }
    public String getCpf() {
        return this.cpf;
    }
    public String setCpf(String cpf){return this.cpf = cpf;}
}