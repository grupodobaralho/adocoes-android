package br.pucrs.ages.adocoes.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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



import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by israeldeorce on 20/09/17.
 */

public class FavoritosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FavoritosAdapter mListAdapter;
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

        items.add(new Menor("Israelzinho1"));
        items.add(new Menor("Israelzinho2"));
        items.add(new Menor("Israelzinho3"));
        items.add(new Menor("Israelzinho4"));
        items.add(new Menor("Israelzinho5"));
        items.add(new Menor("Israelzinho6"));

        //Tentando salvar na memoria
/*
        try (FileOutputStream fos = getActivity().openFileOutput("listagem_de_favoritos.txt", Context.MODE_PRIVATE)) {
            PrintWriter p = new PrintWriter(fos);
            for(Menor menor : items) {
                p.append(menor.getNome());
                Toast.makeText(getActivity(), "add " + p.toString(), Toast.LENGTH_SHORT).show();
            }
            //fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

            String ret = "";

            try {
                InputStream inputStream = getActivity().openFileInput("listagem_de_favoritos.txt");

                if ( inputStream != null ) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        lendoArquivo.add(new Menor(receiveString));
                        Toast.makeText(getActivity(), "leu " + receiveString, Toast.LENGTH_SHORT).show();
                    }
                    inputStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
*/


        mListAdapter = new FavoritosAdapter(getActivity());
        mListAdapter.setData(items);

        mListAdapter.setOnMenorDesfavoritarListener(new FavoritosAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de desfavoritar :)

                Toast.makeText(getActivity(), "desfavoritou " + menor.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        mListAdapter.setListener(new FavoritosAdapter.OnMenorSelectedListener() {
            @Override
            public void OnMenorItemSelected(Menor menor) {
                // Coloque aqui a ação de ir para tela de detalhes :)

                Toast.makeText(getActivity(), "selecionou " + menor.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(mListAdapter);
        //mRecyclerView.setVisibility(View.VISIBLE);
    }
}
