package br.pucrs.ages.adocoes.MenorDetails;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.pucrs.ages.adocoes.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class PagerAdapterFragment extends Fragment {


    public PagerAdapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_adapter, container, false);
    }

}
