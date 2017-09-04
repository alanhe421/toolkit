package com.qq.reader.module.comic.views;

import android.view.MotionEvent;

/* compiled from: RotateGestureDetector */
public class b {
    private a a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;

    public b(a aVar) {
        this.a = aVar;
    }

    public void a(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 2:
                if (motionEvent.getPointerCount() > 1) {
                    this.c = b(motionEvent);
                    double toDegrees = Math.toDegrees(Math.atan((double) this.c)) - Math.toDegrees(Math.atan((double) this.b));
                    if (Math.abs(toDegrees) <= 120.0d) {
                        this.a.a((float) toDegrees, (this.f + this.d) / 2.0f, (this.g + this.e) / 2.0f);
                    }
                    this.b = this.c;
                    return;
                }
                return;
            case 5:
            case 6:
                if (motionEvent.getPointerCount() == 2) {
                    this.b = b(motionEvent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private float b(MotionEvent motionEvent) {
        this.d = motionEvent.getX(0);
        this.e = motionEvent.getY(0);
        this.f = motionEvent.getX(1);
        this.g = motionEvent.getY(1);
        return (this.g - this.e) / (this.f - this.d);
    }
}
