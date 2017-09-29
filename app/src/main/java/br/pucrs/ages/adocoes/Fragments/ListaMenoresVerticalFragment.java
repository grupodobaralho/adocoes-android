package br.pucrs.ages.adocoes.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.MenorDetails.MenorDetailsActivity;
import br.pucrs.ages.adocoes.Model.Menor;

import static br.pucrs.ages.adocoes.R.id;
import static br.pucrs.ages.adocoes.R.layout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMenoresVerticalFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListaVerticalAdapter mListaVerticalAdapter;
    private ListaHorizontalAdapter mListaHorizontalAdapter;
    private List<Menor> items = new ArrayList<>();
    protected MenuItem listagemHorizontal;
    private PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

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


        mListaVerticalAdapter = new ListaVerticalAdapter(getActivity());
        mListaHorizontalAdapter = new ListaHorizontalAdapter(getActivity());

        mListaVerticalAdapter.setData(items);

        mListaVerticalAdapter.setOnMenorFavoritarListener(new ListaVerticalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de favoritar :)

                Toast.makeText(getActivity(), "favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        mListaVerticalAdapter.setListener(new ListaVerticalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de ir para tela de detalhes :)
                // Boa Roberto ;)

                Intent intent = new Intent(getActivity(), MenorDetailsActivity.class);
                intent.putExtra(MenorDetailsActivity.EXTRA_MENOR, menor);
                startActivity(intent);
            }
        });

        mListaHorizontalAdapter.setOnMenorFavoritarListener(new ListaHorizontalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                Toast.makeText(getActivity(), "favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        mListaHorizontalAdapter.setListener(new ListaHorizontalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                Intent intent = new Intent(getActivity(), MenorDetailsActivity.class);
                intent.putExtra(MenorDetailsActivity.EXTRA_MENOR, menor);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mListaVerticalAdapter);
    }

    public void setItems(List<Menor> items, boolean isListagemVertical) {
        this.items = items;
        if (isListagemVertical){
            pagerSnapHelper.attachToRecyclerView(null);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            this.mRecyclerView.setLayoutManager(layoutManager);
            this.mRecyclerView.setAdapter(mListaVerticalAdapter);
            mListaVerticalAdapter.setData(items);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            pagerSnapHelper.attachToRecyclerView(this.mRecyclerView);
            this.mRecyclerView.setLayoutManager(layoutManager);
            this.mRecyclerView.setAdapter(mListaHorizontalAdapter);
            mListaHorizontalAdapter.setData(items);
        }
    }


}
