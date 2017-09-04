package com.tencent.widget;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.tencent.util.AnimateUtils;

public class OverScroller {
    private static final int DEFAULT_DURATION = 250;
    private static final int FLING_MODE = 1;
    private static final int SCROLL_MODE = 0;
    private final boolean mFlywheel;
    private final Interpolator mInterpolator;
    private int mMode;
    private final SplineOverScroller mScrollerX;
    private final SplineOverScroller mScrollerY;

    public OverScroller(Context context) {
        this(context, null);
    }

    public OverScroller(Context context, Interpolator interpolator) {
        this(context, interpolator, true);
    }

    public OverScroller(Context context, Interpolator interpolator, boolean z) {
        this.mInterpolator = interpolator;
        this.mFlywheel = z;
        this.mScrollerX = new SplineOverScroller();
        this.mScrollerY = new SplineOverScroller();
        SplineOverScroller.initFromContext(context);
    }

    public OverScroller(Context context, Interpolator interpolator, float f, float f2) {
        this(context, interpolator, true);
    }

    public OverScroller(Context context, Interpolator interpolator, float f, float f2, boolean z) {
        this(context, interpolator, z);
    }

    public final void setFriction(float f) {
        this.mScrollerX.setFriction(f);
        this.mScrollerY.setFriction(f);
    }

    public final boolean isFinished() {
        return SplineOverScroller.access$000(this.mScrollerX) && SplineOverScroller.access$000(this.mScrollerY);
    }

    public final void forceFinished(boolean z) {
        SplineOverScroller.access$002(this.mScrollerX, SplineOverScroller.access$002(this.mScrollerY, z));
    }

    public final int getCurrX() {
        return SplineOverScroller.access$100(this.mScrollerX);
    }

    public final int getCurrY() {
        return SplineOverScroller.access$100(this.mScrollerY);
    }

    public float getCurrVelocity() {
        return (float) Math.sqrt((double) ((SplineOverScroller.access$200(this.mScrollerX) * SplineOverScroller.access$200(this.mScrollerX)) + (SplineOverScroller.access$200(this.mScrollerY) * SplineOverScroller.access$200(this.mScrollerY))));
    }

    public final int getStartX() {
        return SplineOverScroller.access$300(this.mScrollerX);
    }

    public final int getStartY() {
        return SplineOverScroller.access$300(this.mScrollerY);
    }

    public final int getFinalX() {
        return SplineOverScroller.access$400(this.mScrollerX);
    }

    public final int getFinalY() {
        return SplineOverScroller.access$400(this.mScrollerY);
    }

    @Deprecated
    public final int getDuration() {
        return Math.max(SplineOverScroller.access$500(this.mScrollerX), SplineOverScroller.access$500(this.mScrollerY));
    }

    @Deprecated
    public void extendDuration(int i) {
        this.mScrollerX.extendDuration(i);
        this.mScrollerY.extendDuration(i);
    }

    @Deprecated
    public void setFinalX(int i) {
        this.mScrollerX.setFinalPosition(i);
    }

    @Deprecated
    public void setFinalY(int i) {
        this.mScrollerY.setFinalPosition(i);
    }

    public boolean computeScrollOffset() {
        if (isFinished()) {
            return false;
        }
        switch (this.mMode) {
            case 0:
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - SplineOverScroller.access$600(this.mScrollerX);
                int access$500 = SplineOverScroller.access$500(this.mScrollerX);
                if (currentAnimationTimeMillis >= ((long) access$500)) {
                    abortAnimation();
                    break;
                }
                float f = ((float) currentAnimationTimeMillis) / ((float) access$500);
                if (this.mInterpolator == null) {
                    f = AnimateUtils.viscousFluid(f);
                } else {
                    f = this.mInterpolator.getInterpolation(f);
                }
                if (!SplineOverScroller.access$000(this.mScrollerX)) {
                    this.mScrollerX.updateScroll(f);
                }
                if (!SplineOverScroller.access$000(this.mScrollerY)) {
                    this.mScrollerY.updateScroll(f);
                    break;
                }
                break;
            case 1:
                if (!(SplineOverScroller.access$000(this.mScrollerX) || this.mScrollerX.update() || this.mScrollerX.continueWhenFinished())) {
                    this.mScrollerX.finish();
                }
                if (!(SplineOverScroller.access$000(this.mScrollerY) || this.mScrollerY.update() || this.mScrollerY.continueWhenFinished())) {
                    this.mScrollerY.finish();
                    break;
                }
        }
        return true;
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        startScroll(i, i2, i3, i4, DEFAULT_DURATION);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        this.mMode = 0;
        this.mScrollerX.startScroll(i, i3, i5);
        this.mScrollerY.startScroll(i2, i4, i5);
    }

    public boolean springBack(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mMode = 1;
        boolean springback = this.mScrollerX.springback(i, i3, i4);
        boolean springback2 = this.mScrollerY.springback(i2, i5, i6);
        if (springback || springback2) {
            return true;
        }
        return false;
    }

    public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        fling(i, i2, i3, i4, i5, i6, i7, i8, 0, 0);
    }

    public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        int i11;
        if (this.mFlywheel && !isFinished()) {
            float access$200 = SplineOverScroller.access$200(this.mScrollerX);
            float access$2002 = SplineOverScroller.access$200(this.mScrollerY);
            if (Math.signum((float) i3) == Math.signum(access$200) && Math.signum((float) i4) == Math.signum(access$2002)) {
                i4 = (int) (((float) i4) + access$2002);
                i11 = (int) (access$200 + ((float) i3));
                this.mMode = 1;
                this.mScrollerX.fling(i, i11, i5, i6, i9);
                this.mScrollerY.fling(i2, i4, i7, i8, i10);
            }
        }
        i11 = i3;
        this.mMode = 1;
        this.mScrollerX.fling(i, i11, i5, i6, i9);
        this.mScrollerY.fling(i2, i4, i7, i8, i10);
    }

    public void fling(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        SplineOverScroller.access$602(this.mScrollerX, j);
        SplineOverScroller.access$602(this.mScrollerY, j);
    }

    public void notifyHorizontalEdgeReached(int i, int i2, int i3) {
        this.mScrollerX.notifyEdgeReached(i, i2, i3);
    }

    public void notifyVerticalEdgeReached(int i, int i2, int i3) {
        this.mScrollerY.notifyEdgeReached(i, i2, i3);
    }

    public boolean isOverScrolled() {
        return ((SplineOverScroller.access$000(this.mScrollerX) || SplineOverScroller.access$700(this.mScrollerX) == 0) && (SplineOverScroller.access$000(this.mScrollerY) || SplineOverScroller.access$700(this.mScrollerY) == 0)) ? false : true;
    }

    public void abortAnimation() {
        this.mScrollerX.finish();
        this.mScrollerY.finish();
    }

    public int timePassed() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - Math.min(SplineOverScroller.access$600(this.mScrollerX), SplineOverScroller.access$600(this.mScrollerY)));
    }

    public boolean isScrollingInDirection(float f, float f2) {
        return !isFinished() && Math.signum(f) == Math.signum((float) (SplineOverScroller.access$400(this.mScrollerX) - SplineOverScroller.access$300(this.mScrollerX))) && Math.signum(f2) == Math.signum((float) (SplineOverScroller.access$400(this.mScrollerY) - SplineOverScroller.access$300(this.mScrollerY)));
    }
}
