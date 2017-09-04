package com.qq.reader.module.feed.swipe;

import android.os.Handler;
import android.view.View;
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

/* compiled from: FeedSwipeDismissTouchListener */
public class b extends c {
    private final com.nhaarman.listviewanimations.itemmanipulation.b.b a;
    private final long b;
    private final Collection<View> c = new LinkedList();
    private final List<Integer> d = new LinkedList();
    private int e;
    private final Handler f = new Handler();

    /* compiled from: FeedSwipeDismissTouchListener */
    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public void onAnimationEnd(Animator animator) {
            this.a.e = this.a.e - 1;
            this.a.a();
        }
    }

    /* compiled from: FeedSwipeDismissTouchListener */
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

    public b(e eVar, com.nhaarman.listviewanimations.itemmanipulation.b.b bVar) {
        super(eVar);
        this.a = bVar;
        this.b = (long) eVar.h().getContext().getResources().getInteger(17694720);
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
        ValueAnimator duration = ValueAnimator.ofInt(new int[]{view.getHeight(), 1}).setDuration(this.b);
        duration.addUpdateListener(new b(view));
        duration.addListener(new a());
        duration.start();
        this.e++;
    }

    protected void a() {
        if (this.e == 0 && c() == 0) {
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
            this.a.onDismiss(b().h(), iArr);
        }
    }

    protected void a(Iterable<View> iterable) {
        for (View d : iterable) {
            d(d);
        }
    }

    protected void d(View view) {
        super.d(view);
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = 0;
        view.setLayoutParams(layoutParams);
    }
}
