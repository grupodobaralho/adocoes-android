package br.pucrs.ages.adocoes.Funcionalidades.ImagePreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.ViewPagerFragment;
import br.pucrs.ages.adocoes.R;

public class ImagePreviewActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";
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
            final ArrayList<String> midiasIds = intent.getStringArrayListExtra(ViewPagerFragment.ARGUMENT_MIDIAS);
            final int position = intent.getIntExtra(EXTRA_POSITION, -1);
            final int imageResource = intent.getIntExtra(EXTRA_IMAGE, -1);

            photoView = (PhotoView) findViewById(R.id.photo_view);
            photoView.setImageResource(imageResource);
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
