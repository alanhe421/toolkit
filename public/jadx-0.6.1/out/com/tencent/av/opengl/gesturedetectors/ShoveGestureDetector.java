package com.tencent.av.opengl.gesturedetectors;

import android.content.Context;
import android.view.MotionEvent;

public class ShoveGestureDetector extends TwoFingerGestureDetector {
    private float mCurrAverageY;
    private final OnShoveGestureListener mListener;
    private float mPrevAverageY;
    private boolean mSloppyGesture;

    public interface OnShoveGestureListener {
        boolean onShove(ShoveGestureDetector shoveGestureDetector);

        boolean onShoveBegin(ShoveGestureDetector shoveGestureDetector);

        void onShoveEnd(ShoveGestureDetector shoveGestureDetector);
    }

    public static class SimpleOnShoveGestureListener implements OnShoveGestureListener {
        public boolean onShove(ShoveGestureDetector shoveGestureDetector) {
            return false;
        }

        public boolean onShoveBegin(ShoveGestureDetector shoveGestureDetector) {
            return true;
        }

        public void onShoveEnd(ShoveGestureDetector shoveGestureDetector) {
        }
    }

    public ShoveGestureDetector(Context context, OnShoveGestureListener onShoveGestureListener) {
        super(context);
        this.mListener = onShoveGestureListener;
    }

    protected void handleStartProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.mSloppyGesture) {
                    this.mSloppyGesture = isSloppyGesture(motionEvent);
                    if (!this.mSloppyGesture) {
                        this.mGestureInProgress = this.mListener.onShoveBegin(this);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                resetState();
                this.mPrevEvent = MotionEvent.obtain(motionEvent);
                this.mTimeDelta = 0;
                updateStateByEvent(motionEvent);
                this.mSloppyGesture = isSloppyGesture(motionEvent);
                if (!this.mSloppyGesture) {
                    this.mGestureInProgress = this.mListener.onShoveBegin(this);
                    return;
                }
                return;
            case 6:
                if (!this.mSloppyGesture) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void handleInProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                updateStateByEvent(motionEvent);
                if (this.mCurrPressure / this.mPrevPressure > 0.67f && Math.abs(getShovePixelsDelta()) > 0.5f && this.mListener.onShove(this)) {
                    this.mPrevEvent.recycle();
                    this.mPrevEvent = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.mSloppyGesture) {
                    this.mListener.onShoveEnd(this);
                }
                resetState();
                return;
            case 6:
                updateStateByEvent(motionEvent);
                if (!this.mSloppyGesture) {
                    this.mListener.onShoveEnd(this);
                }
                resetState();
                return;
            default:
                return;
        }
    }

    protected void resetState() {
        super.resetState();
        this.mSloppyGesture = false;
        this.mPrevAverageY = 0.0f;
        this.mCurrAverageY = 0.0f;
    }

    protected void updateStateByEvent(MotionEvent motionEvent) {
        super.updateStateByEvent(motionEvent);
        MotionEvent motionEvent2 = this.mPrevEvent;
        this.mPrevAverageY = (motionEvent2.getY(1) + motionEvent2.getY(0)) / 2.0f;
        this.mCurrAverageY = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
    }

    protected boolean isSloppyGesture(MotionEvent motionEvent) {
        if (super.isSloppyGesture(motionEvent)) {
            return true;
        }
        double abs = Math.abs(Math.atan2((double) this.mCurrFingerDiffY, (double) this.mCurrFingerDiffX));
        if ((0.0d >= abs || abs >= 0.3499999940395355d) && (2.7899999618530273d >= abs || abs >= 3.141592653589793d)) {
            return true;
        }
        return false;
    }

    public float getShovePixelsDelta() {
        return this.mCurrAverageY - this.mPrevAverageY;
    }
}
