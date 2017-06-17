package br.pucrs.ages.adocoes.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kuquert on 03/06/17.
 */

public class RestUtil {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static AuthEndPoint getAuthEndPoint() {
        return retrofit.create(AuthEndPoint.class);
    }

    public static ConteudosEndPoint getConteudosEndPoint() {
        return retrofit.create(ConteudosEndPoint.class);
    }

    public static InteressadosEndPoint getInteressadosEndPoint() {
        return retrofit.create(InteressadosEndPoint.class);
    }

    public static MenoresEndPoint getMenoresEndPoint() {
        return retrofit.create(MenoresEndPoint.class);
    }

    public static UsuariosEndPoint getUsuariosEndPoint() {
        return retrofit.create(UsuariosEndPoint.class);
    }

}
