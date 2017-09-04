package com.qq.reader.common.widget.swipelistview;

import android.support.v4.widget.h;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.qq.reader.common.monitor.f;

public class SwipeMenuLayout extends FrameLayout {
    private View a;
    private SwipeMenuView b;
    private int c;
    private h d;
    private h e;
    private int f;
    private int g;

    public int getPosition() {
        return this.g;
    }

    public void setPosition(int i) {
        this.g = i;
        this.b.setPosition(i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public boolean a() {
        return this.c == 1;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    private void a(int i) {
        int width;
        if (i > this.b.getWidth()) {
            width = this.b.getWidth();
        } else {
            width = i;
        }
        if (width < 0) {
            width = 0;
        }
        this.a.layout(-width, this.a.getTop(), this.a.getWidth() - width, getMeasuredHeight());
        this.b.layout(this.a.getWidth() - width, this.b.getTop(), (this.a.getWidth() + this.b.getWidth()) - width, this.b.getBottom());
    }

    public void computeScroll() {
        if (this.c == 1) {
            if (this.d.g()) {
                a(this.d.b());
                postInvalidate();
            }
        } else if (this.e.g()) {
            a(this.f - this.e.b());
            postInvalidate();
        }
    }

    public View getContentView() {
        return this.a;
    }

    public SwipeMenuView getMenuView() {
        return this.b;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.b.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, getMeasuredWidth(), this.a.getMeasuredHeight());
        this.b.layout(getMeasuredWidth(), 0, getMeasuredWidth() + this.b.getMeasuredWidth(), this.a.getMeasuredHeight());
    }

    public void setMenuHeight(int i) {
        f.b("byz", "pos = " + this.g + ", height = " + i);
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        if (layoutParams.height != i) {
            layoutParams.height = i;
            this.b.setLayoutParams(this.b.getLayoutParams());
        }
    }
}
