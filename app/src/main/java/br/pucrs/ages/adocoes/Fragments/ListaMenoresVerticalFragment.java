package br.pucrs.ages.adocoes.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.DatabaseHelper;
import br.pucrs.ages.adocoes.Model.Menor;

import static br.pucrs.ages.adocoes.R.id;
import static br.pucrs.ages.adocoes.R.layout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMenoresVerticalFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListaVerticalAdapter mListAdapter;
    private ArrayList<Menor> items = new ArrayList<>();
    protected MenuItem listagemHorizontal;

    public ListaMenoresVerticalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layout.fragment_lista_menores_vertical, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        items.add(new Menor("Marcus Kuquert 1"));
        items.add(new Menor("Marcus Kuquert 2"));
        items.add(new Menor("Marcus Kuquert 3"));
        items.add(new Menor("Marcus Kuquert 4"));
        items.add(new Menor("Marcus Kuquert 5"));
        items.add(new Menor("Marcus Kuquert 6"));
        items.add(new Menor("Marcus Kuquert 7"));
        items.add(new Menor("Marcus Kuquert 8"));

        mListAdapter = new ListaVerticalAdapter(getActivity());
        mListAdapter.setData(items);

        mListAdapter.setOnMenorFavoritarListener(new ListaVerticalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de favoritar :)
                boolean isFavorite = DatabaseHelper.getInstance(getActivity()).contemMenor(menor);
                if(isFavorite) {
                    Toast.makeText(getActivity(), "Já é favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean result = DatabaseHelper.getInstance(getActivity()).insereFavorito(menor);
                if(result)
                    Toast.makeText(getActivity(), "favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "nao favoritou", Toast.LENGTH_SHORT).show();
            }
        });

        mListAdapter.setListener(new ListaVerticalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de ir para tela de detalhes :)

                Toast.makeText(getActivity(), "selecionou " + menor.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mListAdapter);
    }
}
