package br.pucrs.ages.adocoes.Funcionalidades.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.pucrs.ages.adocoes.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new LoginFragment())
                .commit();
    }
}

