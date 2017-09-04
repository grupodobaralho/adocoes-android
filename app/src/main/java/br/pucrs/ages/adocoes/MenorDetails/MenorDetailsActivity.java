package br.pucrs.ages.adocoes.MenorDetails;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by Matheus on 03/09/2017.
 */

public class MenorDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menor_details);

        // TODO: Receber menor de um intent e passar para os fragments

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.common_information_layout, CommonMenorInformationFragment.newInstance(), "CommonInformation")
                    .add(R.id.menor_details_layout, ParentesListFragment.newInstance(), "ParentesList")
                    .commit();
        }
    }
}
