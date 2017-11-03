package br.pucrs.ages.adocoes.Funcionalidades.ImagePreview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Model.MenorMidia;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagePreviewActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    public static final String EXTRA_MIDIA = "EXTRA_MIDIA";
    public static final String EXTRA_MENOR_ID = "EXTRA_MENOR_ID";
    private PhotoView photoView;
    private boolean isShowingSystemUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        if (intent != null) {

            final String midiaId = intent.getStringExtra(EXTRA_MIDIA);
            final String menorId = intent.getStringExtra(EXTRA_MENOR_ID);

            photoView = (PhotoView) findViewById(R.id.photo_view);
            photoView.setImageResource(R.drawable.boy);

            final String token = UserBusiness.getInstance().getAccessToken();
            //Talvez seja preciso fazer um filter em mMidiaIds para pegar apenas as fotos. Provavelmente virão refs de vídeos, cartas, etc, junto no campo midias.
            RestUtil.getMenoresEndPoint().menorMidia(menorId, midiaId, token).enqueue(new Callback<MenorMidia>() {
                @Override
                public void onResponse(Call<MenorMidia> call, Response<MenorMidia> response) {
                    MenorMidia midia = response.body();
                    if (midia != null) {
                        byte[] imageAsBytes = Base64.decode(midia.getConteudo().getBytes(), Base64.DEFAULT);
                        Bitmap imgBitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                        photoView.setImageBitmap(imgBitmap);
                    }
                }

                @Override
                public void onFailure(Call<MenorMidia> call, Throwable t) {

                }
            });
        }

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowingSystemUI) {
                    hideSystemUI();
                } else {
                    showSystemUI();
                }
            }
        });
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideSystemUI();
        }
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        photoView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        isShowingSystemUI = false;
    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        photoView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        isShowingSystemUI = true;
    }
}
