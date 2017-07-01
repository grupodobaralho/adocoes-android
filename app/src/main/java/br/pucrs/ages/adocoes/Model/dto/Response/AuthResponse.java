package br.pucrs.ages.adocoes.Model.dto.Response;

import br.pucrs.ages.adocoes.Model.dto.AccessToken;

/**
 * Created by kuquert on 30/06/17.
 */

public class AuthResponse {
    private AccessToken access_token;
    private String token_type;

    public AccessToken getAccess_token() {
        return access_token;
    }
}
