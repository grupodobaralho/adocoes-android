package br.pucrs.ages.adocoes.Funcionalidades.Filtro;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import br.pucrs.ages.adocoes.R;


public class FilterActivity extends AppCompatActivity {

    private FrameLayout preferenceArea;
    private ImageView target;

    // Target center
    private float xTargetCenter;
    private float yTargetCenter;

    // Target touch offset
    // These are properties to avoid recalculation everytime the user' touch position moves a pixel
    private float xDifference;
    private float yDifference;

    private float actionBarHeight;
    private float statusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("Child Preference");

        this.preferenceArea = (FrameLayout) findViewById(R.id.preferenceArea);
        this.target = (ImageView) findViewById(R.id.blueHeart);

        xTargetCenter = getResources().getDimensionPixelSize(R.dimen.target_width) / 2;
        yTargetCenter = getResources().getDimensionPixelSize(R.dimen.target_height) / 2;

        actionBarHeight = getActionBarHeight();
        statusBarHeight = getStatusBarHeight();

        this.target.setOnTouchListener(new TargetTouchListener());
    }


    private final class TargetTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent event) {
            // Gets raw screen touch position
            int rawX = (int) event.getRawX();
            int rawY = (int) event.getRawY();

            // Gets permited movable area in dp
            float preferenceAreaHeight = preferenceArea.getHeight();
            float preferenceAreaWidth = preferenceArea.getWidth();

            System.out.println("Screen Height: " + preferenceAreaHeight);
            System.out.println("Screen Width:" + preferenceAreaWidth);

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    // Gets target touch position
                    float xTargetTouch = event.getX();
                    float yTargetTouch = event.getY();

                    // Calculates difference between touch position and center position
                    xDifference = xTargetTouch - xTargetCenter;
                    yDifference = yTargetTouch - yTargetCenter;

                case MotionEvent.ACTION_MOVE:
                    // Values that represent the target's center
                    float xWithParenteOffset = rawX - preferenceArea.getX();
                    float yWithParenteOffset = rawY - preferenceArea.getY() - actionBarHeight - statusBarHeight;

                    // These 4 "ifs" make sure the target's coordinates stay inside the allowed area
                    if (xWithParenteOffset > preferenceAreaWidth) {
                        xWithParenteOffset = preferenceAreaWidth;
                    }
                    if (xWithParenteOffset < 0) {
                        xWithParenteOffset = 0;
                    }
                    if (yWithParenteOffset > preferenceAreaHeight) {
                        yWithParenteOffset = preferenceAreaHeight;
                    }
                    if (yWithParenteOffset < 0) {
                        yWithParenteOffset = 0;
                    }

                    // This will make the target snap to its center when touched
                    float xSnapedToCenter = xWithParenteOffset - xTargetCenter;
                    float ySnapedToCenter = yWithParenteOffset - yTargetCenter;

                    // Sets target's new coordinates
                    target.setX(xSnapedToCenter);
                    target.setY(ySnapedToCenter);

//                    System.out.println("X: " + xWithParenteOffset);
//                    System.out.println("Y: " + yWithParenteOffset);
//                    System.out.println("Snap with Offset X: " + xSnapedToCenter);
//                    System.out.println("Snap with Offset Y: " + ySnapedToCenter);

                    break;

                default:
                    break;
            }
            preferenceArea.invalidate();
            return true;
        }
    }

    // Utility methods

    public float getStatusBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public float getActionBarHeight() {
        TypedValue tv = new TypedValue();
        float actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
