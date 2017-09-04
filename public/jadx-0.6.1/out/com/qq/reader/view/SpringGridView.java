package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.widget.GridView;
import android.widget.Scroller;

public class SpringGridView extends GridView {
    private Scroller a;
    private float b = 0.0f;
    private float c = 0.0f;
    private int d = 0;
    private boolean e = false;

    public void a(Context context) {
        this.a = new Scroller(context, new AccelerateInterpolator());
        this.d = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
    }

    public SpringGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public SpringGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SpringGridView(Context context) {
        super(context);
        a(context);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = motionEvent.getY();
                this.c = this.b;
                break;
            case 1:
                int scrollY = getScrollY();
                if (scrollY != 0) {
                    getScroller().startScroll(0, scrollY, 0, -scrollY, Math.abs(scrollY) << 1);
                    invalidate();
                }
                this.e = false;
                break;
            case 2:
                float y = motionEvent.getY();
                int top = getChildAt(0).getTop();
                int bottom = getChildAt(getChildCount() - 1).getBottom();
                int height = getHeight() - getPaddingBottom();
                float f = this.b - y;
                if (top >= getPaddingTop()) {
                    if (f < 0.0f || getScrollY() < 0) {
                        a(y, f);
                        if (!this.e) {
                            if (Math.abs(this.b - this.c) > ((float) this.d)) {
                                this.e = true;
                                break;
                            }
                        }
                        return true;
                    }
                } else if (bottom <= height && (f > 0.0f || getScrollY() > 0)) {
                    a(y, f);
                    if (this.e) {
                        return true;
                    }
                    if (Math.abs(this.b - this.c) > ((float) this.d)) {
                        this.e = true;
                    }
                }
                this.b = y;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(float f, float f2) {
        scrollBy(0, (int) ((1.0f - (((float) Math.abs(getScrollY())) / 200.0f)) * f2));
        this.b = f;
    }

    public void computeScroll() {
        if (this.a.computeScrollOffset()) {
            scrollTo(0, this.a.getCurrY());
            invalidate();
        }
    }

    public Scroller getScroller() {
        return this.a;
    }
}
