package br.pucrs.ages.adocoes.Rest;

import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.pucrs.ages.adocoes.AdocoesApplication;
import br.pucrs.ages.adocoes.Model.dto.Response.ErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kuquert on 03/06/17.
 */

public class RestUtil {


//    private static OkHttpClient httpClient = new OkHttpClient.Builder()
//            .addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request.Builder ongoing = chain.request().newBuilder();
//                    ongoing.addHeader("Accept", "application/json;versions=1");
//                    String token = UserBusiness.getInstance().getAccessToken();
//                    if (token != null) {
//                        ongoing.addHeader("Authorization", token);
//                    }
//                    return chain.proceed(ongoing.build());
//                }
//            })
//            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.homo.ages.pucrs.br/adocoes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static AuthEndPoint getAuthEndPoint() {
        return retrofit.create(AuthEndPoint.class);
    }

    public static ConteudosEndPoint getConteudosEndPoint() {
        return retrofit.create(ConteudosEndPoint.class);
    }

    public static euEndPoint getEuEndPoint() {
        return retrofit.create(euEndPoint.class);
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

    public static void showApiError(Response response) {
        ErrorResponse errorResponse = errorConverter(response.errorBody());
        if (errorResponse != null) {
            Toast.makeText(AdocoesApplication.getAdocoesApplicationContext(), errorResponse.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private static ErrorResponse errorConverter(ResponseBody response) {
        try {
            Converter<ResponseBody, ErrorResponse> errorConverter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
            return errorConverter.convert(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}


