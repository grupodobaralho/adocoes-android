package br.pucrs.ages.adocoes.Funcionalidades.ListagemDeMenores;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.io.IOException;
import java.util.List;

import br.pucrs.ages.adocoes.Database.SQLite.DatabaseHelper;
import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.MenorDetailsActivity;
import br.pucrs.ages.adocoes.Model.Body.Interesse;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import okhttp3.ResponseBody;
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
    private boolean isLogged;


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
        isLogged = UserBusiness.getInstance().isLogged();

        mListaVerticalAdapter = new ListaVerticalAdapter(getActivity());
        mListaHorizontalAdapter = new ListaHorizontalAdapter(getActivity());


        final OnMenorSelectedListener acaoDeFavoritar = new OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(final Menor menor) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Atenção");
                alert.setMessage("Deseja demonstrar interesse em " + menor.getNome() + "?");

                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isLogged = UserBusiness.getInstance().isLogged();
                        // User clicked OK button
                        if(isLogged)
                            demonstraInteresseApi(menor);
                        else
                            demonstraInteresseLocal(menor);
                    }
                });

                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Cancel button
                    }
                });

                AlertDialog dialog = alert.create();
                alert.show();
            }
        };

        final OnMenorSelectedListener acaoIrMenorDetalhes = new OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                Intent intent = new Intent(getActivity(), MenorDetailsActivity.class);
                intent.putExtra(MenorDetailsActivity.EXTRA_MENOR, (menor));
                startActivity(intent);
            }
        };

        mListaVerticalAdapter.setOnMenorFavoritarListener(acaoDeFavoritar);
        mListaHorizontalAdapter.setOnMenorFavoritarListener(acaoDeFavoritar);

        mListaVerticalAdapter.setListener(acaoIrMenorDetalhes);
        mListaHorizontalAdapter.setListener(acaoIrMenorDetalhes);

        mRecyclerView.setAdapter(mListaVerticalAdapter);
        fetchMenores();
    }

    public void fetchMenores() {
        String token = UserBusiness.getInstance().getAccessToken();
        double pontoIdade = UserBusiness.getInstance().getPontoIdade();
        double pontoSexo = UserBusiness.getInstance().getPontoSexo();
        final boolean listagemVertical = UserBusiness.getInstance().isListagemVertical();
        RestUtil.getMenoresEndPoint().menores(token, pontoIdade, pontoSexo).enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<Menor>> call, Response<List<Menor>> response) {
                if (response.body() != null) {
                    menores = response.body();
                    System.out.println(menores);
                    trocarVisualizacao(listagemVertical);
                }else {
                    try {
                        Log.e("ListagemDeMenores", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Menor>> call, Throwable t) {
                Log.e("ListagemDeMenores", t.getLocalizedMessage(), t);
            }
        });
    }

    private void demonstraInteresseApi(final Menor menor){
        String token = UserBusiness.getInstance().getAccessToken();
        System.out.println(menor.getId());
        RestUtil.getEuEndPoint().postMenorInteresse(token, new Interesse(menor.getId(), "favoritar")).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "Demonstrou interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    fetchMenores();
                }else {
                    try {
                        Log.e("Demonstra interesse", response.errorBody().string());
                        Toast.makeText(getActivity(), "Não foi possível demonstrar interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Demonstra interesse", t.getLocalizedMessage(), t);
                Toast.makeText(getActivity(), "caiu no failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void demonstraInteresseLocal(Menor menor){
        // Chama o Banco e verifica se o menor já é um favorito
        boolean isFavorite = DatabaseHelper.getInstance(getActivity()).contemMenor(menor);
        if(isFavorite) {
            Toast.makeText(getActivity(), "Já é um interessado em " + menor.getNome(), Toast.LENGTH_SHORT).show();
            return;
        }

        // Chama o Banco e tenta inserir um novo favorito
        boolean result = DatabaseHelper.getInstance(getActivity()).insereFavorito(menor);
        if(result)
            Toast.makeText(getActivity(), "Demonstrou interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Não foi possível demonstrar interesse em ", Toast.LENGTH_SHORT).show();
    }
    public void trocarVisualizacao(boolean isListagemVertical) {
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