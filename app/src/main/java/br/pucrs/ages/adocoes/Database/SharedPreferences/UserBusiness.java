package br.pucrs.ages.adocoes.Database.SharedPreferences;

import android.util.Log;

import java.io.IOException;

import br.pucrs.ages.adocoes.Model.Eu;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBusiness {

    private String accessToken;
    private String userId;
    private Eu eu;

    private static final UserBusiness ourInstance = new UserBusiness();

    private static final String prefix = "Bearer ";
    private static final String anonymousToken = "Bearer anonymous";
    private float pontoIdade;
    private float pontoSexo;
    private float targetCoordinateX;
    private float targetCoordinateY;

    public static UserBusiness getInstance() {
        return ourInstance;
    }

    public void updateAccessToken(String accessToken, String userId) {
        this.accessToken = prefix  + accessToken;
        this.userId = userId;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.ACCESS_TOKEN, this.accessToken);
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.USER_ID, this.userId);
    }

    public String getAccessToken() {
        if (accessToken == null) {
            accessToken = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        }
        return accessToken;
    }

    public String getUserId() {
        if (userId == null) {
            userId = SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN);
        }
        return userId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.ACCESS_TOKEN, accessToken);
    }

    public void setAnonymousToken() {
        setAccessToken(anonymousToken);
    }

    /**
     * Verifica se o Usuário está logado
     * @return
     */
    public boolean isLogged(){
        if(getAccessToken() == null || getAccessToken().equals("Bearer anonymous"))
            return false;
        else
            return true;
    }

    public String getIdInteressado(){
        String token = getAccessToken();
        //Call<Eu> getEu(@Header("Authorization") String accessToken);
        eu = new Eu();
        RestUtil.getEuEndPoint().getEu(token).enqueue(new Callback<Eu>() {
            @Override
            public void onResponse(Call<Eu> call, Response<Eu> response) {
                if (response.body() != null) {
                    eu = response.body();
                }else {
                    try {
                        Log.e("Erro no getEu", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Eu> call, Throwable t) {
                Log.e("Erro no getEu", t.getLocalizedMessage(), t);
            }
        });
        return "59b341db82ed24d7e1d8dd32";
    }


    public void setPontoIdade(float pontoIdade) {
        this.pontoIdade = pontoIdade;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.PONTO_IDADE, pontoIdade);
    }

    public double getPontoIdade() {
        return SharedPreferencesOperations.loadFloatFromPrefs(SharedPreferencesOperations.PONTO_IDADE, 9);
    }

    public void setPontoSexo(float pontoSexo) {
        this.pontoSexo = pontoSexo;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.PONTO_SEXO, pontoSexo);
    }

    public double getPontoSexo() {
        return SharedPreferencesOperations.loadFloatFromPrefs(SharedPreferencesOperations.PONTO_SEXO, 0.5f);
    }

    public void setTargetCoordinateX(float coordinateX) {
        this.targetCoordinateX = coordinateX;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.RAW_SEXO, coordinateX);
    }

    public float getTargetCoordinateX() {
        return SharedPreferencesOperations.loadFloatFromPrefs(SharedPreferencesOperations.RAW_SEXO, -1);
    }

    public void setTargetCoordinateY(float coordinateY) {
        this.targetCoordinateY = coordinateY;
        SharedPreferencesOperations.saveOnPrefs(SharedPreferencesOperations.RAW_IDADE, coordinateY);
    }

    public float getTargetCoordinateY() {
        return SharedPreferencesOperations.loadFloatFromPrefs(SharedPreferencesOperations.RAW_IDADE, -1);
    }
}
