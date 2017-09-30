package br.pucrs.ages.adocoes.MenorDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by Matheus on 03/09/2017.
 */

public class MenorDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MENOR = "menor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menor_details);

        Intent intentFromList = getIntent();
        if (intentFromList != null) {
            final Menor menor = (Menor) intentFromList.getSerializableExtra(EXTRA_MENOR);


            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.menor_details_layout, ViewPagerFragment.newInstance(menor), "ViewPager")
                        .add(R.id.menor_details_layout, CommonMenorInformationFragment.newInstance(menor), "CommonInformation")
                        .add(R.id.menor_details_layout, ParentesListFragment.newInstance(), "ParentesList")
                        .add(R.id.menor_details_layout, MenorDetailsButtonsFragment.newInstance(), "Buttons")
                        .commit();
            }
        }
    }
}
