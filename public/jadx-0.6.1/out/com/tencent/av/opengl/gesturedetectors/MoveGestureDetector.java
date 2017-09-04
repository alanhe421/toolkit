package com.tencent.av.opengl.gesturedetectors;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;

public class MoveGestureDetector extends BaseGestureDetector {
    private static final PointF FOCUS_DELTA_ZERO = new PointF();
    private PointF mCurrFocusInternal;
    private PointF mFocusDeltaExternal = new PointF();
    private PointF mFocusExternal = new PointF();
    private final OnMoveGestureListener mListener;
    private PointF mPrevFocusInternal;

    public interface OnMoveGestureListener {
        boolean onMove(MoveGestureDetector moveGestureDetector);

        boolean onMoveBegin(MoveGestureDetector moveGestureDetector);

        void onMoveEnd(MoveGestureDetector moveGestureDetector);
    }

    public static class SimpleOnMoveGestureListener implements OnMoveGestureListener {
        public boolean onMove(MoveGestureDetector moveGestureDetector) {
            return false;
        }

        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            return true;
        }

        public void onMoveEnd(MoveGestureDetector moveGestureDetector) {
        }
    }

    public MoveGestureDetector(Context context, OnMoveGestureListener onMoveGestureListener) {
        super(context);
        this.mListener = onMoveGestureListener;
    }

    protected void handleStartProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 0:
                resetState();
                this.mPrevEvent = MotionEvent.obtain(motionEvent);
                this.mTimeDelta = 0;
                updateStateByEvent(motionEvent);
                return;
            case 2:
                this.mGestureInProgress = this.mListener.onMoveBegin(this);
                return;
            default:
                return;
        }
    }

    protected void handleInProgressEvent(int i, MotionEvent motionEvent) {
        switch (i) {
            case 1:
            case 3:
                this.mListener.onMoveEnd(this);
                resetState();
                return;
            case 2:
                updateStateByEvent(motionEvent);
                if (this.mCurrPressure / this.mPrevPressure > 0.67f && this.mListener.onMove(this)) {
                    this.mPrevEvent.recycle();
                    this.mPrevEvent = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void updateStateByEvent(MotionEvent motionEvent) {
        super.updateStateByEvent(motionEvent);
        MotionEvent motionEvent2 = this.mPrevEvent;
        this.mCurrFocusInternal = determineFocalPoint(motionEvent);
        this.mPrevFocusInternal = determineFocalPoint(motionEvent2);
        this.mFocusDeltaExternal = (motionEvent2.getPointerCount() != motionEvent.getPointerCount() ? 1 : null) != null ? FOCUS_DELTA_ZERO : new PointF(this.mCurrFocusInternal.x - this.mPrevFocusInternal.x, this.mCurrFocusInternal.y - this.mPrevFocusInternal.y);
        PointF pointF = this.mFocusExternal;
        pointF.x += this.mFocusDeltaExternal.x;
        pointF = this.mFocusExternal;
        pointF.y += this.mFocusDeltaExternal.y;
    }

    private PointF determineFocalPoint(MotionEvent motionEvent) {
        float f = 0.0f;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f2 += motionEvent.getX(i);
            f += motionEvent.getY(i);
        }
        return new PointF(f2 / ((float) pointerCount), f / ((float) pointerCount));
    }

    public float getFocusX() {
        return this.mFocusExternal.x;
    }

    public float getFocusY() {
        return this.mFocusExternal.y;
    }

    public PointF getFocusDelta() {
        return this.mFocusDeltaExternal;
    }
}
