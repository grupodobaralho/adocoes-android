package br.pucrs.ages.adocoes.Funcionalidades.ImagePreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.ViewPagerFragment;
import br.pucrs.ages.adocoes.R;

public class ImagePreviewActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "EXTRA_POSITION";
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";

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

            final PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
            photoView.setImageResource(imageResource);
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
