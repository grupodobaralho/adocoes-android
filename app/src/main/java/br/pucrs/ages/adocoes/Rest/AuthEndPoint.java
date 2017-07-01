package br.pucrs.ages.adocoes.Rest;

import br.pucrs.ages.adocoes.Model.dto.Request.AuthRequest;
import br.pucrs.ages.adocoes.Model.dto.Response.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by kuquert on 03/06/17.
 */

public interface AuthEndPoint {
    @POST("/adocoes/oauth")
    Call<AuthResponse> authUser(@Header("Authorization") String authorization, @Body AuthRequest request);
}
