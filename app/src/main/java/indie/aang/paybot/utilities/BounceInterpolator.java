package indie.aang.paybot.utilities;

import android.view.animation.Interpolator;

/**
 * Created by aangjnr on 01/10/2017.
 */

public class BounceInterpolator implements Interpolator {


    private double mAmplitide = 1;
    private double mFrequency = 10;


    public BounceInterpolator(double amplitide, double frequency){
        mAmplitide = amplitide;
        mFrequency = frequency;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (-1 * Math.pow(Math.E, - input/mAmplitide) * Math.cos(mFrequency * input) + 1);
    }
}
