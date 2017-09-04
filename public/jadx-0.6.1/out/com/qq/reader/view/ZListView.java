package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class ZListView extends ListView {
    private float a;
    private float b;
    private float c;
    private float d;

    public ZListView(Context context) {
        super(context);
    }

    public ZListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ZListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = 0.0f;
                this.a = 0.0f;
                this.c = motionEvent.getX();
                this.d = motionEvent.getY();
                break;
            case 2:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.a += Math.abs(x - this.c);
                this.b += Math.abs(y - this.d);
                this.c = x;
                this.d = y;
                if (this.a > this.b) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
