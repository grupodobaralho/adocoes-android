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
    private ImageView target;
    // Screen touch coordinates
    private int x;
    private int y;
    // Target center
    private float xTargetCenter;
    private float yTargetCenter;
    // Target touch offset;
    private int xDifference;
    private int yDifference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("Child Preference");
        this.preferenceArea = (FrameLayout) findViewById(R.id.preferenceArea);
        this.target = (ImageView) findViewById(R.id.blueHeart);
        xTargetCenter = (float) getResources().getDimension(R.dimen.target_width) / 2;
        yTargetCenter = (float) getResources().getDimension(R.dimen.target_height) / 2;

        this.target.setOnTouchListener(new TargetTouchListener());
    }

    private final class TargetTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent event) {
            int rawX = (int) event.getRawX();
            int rawY = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    break;

                case MotionEvent.ACTION_DOWN:
                    FrameLayout.LayoutParams layoutParamsOnActionDown = (FrameLayout.LayoutParams) view.getLayoutParams();
                    // X and Y represent the click position
                    x = rawX - layoutParamsOnActionDown.leftMargin;
                    y = rawY - layoutParamsOnActionDown.topMargin;

                    // Gets target touch position
                    float xTargetTouch = event.getX();
                    float yTargetTouch = event.getY();

                    // Calculates difference between touch position and center position
                    xDifference = (int) (xTargetTouch - xTargetCenter);
                    yDifference = (int) (yTargetTouch - yTargetCenter);

                case MotionEvent.ACTION_MOVE:
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = rawX + xDifference - x;
                    layoutParams.topMargin = rawY + yDifference - y;

//                    System.out.println("LEFT MARGIN -> " + layoutParams.leftMargin);
//                    System.out.println("TOP MARGIN -> " + layoutParams.topMargin);

                    view.setLayoutParams(layoutParams);
                    break;
            }
            preferenceArea.invalidate();
            return true;
        }
    }
}
