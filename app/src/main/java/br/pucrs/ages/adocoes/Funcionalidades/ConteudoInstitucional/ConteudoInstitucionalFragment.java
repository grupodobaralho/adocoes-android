package br.pucrs.ages.adocoes.Funcionalidades.ConteudoInstitucional;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.pucrs.ages.adocoes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConteudoInstitucionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConteudoInstitucionalFragment extends Fragment {

    private RecyclerView recyclerView;

    public ConteudoInstitucionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConteudoInstitucionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConteudoInstitucionalFragment newInstance(String param1, String param2) {
        ConteudoInstitucionalFragment fragment = new ConteudoInstitucionalFragment();

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


        return view;
    }

}
