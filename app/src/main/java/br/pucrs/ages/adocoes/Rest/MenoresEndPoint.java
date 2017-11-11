package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Interesse;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.MenorMidia;
import br.pucrs.ages.adocoes.Model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kuquert on 03/06/17.
 */

public interface MenoresEndPoint {
    @GET("menores")
    Call<List<Menor>> menores(@Header("Authorization") String accessToken);

    @GET("menores/{menorId}")
    Call<Menor> menor(@Path("menorId") String portfolioId, @Header("Authorization") String accessToken);

    //@DELETE("menores/{menorId}")
    //Call<Menor> menor(@Path("menorId") String portfolioId, @Header("Authorization") String accessToken);

    @POST("menores/{menorId}")
    Call<Menor> updateMenor(@Path("menorId") String portfolioId, @Header("Authorization") String accessToken, @Body Menor menor);

    @GET("menores/{menorId}/midias/{midiaId}")
    Call<MenorMidia> menorMidia(@Path("menorId") String menorId, @Path("midiaId") String midiaId, @Header("Authorization") String accessToken);

    @POST("menores/{menorId}/interessados")
    Call<Menor> postMenorInteresse(@Path("menorId") String menorId, @Header("Authorization") String accessToken, @Body Interesse body);

    @GET("menores/{menorId}/interessados")
    Call<List<Usuario>> getMenoresInteressadoInteresse(@Path("menorId") String interessadoId, @Header("Authorization") String accessToken, @Query("interesse") String tipoInteresse);


}
