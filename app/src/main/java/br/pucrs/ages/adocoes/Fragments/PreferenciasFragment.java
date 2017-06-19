package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;


import br.pucrs.ages.adocoes.R;

public class PreferenciasFragment extends Fragment {

    private ProgressBar mProgressBar;

    public PreferenciasFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preferencias, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.portfolio_allocation_list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.native_progress_bar);

        Button nomeParaOBotao = (Button) view.findViewById(R.id.teste1b);
        final TextView textView = (TextView) view.findViewById(R.id.teste1tv);

        nomeParaOBotao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText("Deu certo");
            }
        });


        mProgressBar.setVisibility(View.VISIBLE);
        nomeParaOBotao.setVisibility(view.VISIBLE);
        textView.setVisibility(view.VISIBLE);

    }

}
