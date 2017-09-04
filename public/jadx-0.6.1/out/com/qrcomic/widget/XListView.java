package com.qrcomic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class XListView extends ListView {
    private int a;
    private int b;
    private a c;
    private b d;
    private a e;

    public interface a {
        void a();
    }

    public interface b {
        boolean a(View view, MotionEvent motionEvent);
    }

    public XListView(Context context) {
        this(context, null);
    }

    public XListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public XListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = -1;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int windowOrientation = getWindowOrientation();
        a(i, i2, i3, i4, this.a != windowOrientation, windowOrientation);
        this.a = windowOrientation;
    }

    public void setOnSizeChangeListener(a aVar) {
        this.c = aVar;
    }

    private void a(int i, int i2, int i3, int i4, boolean z, int i5) {
        if (this.c != null) {
            this.c.a(i, i2, i3, i4, z, i5);
        }
    }

    private int getWindowOrientation() {
        return getContext().getResources().getDisplayMetrics().widthPixels > getContext().getResources().getDisplayMetrics().heightPixels ? 1 : 2;
    }

    public void setMotionEventInterceptor(b bVar) {
        this.d = bVar;
    }

    public void setMaxHeight(int i) {
        this.b = i;
        if (this.b < getMeasuredHeight()) {
            requestLayout();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean a;
        boolean z;
        if (this.d != null) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            if (obtain != null) {
                a = this.d.a(this, obtain);
                obtain.recycle();
                z = a;
                if (!z) {
                    return true;
                }
                try {
                    a = super.dispatchTouchEvent(motionEvent);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    a = false;
                }
                if (z || r2) {
                    return true;
                }
                return false;
            }
        }
        z = false;
        if (!z) {
            return true;
        }
        a = super.dispatchTouchEvent(motionEvent);
        if (!z) {
        }
        return true;
    }

    public void setDrawFinishedListener(a aVar) {
        this.e = aVar;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.e != null) {
            this.e.a();
        }
    }
}
