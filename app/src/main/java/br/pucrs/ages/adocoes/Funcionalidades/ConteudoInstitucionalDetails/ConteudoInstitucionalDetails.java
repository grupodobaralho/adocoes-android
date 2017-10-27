package br.pucrs.ages.adocoes.Funcionalidades.ConteudoInstitucionalDetails;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.pucrs.ages.adocoes.Funcionalidades.ImagePreview.ImagePreviewActivity;
import br.pucrs.ages.adocoes.Model.Conteudo;
import br.pucrs.ages.adocoes.R;

import static br.pucrs.ages.adocoes.Funcionalidades.ListagemConteudoInstitucional.ListagemConteudoInstitucionalFragment.EXTRA;

public class ConteudoInstitucionalDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_institucional_details);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (intent != null) {
            final Conteudo conteudo = (Conteudo) intent.getSerializableExtra(EXTRA);


            final TextView titleTextView = (TextView) findViewById(R.id.tv_title);
            final ImageView imageView = (ImageView) findViewById(R.id.iv_image);
            final TextView conteudoTextView = (TextView) findViewById(R.id.tv_conteudo);

            titleTextView.setText(conteudo.getTitulo());
            conteudoTextView.setText(conteudo.getConteudo());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ConteudoInstitucionalDetails.this, ImagePreviewActivity.class);
                    intent.putExtra(ImagePreviewActivity.EXTRA_IMAGE, R.drawable.imagemconteudo1);
                    startActivity(intent);
                }
            });
            // TODO: Set image here!
            Drawable drawable = getResources().getDrawable(R.drawable.imagemconteudo1, null);
            imageView.setImageDrawable(drawable);

        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            this.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
