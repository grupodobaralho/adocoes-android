package br.pucrs.ages.adocoes.Fragments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
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

        WebView wv = (WebView) view.findViewById(R.id.wv_trocaapi);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Preferências escolhidas: ");


        Button btn_salvarpref = (Button) view.findViewById(R.id.btn_salvarpref);


        btn_salvarpref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean checked = ((RadioButton) v).isChecked();

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

        });

        AlertDialog dialog = builder.create();
        dialog.show();

      //  wv.loadUrl();

    }

}
