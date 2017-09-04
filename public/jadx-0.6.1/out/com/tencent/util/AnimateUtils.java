package com.tencent.util;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public final class AnimateUtils {
    private static float END_TENSION = (1.0f - START_TENSION);
    private static final int NB_SAMPLES = 100;
    private static final float[] SPLINE = new float[101];
    private static float START_TENSION = 0.4f;
    private static float sViscousFluidNormalize;
    private static float sViscousFluidScale = 8.0f;

    public static class AnimationAdapter implements AnimationListener {
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    static {
        float f = 0.0f;
        int i = 0;
        while (i <= 100) {
            float f2;
            float f3 = ((float) i) / 100.0f;
            float f4 = 1.0f;
            float f5 = f;
            while (true) {
                f = ((f4 - f5) / 2.0f) + f5;
                f2 = (3.0f * f) * (1.0f - f);
                float f6 = ((((1.0f - f) * START_TENSION) + (END_TENSION * f)) * f2) + ((f * f) * f);
                if (((double) Math.abs(f6 - f3)) < 1.0E-5d) {
                    break;
                } else if (f6 > f3) {
                    f4 = f;
                } else {
                    f5 = f;
                }
            }
            SPLINE[i] = ((f * f) * f) + f2;
            i++;
            f = f5;
        }
        SPLINE[100] = 1.0f;
        sViscousFluidNormalize = 1.0f;
        sViscousFluidNormalize = 1.0f / viscousFluid(1.0f);
    }

    private AnimateUtils() {
    }

    public static float viscousFluid(float f) {
        float f2 = sViscousFluidScale * f;
        if (f2 < 1.0f) {
            f2 -= 1.0f - ((float) Math.exp((double) (-f2)));
        } else {
            f2 = ((1.0f - ((float) Math.exp((double) (1.0f - f2)))) * (1.0f - 0.36787945f)) + 0.36787945f;
        }
        return f2 * sViscousFluidNormalize;
    }
}
