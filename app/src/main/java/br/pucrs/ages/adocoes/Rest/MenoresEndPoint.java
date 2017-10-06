package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Fragments.MenorMidia;
import br.pucrs.ages.adocoes.Model.Menor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by kuquert on 03/06/17.
 */

public interface MenoresEndPoint {
    @GET("menores")
    Call<List<Menor>> menores(@Header("Authorization") String accessToken);

    @GET("menores/{menorId}")
    Call<Menor> menor(@Path("menorId") String portfolioId, @Header("access-token") String accessToken);

    @POST("menores/{menorId}")
    Call<Menor> updateMenor(@Path("menorId") String portfolioId, @Header("access-token") String accessToken, @Body Menor menor);

    @GET("menores/{menorId}/midias")
    Call<MenorMidia> midiasMenor(@Header("access-token") String accessToken, @Path("menorId") String menorId);
}
