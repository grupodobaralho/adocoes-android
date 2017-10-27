package br.pucrs.ages.adocoes.Funcionalidades.TermosDeUso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.pucrs.ages.adocoes.MainActivity;
import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 26/05/17.
 */

public class TermosFragment extends Fragment {

    public TermosFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_termos, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView wv = (WebView) view.findViewById(R.id.wv_termos);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        final Button btnTermos = (Button) (view.findViewById(R.id.btn_termos));
        btnTermos.setEnabled(false);
        btnTermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnTermos.setEnabled(true);
                } else {
                    btnTermos.setEnabled(false);
                }
            }
        });


        wv.loadUrl("file:///android_asset/termosDeUso.html");



    }
}
