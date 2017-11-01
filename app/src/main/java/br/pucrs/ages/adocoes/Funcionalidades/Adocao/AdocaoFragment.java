package br.pucrs.ages.adocoes.Funcionalidades.Adocao;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

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
 * Created by israeldeorce on 20/09/17.
 */

public class AdocaoFragment extends Fragment {

    private RecyclerView mRecyclerView;
//    private ListaInteresseAdotarAdapter mListAdapter;
    private Cursor mListaInteresseAdotar;
    private ArrayList<Menor> items = new ArrayList<>();
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
        return inflater.inflate(R.layout.fragment_listagem_interesse_adotar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_interesse_adotar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

//        mListaInteresseAdotar = DatabaseHelper.getInstance(getActivity()).getAllInteressesAdotar();

        if (mListaInteresseAdotar.getCount() == 0) {
            Toast.makeText(getActivity(), "A sua lista de interesses de adotar está vazia!", Toast.LENGTH_SHORT).show();
        } else {
            while (mListaInteresseAdotar.moveToNext()) {
                items.add(new Menor(mListaInteresseAdotar.getString(1)));
            }
        }

//        items.add(new Menor("Israel Lixo"));
//        items.add(new Menor("Hercilio Lindo"));
//        items.add(new Menor("Tonio Lanu"));
//        items.add(new Menor("Vaxxaro MAt"));
//        items.add(new Menor("Homero Greco"));
//        items.add(new Menor("Eduardo Pistola"));

        //Usa-se:
//        mListAdapter = new ListaInteresseAdotarAdapter(getActivity());
//        mListAdapter.setData(items);

        //FAZER FUNÇÃO SEMELHANTE PARA DELETE INTERESSE...

//        mListAdapter.setOnMenorDeleteInteresseListener(new ListaInteresseAdotarAdapter.OnMenorSelectedListener() {
//            @Override
//            public void OnMenorItemSelected(final Menor menor, final int position) {
//                // Coloque aqui a ação de desfavoritar :)
//
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
//
//                alert.setTitle("Atenção");
//                alert.setMessage("Deseja realmente desfavoritar " + menor.getNome() + "?");
//
//                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK button
//                        boolean result = DatabaseHelper.getInstance(getActivity()).removeFavorito(menor);
//                        if (result) {
//                            Toast.makeText(getActivity(), "desfavoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
//                            items.remove(position);
//                            mListAdapter.setData(items);
//
//                        } else {
//                            Toast.makeText(getActivity(), "deu ruim " + menor.getNome(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked Cancel button
//                    }
//                });
//
//                AlertDialog dialog = alert.create();
//                alert.show();
//            }
//        });

        //Usa-se:
//        mListAdapter.setListener(new ListaInteresseAdotarAdapter.OnMenorSelectedListener() {
//            @Override
//            public void OnMenorItemSelected(Menor menor, int position) {
//                // Coloque aqui a ação de ir para tela de detalhes :)
//
//                Toast.makeText(getActivity(), "selecionou " + menor.getNome(), Toast.LENGTH_SHORT).show();
//            }
//        });

        //Usa-se:
//        mRecyclerView.setAdapter(mListAdapter);
//        //mRecyclerView.setVisibility(View.VISIBLE);
    }
}
