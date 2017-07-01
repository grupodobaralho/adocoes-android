package br.pucrs.ages.adocoes.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.pucrs.ages.adocoes.R;

/**
 * Created by 16204012 on 24/06/2017.
 */
public class SobreFragment extends Fragment{

    public SobreFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_sobre, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iv = (ImageView) view.findViewById(R.id.logo_image_sobre);

        TextView tv = (TextView) view.findViewById(R.id.projeto_adocoes_text_view_sobre);

        WebView wv = (WebView) view.findViewById(R.id.wv_sobre);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);



    }
}

