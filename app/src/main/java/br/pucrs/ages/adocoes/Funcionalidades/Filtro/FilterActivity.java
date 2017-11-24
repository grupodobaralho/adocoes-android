package br.pucrs.ages.adocoes.Funcionalidades.Filtro;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;


public class FilterActivity extends AppCompatActivity {

    private FrameLayout preferenceArea;
    private ImageView target;
    private Button button;

    private List<Menor> menores;

    // Target center
    private float xTargetCenter;
    private float yTargetCenter;

    // Target touch offset
    // These are properties to avoid recalculation everytime the user' touch position moves a pixel
    private float xDifference;
    private float yDifference;

    private float actionBarHeight;
    private float statusBarHeight;

    // Default algorithm inputs
    // -1 means a touch event has no occured yet, see getIdadePonto() and getSexoPonto()
    private double targetX = -1;
    private double targetY = -1;

    // Base algorithm measures
    private final double minSexo = 0.0;     // related to X
    private final double maxSexo = 1.0;     // related to X
    private final double minIdade = 0.0;    // related to Y
    private final double maxIdade = 18.0;   // related to Y

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("Adoção");

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        this.preferenceArea = (FrameLayout) findViewById(R.id.preferenceArea);
        this.target = (ImageView) findViewById(R.id.blueHeart);
        this.button = (Button) findViewById(R.id.filtrar);

        xTargetCenter = getResources().getDimensionPixelSize(R.dimen.target_width) / 2;
        yTargetCenter = getResources().getDimensionPixelSize(R.dimen.target_height) / 2;

        actionBarHeight = getActionBarHeight();
        statusBarHeight = getStatusBarHeight();

        this.target.setOnTouchListener(new TargetTouchListener());
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double pontoIdade = getPontoIdade();
                double pontoSexo = getPontoSexo();
                System.out.println(pontoIdade);
                System.out.println(pontoSexo);

                setResult(RESULT_OK);
                finish();
            }
        });

        final float sexoCoordinate = UserBusiness.getInstance().getTargetCoordinateX();
        final float idadeCoordinate = UserBusiness.getInstance().getTargetCoordinateY();

        if (sexoCoordinate != -1 && idadeCoordinate != -1) {
            target.setX(sexoCoordinate);
            target.setY(idadeCoordinate);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private final class TargetTouchListener implements View.OnTouchListener {
        float coordinateX = 0;
        float coordinateY = 0;
        public boolean onTouch(View view, MotionEvent event) {
            // Gets raw screen touch position
            int rawX = (int) event.getRawX();
            int rawY = (int) event.getRawY();

            System.out.println(rawX);
            System.out.println(rawY);

            // Gets permited movable area
            // This is done here because the preference area measures are not available in onCreate method
            float preferenceAreaHeight = preferenceArea.getHeight();
            float preferenceAreaWidth = preferenceArea.getWidth();



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
                    float xWithParentOffset = rawX - preferenceArea.getX();
                    float yWithParentOffset = rawY - preferenceArea.getY() - actionBarHeight - statusBarHeight;

                    // These 4 "ifs" make sure the target's coordinates stay inside the allowed area
                    if (xWithParentOffset > preferenceAreaWidth) {
                        xWithParentOffset = preferenceAreaWidth;
                    }
                    if (xWithParentOffset < 0) {
                        xWithParentOffset = 0;
                    }
                    if (yWithParentOffset > preferenceAreaHeight) {
                        yWithParentOffset = preferenceAreaHeight;
                    }
                    if (yWithParentOffset < 0) {
                        yWithParentOffset = 0;
                    }

                    // This will make the target snap to its center when touched
                    float xSnapedToCenter = xWithParentOffset - xTargetCenter;
                    float ySnapedToCenter = yWithParentOffset - yTargetCenter;

                    // Sets target's new coordinates
                    target.setX(xSnapedToCenter);
                    target.setY(ySnapedToCenter);
                    targetX = xWithParentOffset;
                    targetY = yWithParentOffset;
                    coordinateX = xSnapedToCenter - preferenceAreaWidth / 2 + xTargetCenter;
                    coordinateY = ySnapedToCenter - preferenceAreaHeight / 2 + yTargetCenter;

                    break;
                case MotionEvent.ACTION_UP:
                    UserBusiness.getInstance().setTargetCoordinateX(coordinateX);
                    UserBusiness.getInstance().setPontoSexo((float)getPontoSexo());
                    UserBusiness.getInstance().setTargetCoordinateY(coordinateY);
                    UserBusiness.getInstance().setPontoIdade((float)getPontoIdade());
                    break;
                default:
                    break;
            }
            preferenceArea.invalidate();
            return true;
        }
    }

    // Methods to get filter algorithm input

    public double getPontoSexo() {
        if (targetX == -1) return maxSexo / 2;
        float preferenceAreaWidth = preferenceArea.getWidth();
        double sexoPercentage = targetX / preferenceAreaWidth;
        return maxSexo * sexoPercentage;
    }

    public double getPontoIdade() {
        if (targetY == -1) return maxIdade / 2;
        float preferenceAreaHeight = preferenceArea.getHeight();
        double flippedTargetY = Math.abs(targetY - preferenceAreaHeight);
        double idadePercentage = flippedTargetY / preferenceAreaHeight;
        return maxIdade * idadePercentage;
    }

    public void setCoordinate(double pontoSexo, double pontoIdade) {
        float widthMultiplier = (float)(pontoSexo / maxSexo);
        float heightMultiplier = (float)(pontoIdade / maxIdade);

        target.setX(preferenceArea.getWidth() * widthMultiplier);
        target.setY(preferenceArea.getHeight() * heightMultiplier);
    }

    // Utility methods

    private float getStatusBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private float getActionBarHeight() {
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
    private static float convertDpToPixel(float dp, Context context){
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
    private static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
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
