package br.pucrs.ages.adocoes.Funcionalidades.Filtro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import br.pucrs.ages.adocoes.R;

public class FilterActivity extends AppCompatActivity {

    private FrameLayout preferenceArea;
    private ImageView blueHeart;
    private int x;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("Child Preference");
        this.preferenceArea = (FrameLayout) findViewById(R.id.preferenceArea);
        this.blueHeart = (ImageView) findViewById(R.id.blueHeart);
        this.blueHeart.setOnTouchListener(new BlueHeartTouchListener2());
    }

    private final class BlueHeartTouchListener2 implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent event) {
            int rawX = (int) event.getRawX();
            int rawY = (int) event.getRawY();
//            System.out.println("RAW X -> " + rawX + "\nRAW Y -> " + rawY);
            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    FrameLayout.LayoutParams layoutParamsOnActionDown = (FrameLayout.LayoutParams) view.getLayoutParams();
                    System.out.println("LAYOUT PARAMS: Left Margin " + layoutParamsOnActionDown.leftMargin);
                    System.out.println("LAYOUT PARAMS: Right Margin " + layoutParamsOnActionDown.rightMargin);
                    System.out.println("LAYOUT PARAMS: Top Margin " + layoutParamsOnActionDown.topMargin);
                    System.out.println("LAYOUT PARAMS: Bottom Margin " + layoutParamsOnActionDown.bottomMargin);
                    System.out.println("LAYOUT PARAMS: Width" + layoutParamsOnActionDown.width);
                    System.out.println("LAYOUT PARAMS: Height" + layoutParamsOnActionDown.height);
                    x = rawX - layoutParamsOnActionDown.leftMargin;
                    y = rawY - layoutParamsOnActionDown.topMargin;
                    // X and Y represent the click position
                    break;

                case MotionEvent.ACTION_UP:
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    break;

                case MotionEvent.ACTION_MOVE:
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = rawX - x;
                    layoutParams.topMargin = rawY - y;
                    view.setLayoutParams(layoutParams);
                    System.out.println("Raw X: " + rawX + "  Raw Y: " + rawY);
                    System.out.println("X: " + x + "  Y: " + y);
                    break;
            }
            preferenceArea.invalidate();
            return true;
        }
    }
}
