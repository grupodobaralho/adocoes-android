package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Eu;
import br.pucrs.ages.adocoes.Model.Body.Interesse;
import br.pucrs.ages.adocoes.Model.Menor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Israel Deorce on 11/11/17.
 */

public interface euEndPoint {

    @GET ("eu")
    Call<Eu> getEu(@Header("Authorization") String accessToken);

    @POST("eu/menores")
    Call<Menor> postMenorInteresse(@Header("Authorization") String accessToken, @Body Interesse body);

    @GET("eu/menores")
    Call<List<Menor>> getMenoresEu(@Header("Authorization") String accessToken, @Query("interesse") String tipo);

    //RFE04: DELETE /eu/menores/{id_menor}

    //RFE05: PUT /eu/ordenacao

}
