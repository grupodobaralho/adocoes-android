package br.pucrs.ages.adocoes.Fragments;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.io.File;


import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 26/05/17.
 */

public class ContentFragment extends Fragment {

    public ContentFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView wv = (WebView) view.findViewById(R.id.wv_termos);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        final Button btnTermos = (Button) (view.findViewById(R.id.btn_termos));
        btnTermos.setEnabled(false);

        RadioGroup group = (RadioGroup) view.findViewById(R.id.rg_termos);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton selected = (RadioButton) group.findViewById(checkedId);
                if(selected.getText().equals("Aceito")){
                    btnTermos.setEnabled(true);
                }
                else {
                    btnTermos.setEnabled(false);
                }
            }
        });

        //Aqui deve ser chamado o conteudo html com o texto de termos de uso:
        //wv.loadUrl();


    }
}
