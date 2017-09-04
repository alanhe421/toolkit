package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MViewPager extends ViewPager {
    private int a = 101;
    private a b;

    public MViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MViewPager(Context context) {
        super(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setCurTab(a aVar) {
        this.b = aVar;
    }

    public a getCurTab() {
        return this.b;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Rect rect = new Rect();
        if (this.b != null) {
            rect = this.b.e();
        }
        if (rect == null) {
            return a(motionEvent);
        }
        if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return a(motionEvent);
    }

    public boolean a(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception e) {
            return false;
        }
    }
}
