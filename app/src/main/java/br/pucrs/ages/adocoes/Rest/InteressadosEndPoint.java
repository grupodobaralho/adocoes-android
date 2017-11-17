package br.pucrs.ages.adocoes.Rest;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kuquert on 03/06/17.
 */

public interface InteressadosEndPoint {

    //@POST("interessados")
    //Call<Usuario> updateInteressado(@Header("Authorization") String accessToken, @Body Usuario interessado);

    @GET("interessados")
    Call<List<Usuario>> getInteressados(@Header("Authorization") String accessToken);

    @GET("interessados/{id_interessado}")
    Call<Usuario> getInteressado(@Path("id_interessado") String interessadoId, @Header("Authorization") String accessToken);

    @PUT("interessados/{id_interessado}")
    Call<Usuario> putIteressado(@Path("id_interessado") String interessadoId, @Header("Authorization") String accessToken, @Body Usuario usuario);

    @DELETE("interessados/{id_interessado}")
    Call<Usuario> deleteInteressado(@Path("id_interessado") String interessadoId, @Header("Authorization") String accessToken, @Body Usuario usuario);

    //@POST("interessados/{id_interessado}/menores")
    //Call<Usuario> menorMidia(@Path("menorId") String menorId, @Path("midiaId") String midiaId, @Header("Authorization") String accessToken);

    //favorito|apadrinhamento|adocao
    //vale a pena transformar o tipoInteresse em Enum (pesquisar)
    @GET("interessados/{id_interessado}/menores")
    Call<List<Menor>> getMenoresInteressadoInteresse(@Path("id_interessado") String interessadoId, @Header("Authorization") String accessToken, @Query("interesse") String tipo);

}
