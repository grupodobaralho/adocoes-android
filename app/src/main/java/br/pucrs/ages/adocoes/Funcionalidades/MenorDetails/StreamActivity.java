package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Model.MenorMidia;
import br.pucrs.ages.adocoes.Model.RefMidia;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StreamActivity extends AppCompatActivity {

    public static final String EXTRA_MIDIA = "EXTRA_MIDIA";
    public static final String EXTRA_MENOR_ID = "EXTRA_MENOR_ID";

    ProgressBar progressBar = null;

    String videoURL = "http://techslides.com/demos/sample-videos/small.mp4";
    Uri vidUri = Uri.parse(videoURL);
    VideoView vidView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        Intent intent = getIntent();
        if(intent != null) {

            final RefMidia midia = intent.getParcelableExtra(EXTRA_MIDIA);
            final String menorId = intent.getStringExtra(EXTRA_MENOR_ID);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            vidView = (VideoView)findViewById(R.id.myVideo);

            // Controlls
            MediaController vidControl = new MediaController(this);
            vidControl.setAnchorView(vidView);
            vidView.setMediaController(vidControl);

            vidView.setVideoURI(vidUri);

            final String token = UserBusiness.getInstance().getAccessToken();
            //Talvez seja preciso fazer um filter em mMidiaIds para pegar apenas as fotos. Provavelmente virão refs de vídeos, cartas, etc, junto no campo midias.
            RestUtil.getMenoresEndPoint().menorMidia(menorId, midia.getId(), token).enqueue(new Callback<MenorMidia>() {
                @Override
                public void onResponse(Call<MenorMidia> call, Response<MenorMidia> response) {
                    MenorMidia midia = response.body();
                    if (midia != null) {
                        System.out.println(midia.getConteudo());
                        videoURL = midia.getConteudo();
                        vidUri = Uri.parse(videoURL);

                        vidView.setVideoURI(vidUri);

                        vidView.start();

                        progressBar.setVisibility(View.VISIBLE);

                        vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                // TODO Auto-generated method stub
                                mp.start();
                                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                    @Override
                                    public void onVideoSizeChanged(MediaPlayer mp, int arg1,
                                                                   int arg2) {
                                        // TODO Auto-generated method stub
                                        progressBar.setVisibility(View.GONE);
                                        mp.start();
                                    }
                                });
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<MenorMidia> call, Throwable t) {

                }
            });
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
