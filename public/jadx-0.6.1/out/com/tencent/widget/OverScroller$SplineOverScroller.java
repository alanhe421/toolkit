package com.tencent.widget;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import com.tencent.util.AnimateUtils;

class OverScroller$SplineOverScroller {
    private static final int BOUNCE = 3;
    private static final int BOUNCE_DURANTION = 400;
    private static final int CUBIC = 1;
    private static float DECELERATION_RATE = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    private static final float END_TENSION = 1.0f;
    private static final float GRAVITY = 2000.0f;
    private static final float INFLEXION = 0.35f;
    private static final int NB_SAMPLES = 100;
    private static final float P1 = 0.175f;
    private static final float P2 = 0.35000002f;
    private static float PHYSICAL_COEF = 0.0f;
    private static final int SCROLL = 4;
    private static final int SPLINE = 0;
    private static final float[] SPLINE_POSITION = new float[101];
    private static final float[] SPLINE_TIME = new float[101];
    private static final float START_TENSION = 0.5f;
    private float mCurrVelocity;
    private int mCurrentPosition;
    private float mDeceleration;
    private int mDuration;
    private int mFinal;
    private boolean mFinished = true;
    private float mFlingFriction = ViewConfiguration.getScrollFriction();
    private long mLastTime;
    private double mLastVelocity;
    private int mOver;
    private int mSplineDistance;
    private int mSplineDuration;
    private int mStart;
    private long mStartTime;
    private int mState = 0;
    private float mTension;
    private int mVelocity;

