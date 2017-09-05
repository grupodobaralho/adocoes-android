package br.pucrs.ages.adocoes.MenorDetails;


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
    private static final String ARGUMENT_NAME = "nome";
    private static final String ARGUMENT_GENDER = "sexo";
    private static final String ARGUMENT_AGE = "idade";
    private static final String ARGUMENT_HEALTH = "saude";

    public static CommonMenorInformationFragment newInstance(Menor menor) {
        final Bundle args = new Bundle();
        args.putString(ARGUMENT_NAME, menor.getNome());
        // PEGAR SEXO
        // PEGAR IDADE
        // PEGAR ESTADO DE SAUDE
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
        nameTextView.setText(args.getString(ARGUMENT_NAME));
        // TODO: Alterar os sets abaixo para pegar informações do bundle
        ageTextView.setText("18");
        genderTextView.setText("Masculino");
        healthTextView.setText("EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ EOQ ");
        return view;
    }

}
