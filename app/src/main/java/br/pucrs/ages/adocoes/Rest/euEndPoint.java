package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Menor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by kuquert on 03/06/17.
 */

public interface euEndPoint {

    @GET("eu/menores")
    Call<List<Menor>> getMenoresInteressado(@Header("Authorization") String accessToken, @Query("interesse") String tipo);


}
