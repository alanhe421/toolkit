package com.qq.reader.common.widget.swipelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.view.pullupdownlist.XListView;

public class SwipeMenuListView extends XListView {
    private int b = 5;
    private int c = 3;
    private int d;
    private c e;
    private a f;
    private b g;
    private b h;
    private Interpolator i;
    private Interpolator j;

    public interface a {
    }

    public interface b {
    }

    public interface c {
    }

    public SwipeMenuListView(Context context) {
        super(context);
        h();
    }

    public SwipeMenuListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        h();
    }

    public SwipeMenuListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        h();
    }

    private void h() {
        setCrashTag(CustomArrayList.Class_FeedGoogleCardsActivity);
        this.c = a(this.c);
        this.b = a(this.b);
        this.d = 0;
    }

    public void setCloseInterpolator(Interpolator interpolator) {
        this.i = interpolator;
    }

    public void setOpenInterpolator(Interpolator interpolator) {
        this.j = interpolator;
    }

    public Interpolator getOpenInterpolator() {
        return this.j;
    }

    public Interpolator getCloseInterpolator() {
        return this.i;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        try {
            z = super.onTouchEvent(motionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getContext().getResources().getDisplayMetrics());
    }

    public void setMenuCreator(b bVar) {
        this.g = bVar;
    }

    public void setOnMenuItemClickListener(b bVar) {
        this.h = bVar;
    }

    public void setOnSwipeListener(c cVar) {
        this.e = cVar;
    }

    public void setUpdateMenuInterface(a aVar) {
        this.f = aVar;
    }
}
