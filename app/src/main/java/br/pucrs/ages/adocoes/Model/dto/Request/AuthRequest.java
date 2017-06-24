package br.pucrs.ages.adocoes.Model.dto.Request;

/**
 * Created by kuquert on 03/06/17.
 */

public class AuthRequest {
    private String email;
    private String senha;

    public AuthRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
