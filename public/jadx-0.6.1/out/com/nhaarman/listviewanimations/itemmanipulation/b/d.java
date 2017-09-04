package com.nhaarman.listviewanimations.itemmanipulation.b;

import android.os.Handler;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import com.nhaarman.listviewanimations.b.e;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* compiled from: SwipeDismissTouchListener */
public class d extends e {
    private final b a;
    private final long b;
    private final Collection<View> c = new LinkedList();
    private final List<Integer> d = new LinkedList();
    private int e;
    private final Handler f = new Handler();

    /* compiled from: SwipeDismissTouchListener */
    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        public void onAnimationEnd(Animator animator) {
            d dVar = this.a;
            dVar.e = dVar.e - 1;
            this.a.a();
        }
    }

    /* compiled from: SwipeDismissTouchListener */
    private static class b implements AnimatorUpdateListener {
        private final View a;

        b(View view) {
            this.a = view;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            LayoutParams layoutParams = this.a.getLayoutParams();
            layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            this.a.setLayoutParams(layoutParams);
        }
    }

    /* compiled from: SwipeDismissTouchListener */
    private class c implements Runnable {
        final /* synthetic */ d a;
        private final int b;
        private final int c;

        c(d dVar, int i, int i2) {
            this.a = dVar;
            this.b = i;
            this.c = i2;
        }

        public void run() {
            this.a.e().a_(-this.b, 1);
            this.a.b(this.c);
        }
    }

    public d(e eVar, b bVar) {
        super(eVar);
        this.a = bVar;
        this.b = (long) eVar.h().getContext().getResources().getInteger(17694720);
    }

    public void a(int i) {
        int c_ = e().c_();
        int d_ = e().d_();
        if (c_ <= i && i <= d_) {
            super.a(i);
        } else if (i > d_) {
            b(i);
        } else {
            d(i);
        }
    }

    protected void b(int i) {
        this.d.add(Integer.valueOf(i));
        a();
    }

    private void d(int i) {
        View a = com.nhaarman.listviewanimations.b.b.a(e(), e().c_());
        if (a != null) {
            a.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = a.getMeasuredHeight();
            e().a_(measuredHeight, (int) this.b);
            this.f.postDelayed(new c(this, measuredHeight, i), this.b);
        }
    }

    protected void a(View view, int i) {
        a();
    }

    protected boolean b(View view, int i) {
        return true;
    }

    protected void c(View view, int i) {
        d(view, i);
    }

    protected void d(View view, int i) {
        this.c.add(view);
        this.d.add(Integer.valueOf(i));
        ValueAnimator duration = ValueAnimator.ofInt(view.getHeight(), 1).setDuration(this.b);
        duration.addUpdateListener(new b(view));
        duration.addListener(new a());
        duration.start();
        this.e++;
    }

    protected void a() {
        if (this.e == 0 && g() == 0) {
            a(this.c);
            a(this.d);
            this.c.clear();
            this.d.clear();
        }
    }

    protected void a(List<Integer> list) {
        if (!list.isEmpty()) {
            Collections.sort(list, Collections.reverseOrder());
            int[] iArr = new int[list.size()];
            int i = 0;
            for (Integer intValue : list) {
                iArr[i] = intValue.intValue();
                i++;
            }
            this.a.onDismiss(e().h(), iArr);
        }
    }

    protected void a(Iterable<View> iterable) {
        for (View a : iterable) {
            a(a);
        }
    }

    protected void a(View view) {
        super.a(view);
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = 0;
        view.setLayoutParams(layoutParams);
    }

    protected int b() {
        return this.e;
    }
}
