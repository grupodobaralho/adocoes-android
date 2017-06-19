package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;


import br.pucrs.ages.adocoes.R;

public class PreferenciasFragment extends Fragment {


    private RadioGroup rg_lista;


    public PreferenciasFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preferencias, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rg_lista = (RadioGroup) view.findViewById(R.id.rg_lista);

        Button btn_salvarpref = (Button) view.findViewById(R.id.btn_salvarpref);
     //   final TextView textView = (TextView) view.findViewById(R.id.teste1tv);

        btn_salvarpref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((RadioButton) v).isChecked();

                // Check which radio button was clicked
                switch(v.getId()) {
                    case R.id.rb_vertical:
                        if (checked)
                            // vertical
                            break;
                    case R.id.rb_horizontal:
                        if (checked)
                            // horizontal
                            break;
                }
            }
        });


        //rg_lista.setVisibility(View.VISIBLE);


    }

}
