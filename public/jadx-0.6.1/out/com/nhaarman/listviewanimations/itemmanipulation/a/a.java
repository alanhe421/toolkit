package com.nhaarman.listviewanimations.itemmanipulation.a;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.nhaarman.listviewanimations.b.d;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;

/* compiled from: AnimateAdditionAdapter */
public class a<T> extends com.nhaarman.listviewanimations.b {
    private long a = 300;
    private long b = 300;
    private final d<T> c;
    private final b<T> d;
    private boolean e = true;

    /* compiled from: AnimateAdditionAdapter */
    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ a a;
        private final int b;

        a(a aVar, int i) {
            this.a = aVar;
            this.b = i;
        }

        public void onAnimationEnd(Animator animator) {
            this.a.d.a(this.b);
        }
    }

    /* compiled from: AnimateAdditionAdapter */
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

    public a(BaseAdapter baseAdapter) {
        super(baseAdapter);
        BaseAdapter b = b();
        if (b instanceof d) {
            this.c = (d) b;
            this.d = new b(this.c);
            return;
        }
        throw new IllegalArgumentException("BaseAdapter should implement Insertable!");
    }

    @Deprecated
    public void a(AbsListView absListView) {
        if (absListView instanceof ListView) {
            a((ListView) absListView);
            return;
        }
        throw new IllegalArgumentException("AnimateAdditionAdapter requires a ListView!");
    }

    public void a(ListView listView) {
        a(new com.nhaarman.listviewanimations.b.a(listView));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Object view2 = super.getView(i, view, viewGroup);
        if (this.d.a().contains(Integer.valueOf(i))) {
            view2.measure(MeasureSpec.makeMeasureSpec(-1, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(-2, 0));
            int measuredHeight = view2.getMeasuredHeight();
            ValueAnimator ofInt = ValueAnimator.ofInt(1, measuredHeight);
            ofInt.addUpdateListener(new b(view2));
            Object a = a(view2, viewGroup);
            Animator[] animatorArr = new Animator[(a.length + 1)];
            animatorArr[0] = ofInt;
            System.arraycopy(a, 0, animatorArr, 1, a.length);
            new AnimatorSet().playTogether(animatorArr);
            ViewHelper.setAlpha(view2, 0.0f);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view2, "alpha", 0.0f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(r1, ofFloat);
            animatorSet.setDuration(this.b);
            animatorSet.addListener(new a(this, i));
            animatorSet.start();
        }
        return view2;
    }

    protected Animator[] a(View view, ViewGroup viewGroup) {
        return new Animator[0];
    }
}
