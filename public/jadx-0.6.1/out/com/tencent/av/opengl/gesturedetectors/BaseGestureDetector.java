package com.tencent.av.opengl.gesturedetectors;

import android.content.Context;
import android.view.MotionEvent;

public abstract class BaseGestureDetector {
    protected static final float PRESSURE_THRESHOLD = 0.67f;
    protected final Context mContext;
    protected MotionEvent mCurrEvent;
    protected float mCurrPressure;
    protected boolean mGestureInProgress;
    protected MotionEvent mPrevEvent;
    protected float mPrevPressure;
    protected long mTimeDelta;

    protected abstract void handleInProgressEvent(int i, MotionEvent motionEvent);

    protected abstract void handleStartProgressEvent(int i, MotionEvent motionEvent);

    public BaseGestureDetector(Context context) {
        this.mContext = context;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.mGestureInProgress) {
            handleInProgressEvent(action, motionEvent);
        } else {
            handleStartProgressEvent(action, motionEvent);
        }
        return true;
    }

    protected void updateStateByEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.mPrevEvent;
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
            this.mCurrEvent = null;
        }
        this.mCurrEvent = MotionEvent.obtain(motionEvent);
        this.mTimeDelta = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.mCurrPressure = motionEvent.getPressure(motionEvent.getActionIndex());
        this.mPrevPressure = motionEvent2.getPressure(motionEvent2.getActionIndex());
    }

    protected void resetState() {
        if (this.mPrevEvent != null) {
            this.mPrevEvent.recycle();
            this.mPrevEvent = null;
        }
        if (this.mCurrEvent != null) {
            this.mCurrEvent.recycle();
            this.mCurrEvent = null;
        }
        this.mGestureInProgress = false;
    }

    public boolean isInProgress() {
        return this.mGestureInProgress;
    }

    public long getTimeDelta() {
        return this.mTimeDelta;
    }

    public long getEventTime() {
        return this.mCurrEvent.getEventTime();
    }
}
