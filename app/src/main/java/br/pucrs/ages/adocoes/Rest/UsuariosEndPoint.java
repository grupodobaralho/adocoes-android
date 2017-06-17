package br.pucrs.ages.adocoes.Rest;

import java.util.List;
import br.pucrs.ages.adocoes.Model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by kuquert on 03/06/17.
 */

public interface UsuariosEndPoint {
    @POST("/usuarios")
    Call<Usuario> updateUsuario(@Header("access-token") String accessToken, @Body Usuario usuario);

    @GET("/usuarios")
    Call<List<Usuario>> usuarios(@Header("access-token") String accessToken);

    @GET("/usuarios/{usuarioId}")
    Call<Usuario> usuario(@Path("usuarioId") String portfolioId, @Header("access-token") String accessToken);
}
