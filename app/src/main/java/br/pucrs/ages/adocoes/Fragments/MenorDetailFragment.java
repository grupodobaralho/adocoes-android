package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.pucrs.ages.adocoes.CustomSwipeAdapter;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

public class MenorDetailFragment extends Fragment {

    private static String MENOR = "MENOR";
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    private Menor menor;

    public MenorDetailFragment() { }

    public static MenorDetailFragment newInstance(Menor menor) {
        Bundle args = new Bundle();
        MenorDetailFragment fragment = new MenorDetailFragment();
        args.putSerializable(MENOR, menor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.menor = (Menor) args.getSerializable(MENOR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menor_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        TextView nameTextView = (TextView) view.findViewById(R.id.nome_text_view);
        nameTextView.setText(menor.getNome());

        TextView sexoTextView = (TextView) view.findViewById(R.id.sexo_text_view);
        sexoTextView.setText(menor.getSexo().getValue());

        TextView saudeTextView = (TextView) view.findViewById(R.id.saude_text_view);
        saudeTextView.setText(menor.getDescricaoSaude());

        adapter = new CustomSwipeAdapter();
        viewPager.setAdapter(adapter);
    }
}
