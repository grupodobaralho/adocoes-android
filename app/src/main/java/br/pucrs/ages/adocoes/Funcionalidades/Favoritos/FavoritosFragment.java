package br.pucrs.ages.adocoes.Funcionalidades.Favoritos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
/*
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
*/
import java.util.ArrayList;


import br.pucrs.ages.adocoes.Database.SQLite.DatabaseHelper;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by israeldeorce on 20/09/17.
 */

public class FavoritosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FavoritosAdapter mListAdapter;
    private Cursor mListaFavoritos;
    private ArrayList<Menor> items = new ArrayList<>();
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

        mListaFavoritos = DatabaseHelper.getInstance(getActivity()).getAllFavoritos();

        if (mListaFavoritos.getCount() == 0) {
            Toast.makeText(getActivity(), "A sua lista de favoritos está vazia!", Toast.LENGTH_SHORT).show();
        } else {
            while (mListaFavoritos.moveToNext()) {
                Log.d("um bug", mListaFavoritos.getString(1));
                Log.d("um bug", mListaFavoritos.getString(2));
                Menor menor = new Menor(mListaFavoritos.getString(1));
                items.add(menor);
            }
        }
        /*
        items.add(new Menor("Israelzinho1"));
        items.add(new Menor("Israelzinho2"));
        items.add(new Menor("Israelzinho3"));
        items.add(new Menor("Israelzinho4"));
        items.add(new Menor("Israelzinho5"));
        items.add(new Menor("Israelzinho6"));
        */

        mListAdapter = new FavoritosAdapter(getActivity());
        mListAdapter.setData(items);



        mListAdapter.setOnMenorDesfavoritarListener(new FavoritosAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(final Menor menor, final int position) {
                // Coloque aqui a ação de desfavoritar :)


                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Atenção");
                alert.setMessage("Deseja realmente desfavoritar " + menor.getNome() + "?");

                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        boolean result = DatabaseHelper.getInstance(getActivity()).removeFavorito(menor);
                        if (result) {
                            Toast.makeText(getActivity(), "desfavoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
                            items.remove(position);
                            mListAdapter.setData(items);

                        } else {
                            Toast.makeText(getActivity(), "Erro " + menor.getNome(), Toast.LENGTH_SHORT).show();
                        }
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
                // Coloque aqui a ação de ir para tela de detalhes :)

                Toast.makeText(getActivity(), "selecionou " + menor.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mListAdapter);
        //mRecyclerView.setVisibility(View.VISIBLE);
    }
}
