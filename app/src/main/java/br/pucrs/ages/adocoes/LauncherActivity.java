package br.pucrs.ages.adocoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.pucrs.ages.adocoes.Login.LoginActivity;
import br.pucrs.ages.adocoes.Model.Usuario;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LauncherActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPreferencesOperations.loadFromPrefs(SharedPreferencesOperations.ACCESS_TOKEN) != null) {
            Call<Usuario> call = RestUtil.getUsuariosEndPoint().me(UserBusiness.getInstance().getAccessToken());

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        gotoMainArea();

                    }  else {
                        RestUtil.showApiError(response);
                        gotoLogin();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(AdocoesApplication.getAdocoesApplicationContext(), "Verifique sua conexão", Toast.LENGTH_LONG).show();
                    gotoLogin();
                }
            });
        } else {
            gotoLogin();
        }
    }


    private void gotoLogin() {
        Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void gotoMainArea() {
        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
