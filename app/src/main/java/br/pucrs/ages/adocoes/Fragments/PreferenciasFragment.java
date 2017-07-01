package br.pucrs.ages.adocoes.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;


import java.io.FileInputStream;
import java.io.FileOutputStream;

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
        int ordenacaoData;
        rg_lista = (RadioGroup) view.findViewById(R.id.rg_lista);
        rg_lista.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton selected = (RadioButton) group.findViewById(checkedId);
                ordenacaoData = selected.getId();
            }
        });


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Preferências escolhidas: ");


        Button btn_salvarpref = (Button) view.findViewById(R.id.btn_salvarpref);


        btn_salvarpref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((RadioButton) v).isChecked();

                save(v);//Salva dados localmente


                // Check which radio button was clicked
                switch(v.getId()) {
                    case R.id.rb_vertical:
                        if (checked)
                            // vertical
                            builder.setMessage("Você escolheu lista vertical!");
                        break;
                    case R.id.rb_horizontal:
                        if (checked)
                            // horizontal
                            builder.setMessage("Você escolheu lista horizontal!");
                            break;
                }
            }

            public void save (View v){

                String fileName = "teste";

                try {
                    //FileOutputStream file = openFileOutput(fileName, Context.MODE_PRIVATE);
                    FileOutputStream file = getContext().openFileOutput(fileName, getActivity().MODE_PRIVATE);
                    file.write(ordenacaoData.toString().getBytes());
                    file.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        AlertDialog dialog = builder.create();
        dialog.show();

      //  wv.loadUrl();

    }

}
