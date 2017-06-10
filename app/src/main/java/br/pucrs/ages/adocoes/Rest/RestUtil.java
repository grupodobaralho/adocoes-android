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

    public static MenoresEndPoint getMenoresEndPoint() {
        return retrofit.create(MenoresEndPoint.class);
    }

}
