package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Interessado;
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

public interface InteressadosEndPoint {
    @POST("/interessados")
    Call<List<Interessado>> createInteressado(@Header("access-token") String accessToken);

    @GET("/interessados")
    Call<List<Interessado>> interessados(@Header("access-token") String accessToken);

    @GET("/interessados/{interessadoId}")
    Call<Interessado> interessado(@Path("interessadoId") String portfolioId, @Header("access-token") String accessToken);

}
