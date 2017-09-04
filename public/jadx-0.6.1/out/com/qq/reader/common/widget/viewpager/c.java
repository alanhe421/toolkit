package com.qq.reader.common.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.common.c.a;
import com.qq.reader.cservice.adv.b;

/* compiled from: ViewPagerManager */
public class c {
    private View a;
    private ViewPager b;
    private a c;
    private ViewGroup d;
    private GestureDetector e;
    private ViewGroup f;
    private Context g;

    public boolean a() {
        if (this.a == null || this.a.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    public void a(MotionEvent motionEvent) {
        if (this.a != null && this.a.getVisibility() == 0 && this.e != null && this.e.onTouchEvent(motionEvent)) {
            motionEvent.setAction(3);
        }
    }

    public synchronized void b() {
        if (this.a != null) {
            if (this.a.getVisibility() == 0) {
                this.a.setVisibility(8);
                this.f.removeView(this.a);
                this.f = null;
                this.a = null;
                if (this.e != null) {
                    this.e = null;
                }
                if (this.d != null) {
                    this.d.removeAllViews();
                    this.d = null;
                }
                if (this.b != null) {
                    this.b.removeAllViews();
                    this.b = null;
                }
                if (this.c != null) {
                    this.c.d();
                    this.c = null;
                }
            }
            b.a(this.g).a(true);
        }
        a.M = false;
    }
}