    static {
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3;
            float f4 = ((float) i) / 100.0f;
            float f5 = END_TENSION;
            float f6 = f2;
            while (true) {
                f2 = ((f5 - f6) / 2.0f) + f6;
                f3 = (3.0f * f2) * (END_TENSION - f2);
                float f7 = ((((END_TENSION - f2) * P1) + (P2 * f2)) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f7 - f4)) < 1.0E-5d) {
                    break;
                } else if (f7 > f4) {
                    f5 = f2;
                } else {
                    f6 = f2;
                }
            }
            SPLINE_POSITION[i] = (f2 * (f2 * f2)) + (f3 * (((END_TENSION - f2) * START_TENSION) + f2));
            f5 = END_TENSION;
            while (true) {
                f2 = ((f5 - f) / 2.0f) + f;
                f3 = (3.0f * f2) * (END_TENSION - f2);
                f7 = ((((END_TENSION - f2) * START_TENSION) + f2) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f7 - f4)) < 1.0E-5d) {
                    break;
                } else if (f7 > f4) {
                    f5 = f2;
                } else {
                    f = f2;
                }
            }
            SPLINE_TIME[i] = (f2 * (f2 * f2)) + ((((END_TENSION - f2) * P1) + (P2 * f2)) * f3);
            i++;
            f2 = f6;
        }
        float[] fArr = SPLINE_POSITION;
        SPLINE_TIME[100] = END_TENSION;
        fArr[100] = END_TENSION;
    }

    static void initFromContext(Context context) {
        PHYSICAL_COEF = ((context.getResources().getDisplayMetrics().density * 160.0f) * 386.0878f) * 0.84f;
    }

    void setFriction(float f) {
        this.mFlingFriction = f;
    }

    OverScroller$SplineOverScroller() {
    }

    void updateScroll(float f) {
        this.mCurrentPosition = this.mStart + Math.round(((float) (this.mFinal - this.mStart)) * f);
    }

    private void adjustDuration(int i, int i2, int i3) {
        float abs = Math.abs(((float) (i3 - i)) / ((float) (i2 - i)));
        int i4 = (int) (100.0f * abs);
        if (i4 < 100) {
            float f = ((float) i4) / 100.0f;
            float f2 = ((float) (i4 + 1)) / 100.0f;
            float f3 = SPLINE_TIME[i4];
            this.mDuration = (int) (((((abs - f) / (f2 - f)) * (SPLINE_TIME[i4 + 1] - f3)) + f3) * ((float) this.mDuration));
        }
    }

    void startScroll(int i, int i2, int i3) {
        this.mFinished = false;
        this.mStart = i;
        this.mFinal = i + i2;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mDuration = i3;
        this.mDeceleration = 0.0f;
        this.mVelocity = 0;
        this.mState = 4;
    }

    void finish() {
        this.mCurrentPosition = this.mFinal;
        this.mFinished = true;
    }

    void setFinalPosition(int i) {
        this.mFinal = i;
        this.mFinished = false;
    }

    void extendDuration(int i) {
        this.mDuration = ((int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime)) + i;
        this.mFinished = false;
    }

    boolean springback(int i, int i2, int i3) {
        this.mFinished = true;
        this.mFinal = i;
        this.mStart = i;
        this.mVelocity = 0;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mDuration = 0;
        if (i < i2) {
            startSpringback(i, i2, 0);
        } else if (i > i3) {
            startSpringback(i, i3, 0);
        }
        if (this.mFinished) {
            return false;
        }
        return true;
    }

    private void startSpringback(int i, int i2, int i3) {
        this.mFinished = false;
        this.mState = 1;
        this.mStart = i;
        this.mFinal = i2;
        this.mOver = i2 - i;
        this.mDuration = 400;
    }

    void fling(int i, int i2, int i3, int i4, int i5) {
        this.mOver = i5;
        this.mFinished = false;
        this.mVelocity = i2;
        this.mCurrVelocity = (float) i2;
        this.mSplineDuration = 0;
        this.mDuration = 0;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStart = i;
        this.mCurrentPosition = i;
        if (i > i4 || i < i3) {
            if (i <= i4) {
                i4 = i3;
            }
            startSpringback(i, i4, i2);
            return;
        }
        this.mState = 0;
        double d = 0.0d;
        if (i2 != 0) {
            int splineFlingDuration = getSplineFlingDuration(i2);
            this.mSplineDuration = splineFlingDuration;
            this.mDuration = splineFlingDuration;
            this.mDeceleration = (float) (getSplineDeceleration(i2) * 1000.0d);
            d = getSplineFlingDistance(i2);
        }
        this.mSplineDistance = (int) (d * ((double) Math.signum((float) i2)));
        this.mFinal = this.mSplineDistance + i;
        if (this.mFinal < i3) {
            adjustDuration(this.mStart, this.mFinal, i3);
            this.mFinal = i3;
        }
        if (this.mFinal > i4) {
            adjustDuration(this.mStart, this.mFinal, i4);
            this.mFinal = i4;
        }
        this.mLastVelocity = (double) i2;
        this.mLastTime = this.mStartTime;
    }

    private double getSplineDeceleration(int i) {
        return Math.log((double) ((INFLEXION * ((float) Math.abs(i))) / (this.mFlingFriction * PHYSICAL_COEF)));
    }

    private double getSplineFlingDistance(int i) {
        return Math.exp(getSplineDeceleration(i) * (((double) DECELERATION_RATE) / (((double) DECELERATION_RATE) - 1.0d))) * ((double) (this.mFlingFriction * PHYSICAL_COEF));
    }

    private int getSplineFlingDuration(int i) {
        return (int) (Math.exp(getSplineDeceleration(i) / (((double) DECELERATION_RATE) - 1.0d)) * 1000.0d);
    }

    void bounce(int i, int i2, int i3, int i4) {
        float f = (0.09606f * ((float) i2)) / (1.0000001E-5f * ((float) i4));
        float f2 = i3 > 0 ? 10.0f : -10.0f;
        if (((float) Math.abs(i3)) < f) {
            f2 = (((float) i3) * 10.0f) / f;
        }
        this.mStart = i;
        this.mFinal = i;
        this.mVelocity = i3;
        this.mOver = i2;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mDuration = i4;
        this.mTension = f2;
        this.mCurrentPosition = this.mStart;
        this.mState = 3;
    }

    void notifyEdgeReached(int i, int i2, int i3) {
        if (this.mState == 0) {
            if (i == i2) {
                bounce(i2, i3, this.mVelocity, 400);
                return;
            }
            adjustDuration(this.mStart, this.mFinal, this.mCurrentPosition - (i - i2));
            this.mOver = i3;
            this.mFinal = i2;
            onEdgeReached();
        } else if (this.mState == 4) {
            this.mCurrentPosition = 0;
            this.mFinal = 0;
            this.mFinished = true;
        }
    }

    private void onEdgeReached() {
        long j = ((long) this.mDuration) + this.mStartTime;
        int i = (int) ((((float) this.mDuration) / ((float) this.mSplineDuration)) * 100.0f);
        float f = 0.0f;
        if (i < 100) {
            f = ((float) i) / 100.0f;
            float f2 = ((float) (i + 1)) / 100.0f;
            f = (SPLINE_POSITION[i + 1] - SPLINE_POSITION[i]) / (f2 - f);
        }
        this.mCurrVelocity = ((f * ((float) this.mSplineDistance)) / ((float) this.mSplineDuration)) * 1000.0f;
        this.mDeceleration = (float) (((((double) this.mCurrVelocity) - this.mLastVelocity) / ((double) (j - this.mLastTime))) * 1000.0d);
        bounce(this.mFinal, this.mOver, (int) this.mCurrVelocity, 400);
        this.mStartTime = j;
        update();
    }

    boolean continueWhenFinished() {
        switch (this.mState) {
            case 0:
                if (this.mDuration < this.mSplineDuration) {
                    onEdgeReached();
                    break;
                }
                return false;
            case 1:
            case 3:
                return false;
        }
        update();
        return true;
    }

    boolean update() {
        float f = END_TENSION;
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        long j = currentAnimationTimeMillis - this.mStartTime;
        if (j > ((long) this.mDuration)) {
            return false;
        }
        double d;
        float f2;
        switch (this.mState) {
            case 0:
                float f3 = ((float) j) / ((float) this.mSplineDuration);
                int i = (int) (100.0f * f3);
                f2 = 0.0f;
                if (i < 100) {
                    f = ((float) i) / 100.0f;
                    f2 = ((float) (i + 1)) / 100.0f;
                    float f4 = SPLINE_POSITION[i];
                    f2 = (SPLINE_POSITION[i + 1] - f4) / (f2 - f);
                    f = ((f3 - f) * f2) + f4;
                }
                double d2 = (double) (f * ((float) this.mSplineDistance));
                this.mCurrVelocity = ((f2 * ((float) this.mSplineDistance)) / ((float) this.mSplineDuration)) * 1000.0f;
                this.mDeceleration = (float) (((((double) this.mCurrVelocity) - this.mLastVelocity) / ((double) (currentAnimationTimeMillis - this.mLastTime))) * 1000.0d);
                d = d2;
                break;
            case 1:
                d = (double) (AnimateUtils.viscousFluid(((float) j) / ((float) this.mDuration)) * ((float) this.mOver));
                break;
            case 3:
                f2 = (((float) j) / ((float) this.mDuration)) - END_TENSION;
                d = (double) (((f2 * this.mTension) + this.mTension) * ((((((float) this.mOver) * f2) * f2) * f2) * f2));
                break;
            default:
                d = 0.0d;
                break;
        }
        this.mCurrentPosition = ((int) Math.round(d)) + this.mStart;
        return true;
    }
}
