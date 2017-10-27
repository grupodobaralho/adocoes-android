package br.pucrs.ages.adocoes.Funcionalidades.ListagemConteudoInstitucional;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.pucrs.ages.adocoes.Funcionalidades.ConteudoInstitucionalDetails.ConteudoInstitucionalDetails;
import br.pucrs.ages.adocoes.Model.Conteudo;
import br.pucrs.ages.adocoes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListagemConteudoInstitucionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListagemConteudoInstitucionalFragment extends Fragment {

    private RecyclerView recyclerView;

    public static String EXTRA = "EXTRA_IMAGES";

    public ListagemConteudoInstitucionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListagemConteudoInstitucionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListagemConteudoInstitucionalFragment newInstance() {
        ListagemConteudoInstitucionalFragment fragment = new ListagemConteudoInstitucionalFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conteudo_institucional, container, false);

        recyclerView = ((RecyclerView) view.findViewById(R.id.recycler_view));

        ConteudoRecyclerAdapter conteudoRecyclerAdapter = new ConteudoRecyclerAdapter(getActivity());
        recyclerView.setAdapter(conteudoRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        conteudoRecyclerAdapter.setMockData();

        conteudoRecyclerAdapter.setListener(new ConteudoRecyclerAdapter.OnSelectedListener() {
            @Override
            public void onItemSelected(Conteudo conteudo) {
                Intent intent = new Intent(getActivity(), ConteudoInstitucionalDetails.class);
                intent.putExtra(EXTRA, conteudo);
                startActivity(intent);
            }
        });

        return view;
    }

}
