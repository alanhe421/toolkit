package com.qq.reader.module.bookstore.qweb;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class WebAdViewPager extends ViewPager {
    private a a;
    private boolean b = true;

    public interface a {
        boolean a();

        void b();
    }

    public WebAdViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WebAdViewPager(Context context) {
        super(context);
    }

    public void setCanHorizontalScroll(boolean z) {
        this.b = z;
    }

    public void setShouldIntercept(a aVar) {
        this.a = aVar;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.b) {
            return z;
        }
        if (this.a == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        boolean a = this.a.a();
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.a.b();
        }
        if (!a) {
            return z;
        }
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }
}
