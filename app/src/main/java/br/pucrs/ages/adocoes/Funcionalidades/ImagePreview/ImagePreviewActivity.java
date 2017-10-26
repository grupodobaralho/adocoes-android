package br.pucrs.ages.adocoes.Funcionalidades.ImagePreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.ViewPagerFragment;
import br.pucrs.ages.adocoes.R;

public class ImagePreviewActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "EXTRA_POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        Intent intent = getIntent();
        if (intent != null) {
            final ArrayList<String> midiasIds = intent.getStringArrayListExtra(ViewPagerFragment.ARGUMENT_MIDIAS);
            final int position = intent.getIntExtra(EXTRA_POSITION, -1);

            final PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
            photoView.setBi
        }
    }
}
