package br.pucrs.ages.adocoes.Funcionalidades.Favoritos;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

import br.pucrs.ages.adocoes.Database.SQLite.DatabaseHelper;
import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.MenorDetailsActivity;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.ObjetoDeMenorEu;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
*/

/**
 * Created by Israel Deorce on 20/09/17.
 */

public class FavoritosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FavoritosAdapter mListAdapter;
    private Cursor mListaFavoritos;
    private List<Menor> items = new ArrayList<>();
    private boolean isLogged;
    private PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
    //private ProgressBar mProgressBar;

    public FavoritosFragment() {
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
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_favoritos);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        isLogged = UserBusiness.getInstance().isLogged();

        if(isLogged)
            listaMenoresApi();
        else
            listaMenoresLocal();

        mListAdapter = new FavoritosAdapter(getActivity());
        mListAdapter.setData(items);

        mListAdapter.setOnMenorDesfavoritarListener(new FavoritosAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(final Menor menor, final int position) {
                // Coloque aqui a ação de desfavoritar :)
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Atenção");
                alert.setMessage("Deseja realmente desfazer interesse em " + menor.getNome() + "?");

                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        if(isLogged)
                            desfazerInteresseApi(menor, position);
                        else
                            desfazerInteresseLocal(menor, position);
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
        });

        mListAdapter.setListener(new FavoritosAdapter.OnMenorSelectedListener() {
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

        mRecyclerView.setAdapter(mListAdapter);
        //mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void listaMenoresApi(){
        String token = UserBusiness.getInstance().getAccessToken();
        RestUtil.getEuEndPoint().getMenoresEu(token, "favoritar").enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<Menor>> call, Response<List<Menor>> response) {
                if (response.body() != null) {
                    items = response.body();
                    pagerSnapHelper.attachToRecyclerView(null);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(mListAdapter);
                    mListAdapter.setData(items);
                }else {
                    Toast.makeText(getActivity(), "Erro: response.body() é null", Toast.LENGTH_SHORT).show();
                    try {
                        Log.e("ListagemDeInteresses", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Menor>> call, Throwable t) {
                Log.e("ListagemDeInteresses", t.getLocalizedMessage(), t);
            }
        });
//        RestUtil.getEuEndPoint().getMenoresEu(token, "favoritar").enqueue(new Callback<List<ObjetoDeMenorEu>>() {
//            @Override
//            public void onResponse(Call<List<ObjetoDeMenorEu>> call, Response<List<ObjetoDeMenorEu>> response) {
//                if (response.body() != null) {
//                    if(response.body().isEmpty())
//                        Toast.makeText(getActivity(), "A sua lista de interesses está vazia!", Toast.LENGTH_SHORT).show();
//                    else {
//                        for (ObjetoDeMenorEu o : response.body()) {
//                            List<Menor> list = o.getMenores();
//                            if (list != null) {
//                                items.addAll(list);
//                            }
//                        }
//                        //items = response.body();
//                        pagerSnapHelper.attachToRecyclerView(null);
//                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setAdapter(mListAdapter);
//                        mListAdapter.setData(items);
//                    }
//                }else {
//                    Toast.makeText(getActivity(), "Acesso não autorizado.", Toast.LENGTH_SHORT).show();
//                    try {
//                        Log.e("ListagemDeInteresses", response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<List<ObjetoDeMenorEu>> call, Throwable t) {
//                Log.e("ListagemDeInteresses", t.getLocalizedMessage(), t);
//            }
//        });

    }
    private void listaMenoresLocal(){
        mListaFavoritos = DatabaseHelper.getInstance(getActivity()).getAllFavoritos();

        if (mListaFavoritos.getCount() == 0) {
            Toast.makeText(getActivity(), "A sua lista de interesses está vazia!", Toast.LENGTH_SHORT).show();
        } else {
            while (mListaFavoritos.moveToNext()) {
                Menor menor = new Menor(mListaFavoritos.getString(1));
                items.add(menor);
            }
        }
    }

    private void desfazerInteresseApi(final Menor menor, final int position){
        String token = UserBusiness.getInstance().getAccessToken();
        //Call<List<Menor>> getMenoresInteressado(@Header("Authorization") String accessToken, @Query("interesse") String tipo);
        RestUtil.getEuEndPoint().deleteMenorEu(token, menor.getId(), "favoritar").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    items.remove(position);
                    pagerSnapHelper.attachToRecyclerView(null);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(mListAdapter);
                    mListAdapter.setData(items);
                    Toast.makeText(getActivity(), "Removeu interesse por " + menor.getNome(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Erro: caiu no else do onResponse " + menor.getId(), Toast.LENGTH_SHORT).show();
                    try {
                        Log.e("Desfazer Interesse", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Desfazer Interesse", t.getLocalizedMessage(), t);
            }
        });
        //Toast.makeText(getActivity(), "Ainda não implementado", Toast.LENGTH_SHORT).show();
    }
    private void desfazerInteresseLocal(Menor menor, int position){
        boolean result = DatabaseHelper.getInstance(getActivity()).removeFavorito(menor);
        if (result) {
            Toast.makeText(getActivity(), "Removeu interesse por " + menor.getNome(), Toast.LENGTH_SHORT).show();
            items.remove(position);
            mListAdapter.setData(items);

        } else {
            Toast.makeText(getActivity(), "Erro " + menor.getNome(), Toast.LENGTH_SHORT).show();
        }
    }
}
