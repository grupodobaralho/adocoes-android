package br.pucrs.ages.adocoes.Rest;

import br.pucrs.ages.adocoes.Model.Usuario;
import br.pucrs.ages.adocoes.Model.dto.Request.AuthRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kuquert on 03/06/17.
 */

public interface AuthEndPoint {
    @POST("/oauth/token")
    Call<Usuario> authUser(@Body AuthRequest request);
}
