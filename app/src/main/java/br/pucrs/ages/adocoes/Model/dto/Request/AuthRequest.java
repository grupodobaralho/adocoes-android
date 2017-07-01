package br.pucrs.ages.adocoes.Model.dto.Request;

/**
 * Created by kuquert on 03/06/17.
 */

public class AuthRequest {

    private String username;
    private String password;
    private String grant_type;

    public AuthRequest(String username, String password) {
        this.grant_type = "password";
        this.username = username;
        this.password = password;
    }
}
