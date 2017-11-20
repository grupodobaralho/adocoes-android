package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Body.Interesse;
import br.pucrs.ages.adocoes.Model.Eu;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.ObjetoDeMenorEu;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Israel Deorce on 11/11/17.
 */

public interface EuEndPoint {

    @GET("eu")
    Call<Eu> getEu(@Header("Authorization") String accessToken);

    @POST("eu/menores")
    Call<Menor> postMenorInteresse(@Header("Authorization") String accessToken, @Body Interesse body);

    //@Query("interesse") String tipo
    @GET("eu/menores")
    Call<List<ObjetoDeMenorEu>> getMenoresEu(@Header("Authorization") String accessToken, @Query("interesse") String interesse);

    //https://stackoverflow.com/questions/36251080/retrofit-2-0-how-to-delete
    @DELETE("eu/menores/{id_menor}")
    Call<ResponseBody> deleteMenorEu(@Header("Authorization") String accessToken, @Path("id_menor") String id_menor, @Query("interesse") String interesse);

    //@PUT("/eu/ordenacao")
}
