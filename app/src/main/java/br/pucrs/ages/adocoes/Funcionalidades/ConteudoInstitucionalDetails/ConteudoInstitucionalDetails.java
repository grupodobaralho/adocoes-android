package br.pucrs.ages.adocoes.Funcionalidades.ConteudoInstitucionalDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import br.pucrs.ages.adocoes.Model.Conteudo;
import br.pucrs.ages.adocoes.R;

import static br.pucrs.ages.adocoes.Funcionalidades.ListagemConteudoInstitucional.ListagemConteudoInstitucionalFragment.EXTRA;

public class ConteudoInstitucionalDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_institucional_details);

        Intent intent = getIntent();
        if (intent != null) {
            final Conteudo conteudo = (Conteudo) intent.getSerializableExtra(EXTRA);


            final TextView titleTextView = (TextView) findViewById(R.id.tv_title);
            final ImageView imageView = (ImageView) findViewById(R.id.iv_image);
            final TextView conteudoTextView = (TextView) findViewById(R.id.tv_conteudo);

            titleTextView.setText(conteudo.getTitulo());
            conteudoTextView.setText(conteudo.getConteudo());
            // TODO: Set image here!
        }
    }
}
