package br.pucrs.ages.adocoes.Funcionalidades.ListagemDeMenores;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.pucrs.ages.adocoes.Database.SQLite.DatabaseHelper;
import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.MenorDetailsActivity;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.pucrs.ages.adocoes.R.id;
import static br.pucrs.ages.adocoes.R.layout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMenoresFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListaVerticalAdapter mListaVerticalAdapter;
    private ListaHorizontalAdapter mListaHorizontalAdapter;
    private List<Menor> menores ;
    protected MenuItem listagemHorizontal;
    private PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

    public ListaMenoresFragment() {
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

        mListaVerticalAdapter.setOnMenorFavoritarListener(new ListaVerticalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de favoritar :)

                // Chama o Banco e verifica se o menor já é um favorito
                boolean isFavorite = DatabaseHelper.getInstance(getActivity()).contemMenor(menor);
                if(isFavorite) {
                    Toast.makeText(getActivity(), ";) Já favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Chama o Banco e tenta inserir um novo favorito
                boolean result = DatabaseHelper.getInstance(getActivity()).insereFavorito(menor);
                if(result)
                    Toast.makeText(getActivity(), "favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "nao favoritou", Toast.LENGTH_SHORT).show();
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

        /**
         * Listener para favoritar um menor na lista Horizontal
         */
        mListaHorizontalAdapter.setOnMenorFavoritarListener(new ListaHorizontalAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de favoritar :)

                // Chama o Banco e verifica se o menor já é um favorito
                boolean isFavorite = DatabaseHelper.getInstance(getActivity()).contemMenor(menor);
                if(isFavorite) {
                    Toast.makeText(getActivity(), ";) Já favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Chama o Banco e tenta inserir um novo favorito
                boolean result = DatabaseHelper.getInstance(getActivity()).insereFavorito(menor);
                if(result)
                    Toast.makeText(getActivity(), "favoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "nao favoritou", Toast.LENGTH_SHORT).show();
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

        RestUtil.getMenoresEndPoint().menores("Bearer anonymous").enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<Menor>> call, Response<List<Menor>> response) {
                menores = response.body();
                System.out.println(menores);
                setItems(true);
            }

            @Override
            public void onFailure(Call<List<Menor>> call, Throwable t) {
                Log.e("ListagemDeMenores", t.getLocalizedMessage(), t);
            }
        });

    }

    public void setItems( boolean isListagemVertical) {
        if (isListagemVertical){
            pagerSnapHelper.attachToRecyclerView(null);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            this.mRecyclerView.setLayoutManager(layoutManager);
            this.mRecyclerView.setAdapter(mListaVerticalAdapter);
            mListaVerticalAdapter.setData(menores);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            pagerSnapHelper.attachToRecyclerView(this.mRecyclerView);
            this.mRecyclerView.setLayoutManager(layoutManager);
            this.mRecyclerView.setAdapter(mListaHorizontalAdapter);
            mListaHorizontalAdapter.setData(menores);
        }
    }


}