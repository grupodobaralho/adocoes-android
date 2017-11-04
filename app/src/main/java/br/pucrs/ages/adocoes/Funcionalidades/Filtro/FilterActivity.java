package br.pucrs.ages.adocoes.Funcionalidades.Filtro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
    // Target touch offset
    // These are properties to avoid recalculation everytime the user' touch position moves a pixel
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
            // Gets raw screen touch position
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
                    FrameLayout.LayoutParams targetLayoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    final ViewGroup.LayoutParams preferenceAreaLayoutParams = preferenceArea.getLayoutParams();

                    // Gets screen dimensions
                    // TODO FIX WIDTH AND HEIGHT, RETURNING 1
                    int screenWidth = preferenceAreaLayoutParams.width;
                    int screenHeight = preferenceAreaLayoutParams.height;

                    // We create the following properties because the screen's anchor point is (0.5, 0.5)
                    int screenMaxWidth = screenWidth / 2;
                    int screenMinWidth = screenWidth / -2;
                    int screenMaxHeight = screenHeight / 2;
                    int screenMinHeight = screenHeight / -2;

                    // New margin target position
                    int newTargetLeftMargin = rawX + xDifference - x;
                    int newTargetTopMargin = rawY + yDifference - y;

                    System.out.println("Screen Height -> " + screenHeight);
                    System.out.println("Screen Width -> " + screenWidth);

                    System.out.println("LEFT MARGIN -> " + newTargetLeftMargin);
                    System.out.println("TOP MARGIN -> " + newTargetTopMargin);

                    // Sets new target position if it's inside the allowed view
                    if (newTargetLeftMargin >= screenMinWidth && newTargetLeftMargin <= screenMaxWidth && newTargetTopMargin >= screenMinHeight && newTargetTopMargin <= screenMaxHeight) {
                        targetLayoutParams.leftMargin = rawX + xDifference - x;
                        targetLayoutParams.topMargin = rawY + yDifference - y;
                    }

                    view.setLayoutParams(targetLayoutParams);
                    break;
            }
            preferenceArea.invalidate();
            return true;
        }
    }
}
