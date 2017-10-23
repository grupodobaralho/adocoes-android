package br.pucrs.ages.adocoes.Funcionalidades.TermosDeUso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.pucrs.ages.adocoes.R;

public class TermosDeUsoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_de_uso);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.termos_layout, new TermosFragment(), "Termos de Uso")
                .commit();
    }
}
