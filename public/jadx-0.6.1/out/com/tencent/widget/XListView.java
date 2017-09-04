package com.tencent.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class XListView extends ListView {
    public static final int WINDOW_ORIENTATION_LANDSCAPE = 1;
    public static final int WINDOW_ORIENTATION_POERRAIT = 2;
    private MotionEventInterceptor mInterceptor;
    private OnSizeChangeListener mOnSizeChangeListener;
    private int mOrientation;
    private float xDistance;
    private float xLast;
    private float yDistance;
    private float yLast;

    public interface MotionEventInterceptor {
        boolean intercept(View view, MotionEvent motionEvent);
    }

    public XListView(Context context) {
        this(context, null);
    }

    public XListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public XListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOrientation = 0;
        setEdgeEffectEnabled(false);
        this.mOverscrollDistance = Integer.MAX_VALUE;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int windowOrientation = getWindowOrientation();
        notifySizeChanged(i, i2, i3, i4, this.mOrientation != windowOrientation, windowOrientation);
        this.mOrientation = windowOrientation;
    }

    public void setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        this.mOnSizeChangeListener = onSizeChangeListener;
    }

    private void notifySizeChanged(int i, int i2, int i3, int i4, boolean z, int i5) {
        if (this.mOnSizeChangeListener != null) {
            this.mOnSizeChangeListener.onSizeChanged(i, i2, i3, i4, z, i5);
        }
    }

    private int getWindowOrientation() {
        return getContext().getResources().getDisplayMetrics().widthPixels > getContext().getResources().getDisplayMetrics().heightPixels ? 1 : 2;
    }

    public void setOverScrollDistance(int i) {
        this.mOverscrollDistance = i;
    }

    public void setMotionEventInterceptor(MotionEventInterceptor motionEventInterceptor) {
        this.mInterceptor = motionEventInterceptor;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean intercept;
        if (this.mInterceptor != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            if (obtain != null) {
                intercept = this.mInterceptor.intercept(this, obtain);
                obtain.recycle();
                if (intercept || super.dispatchTouchEvent(motionEvent)) {
                    return true;
                }
                return false;
            }
        }
        intercept = false;
        if (!intercept) {
        }
        return true;
    }

    public void setEnsureOverScrollStatusToIdleWhenRelease(boolean z) {
        this.mEnsureOverScrollStatusToIdleWhenRelease = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.yDistance = 0.0f;
                this.xDistance = 0.0f;
                this.xLast = motionEvent.getX();
                this.yLast = motionEvent.getY();
                break;
            case 2:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.xDistance += Math.abs(x - this.xLast);
                this.yDistance += Math.abs(y - this.yLast);
                this.xLast = x;
                this.yLast = y;
                if (this.xDistance > this.yDistance) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
