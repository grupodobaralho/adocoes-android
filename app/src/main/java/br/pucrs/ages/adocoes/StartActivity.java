package br.pucrs.ages.adocoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Funcionalidades.Login.LoginActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final boolean logged = UserBusiness.getInstance().isLogged();
        if (logged) {
            final Intent main = new Intent(this, MainActivity.class);
            startActivity(main);
        } else {
            final Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }

    }
}
