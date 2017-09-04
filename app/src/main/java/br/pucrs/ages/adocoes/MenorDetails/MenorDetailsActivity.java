package br.pucrs.ages.adocoes.MenorDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.pucrs.ages.adocoes.R;

public class MenorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menor_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.menor_details_layout, ParentesListFragment.newInstance(), "ParentesList")
                    .commit();
        }
    }
}
