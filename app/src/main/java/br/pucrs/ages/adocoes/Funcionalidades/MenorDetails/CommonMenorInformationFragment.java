package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by Matheus on 03/09/2017.
 */
public class CommonMenorInformationFragment extends Fragment {

    private static final String ARGUMENT_MENOR = "menor";

    public static CommonMenorInformationFragment newInstance(Menor menor) {
        final Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_MENOR, menor);
        final CommonMenorInformationFragment fragment = new CommonMenorInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_common_menor_information, container, false);
        final TextView nameTextView = (TextView) view.findViewById(R.id.nome_text_view);
        final TextView ageTextView = (TextView) view.findViewById(R.id.idade_text_view);
        final TextView genderTextView = (TextView) view.findViewById(R.id.sexo_text_view);
        final TextView healthTextView = (TextView) view.findViewById(R.id.saude_text_view);

        final Bundle args = getArguments();
        final Menor menor = (Menor) args.getSerializable(ARGUMENT_MENOR);

        nameTextView.setText(menor.getNome());
        ageTextView.setText("8");
        healthTextView.setText(menor.getDescricaoSaude());
        genderTextView.setText(menor.getSexo().toString());
        return view;
    }

}
