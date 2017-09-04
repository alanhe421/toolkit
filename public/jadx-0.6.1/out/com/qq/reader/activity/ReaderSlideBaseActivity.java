package com.qq.reader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class ReaderSlideBaseActivity extends ReaderBaseActivity implements OnGestureListener {
    private GestureDetector a;
    private boolean b = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = false;
        this.a = new GestureDetector(this);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b || this.a == null || !this.a.onTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if ((Math.atan2((double) Math.abs(motionEvent2.getY() - motionEvent.getY()), (double) Math.abs(motionEvent2.getX() - motionEvent.getX())) * 180.0d) / 3.141592653589793d >= 40.0d || Math.abs(f) < 500.0f) {
            return false;
        }
        if (motionEvent.getX() > motionEvent2.getX()) {
            b();
        } else if (motionEvent.getX() >= motionEvent2.getX()) {
            return false;
        } else {
            a();
        }
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public void startActivity(Intent intent) {
        this.b = true;
        super.startActivity(intent);
    }

    protected void onResume() {
        this.b = false;
        super.onResume();
    }
}
