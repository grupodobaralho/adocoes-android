package br.pucrs.ages.adocoes.Funcionalidades.Adocao;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.MenorDetailsActivity;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Israel Deorce on 20/09/17.
 */

public class AdocaoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AdocaoAdapter mAdocoesAdapter;
    private Cursor mListaAdocoes;
    private List<Menor> items = new ArrayList<>();
    private boolean isLogged;
    private PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
    //private ProgressBar mProgressBar;

    public AdocaoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adocao, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_adocao);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        isLogged = UserBusiness.getInstance().isLogged();

        if(isLogged)
            listaMenoresApi();
        else
            Toast.makeText(getActivity(), "Lista de Adocoes indisponivel para usuários anônimos.", Toast.LENGTH_SHORT);

        mAdocoesAdapter = new AdocaoAdapter(getActivity());
        mAdocoesAdapter.setData(items);

        mAdocoesAdapter.setListener(new AdocaoAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor, int position) {
                if(isLogged) {
                    Intent intent = new Intent(getActivity(), MenorDetailsActivity.class);
                    intent.putExtra(MenorDetailsActivity.EXTRA_MENOR, (menor));
                    startActivity(intent);
                } else{
                    Toast.makeText(getActivity(), "Detalhes de " + menor.getNome() + " para usuários não logados disponivel em breve!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRecyclerView.setAdapter(mAdocoesAdapter);
        //mRecyclerView.setVisibility(View.VISIBLE);

    }

    private void listaMenoresApi() {
        String token = UserBusiness.getInstance().getAccessToken();
        RestUtil.getEuEndPoint().getMenoresEu(token, "adotar").enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<Menor>> call, Response<List<Menor>> response) {
                if (response.body() != null) {
                    items = response.body();
                    pagerSnapHelper.attachToRecyclerView(null);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(mAdocoesAdapter);
                    mAdocoesAdapter.setData(items);
                }else {
                    Toast.makeText(getActivity(), "Erro: response.body() é null", Toast.LENGTH_SHORT).show();
                    try {
                        Log.e("Adocoes em Andamento", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Menor>> call, Throwable t) {
                Log.e("Adocoes em Andamento", t.getLocalizedMessage(), t);
            }
        });
    }
}
