package br.pucrs.ages.adocoes.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.MenorDetails.MenorDetailsActivity;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMenoresCardFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private ListaHorizontalAdapter mListAdapter;
    private ArrayList<Menor> items = new ArrayList<>();


    public ListaMenoresCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_menores_horizontal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        items.add(new Menor("Marcus Kuquert 1"));
        items.add(new Menor("Marcus Kuquert 2"));
        items.add(new Menor("Marcus Kuquert 3"));
        items.add(new Menor("Marcus Kuquert 4"));
        items.add(new Menor("Marcus Kuquert 5"));
        items.add(new Menor("Marcus Kuquert 6"));
        items.add(new Menor("Marcus Kuquert 7"));
        items.add(new Menor("Marcus Kuquert 8"));


        PagerSnapHelper snapHelper = new PagerSnapHelper();
        mListAdapter = new ListaHorizontalAdapter(getActivity());
        mListAdapter.setData(items);

        snapHelper.attachToRecyclerView(mRecyclerView);

        mListAdapter.setOnMenorFavoritarListener(new ListaHorizontalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de favoritar :)

                Toast.makeText(getActivity(), "favoritou "+ menor.getNome(), Toast.LENGTH_SHORT)
                        .show();
            }
        });

        mListAdapter.setListener(new ListaHorizontalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                final Intent intent = new Intent(getActivity(), MenorDetailsActivity.class);
                intent.putExtra(MenorDetailsActivity.EXTRA_MENOR, menor);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mListAdapter);
    }
}