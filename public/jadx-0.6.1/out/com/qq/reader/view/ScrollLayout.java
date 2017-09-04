package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

public class ScrollLayout extends ViewGroup {
    private Scroller a;
    private VelocityTracker b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private float h;
    private a i;

    public interface a {
        void a(int i);
    }

    public ScrollLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrollLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.e = 0;
        this.a = new Scroller(context);
        this.c = this.d;
        this.f = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
    }

    public void setScrollListener(a aVar) {
        this.i = aVar;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                childAt.layout(i5, 0, i5 + measuredWidth, childAt.getMeasuredHeight());
                i5 += measuredWidth;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    public void a() {
        int width = getWidth();
        a((getScrollX() + (width / 2)) / width);
    }

    public void a(int i) {
        int max = Math.max(0, Math.min(i, getChildCount() - 1));
        if (getScrollX() != getWidth() * max) {
            int width = (getWidth() * max) - getScrollX();
            this.a.startScroll(getScrollX(), 0, width, 0, Math.abs(width) * 2);
            this.c = max;
            if (this.i != null) {
                this.i.a(max);
            }
            invalidate();
        }
    }

    public void setToScreen(int i) {
        int max = Math.max(0, Math.min(i, getChildCount() - 1));
        this.c = max;
        scrollTo(max * getWidth(), 0);
    }

    public int getCurScreen() {
        return this.c;
    }

    public void computeScroll() {
        if (this.a.computeScrollOffset()) {
            scrollTo(this.a.getCurrX(), this.a.getCurrY());
            postInvalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b == null) {
            this.b = VelocityTracker.obtain();
        }
        this.b.addMovement(motionEvent);
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        motionEvent.getY();
        switch (action) {
            case 0:
                Log.e("ScrollLayout", "event down!");
                if (!this.a.isFinished()) {
                    this.a.abortAnimation();
                }
                this.g = x;
                break;
            case 1:
                Log.e("ScrollLayout", "event : up");
                VelocityTracker velocityTracker = this.b;
                velocityTracker.computeCurrentVelocity(1000);
                action = (int) velocityTracker.getXVelocity();
                Log.e("ScrollLayout", "velocityX:" + action);
                if (action > ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE && this.c > 0) {
                    Log.e("ScrollLayout", "snap left");
                    a(this.c - 1);
                } else if (action >= -600 || this.c >= getChildCount() - 1) {
                    a();
                } else {
                    Log.e("ScrollLayout", "snap right");
                    a(this.c + 1);
                }
                if (this.b != null) {
                    this.b.recycle();
                    this.b = null;
                }
                this.e = 0;
                break;
            case 2:
                action = (int) (this.g - x);
                this.g = x;
                scrollBy(action, 0);
                break;
            case 3:
                this.e = 0;
                break;
        }
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Log.e("ScrollLayout", "onInterceptTouchEvent-slop:" + this.f);
        int action = motionEvent.getAction();
        if (action == 2 && this.e != 0) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (action) {
            case 0:
                this.g = x;
                this.h = y;
                this.e = this.a.isFinished() ? 0 : 1;
                break;
            case 1:
            case 3:
                this.e = 0;
                break;
            case 2:
                if (((int) Math.abs(this.g - x)) > this.f) {
                    this.e = 1;
                    break;
                }
                break;
        }
        if (this.e == 0) {
            return false;
        }
        return true;
    }
}
