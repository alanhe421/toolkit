package com.qq.reader.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;

public class FloatingFadeAnimView extends ImageView implements OnScrollListener {
    private boolean a = false;
    private a b;

    public static class a {
        public static long a = 400;
        public static long b = 3000;
        private View c;
        private AnimatorSet d;
        private int e = 0;
        private int f = 0;

        public a(View view, int i) {
            this.c = view;
            this.f = i;
        }

        private ObjectAnimator c() {
            return ObjectAnimator.ofFloat(this.c, "scaleX", new float[]{1.0f, 1.2f, 1.0f});
        }

        private ObjectAnimator d() {
            return ObjectAnimator.ofFloat(this.c, "scaleY", new float[]{1.0f, 1.2f, 1.0f});
        }

        private ObjectAnimator e() {
            return ObjectAnimator.ofFloat(this.c, "rotation", new float[]{0.0f, 22.5f, 0.0f, -22.5f, 0.0f, 22.5f, 0.0f, -22.5f, 0.0f});
        }

        private AnimatorSet f() {
            AnimatorSet animatorSet = new AnimatorSet();
            this.e = 0;
            animatorSet.playTogether(new Animator[]{c(), d(), e()});
            animatorSet.setStartDelay(b);
            animatorSet.setDuration(a);
            animatorSet.setInterpolator(new LinearInterpolator());
            animatorSet.addListener(new AnimatorListenerAdapter(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onAnimationEnd(Animator animator) {
                    if (this.a.f == -1) {
                        animator.start();
                        return;
                    }
                    this.a.e = this.a.e + 1;
                    if (this.a.e <= this.a.f) {
                        animator.start();
                    }
                }
            });
            return animatorSet;
        }

        public void a() {
            if (this.d != null) {
                this.d.removeAllListeners();
                this.d.end();
            }
        }

        public void b() {
            if (this.d != null) {
                a();
                this.d = null;
            }
            this.d = f();
            this.d.start();
        }
    }

    public FloatingFadeAnimView(Context context) {
        super(context);
        b();
    }

    public FloatingFadeAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public FloatingFadeAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (getVisibility() != 0) {
            return;
        }
        if (i == 1) {
            this.a = true;
            a(absListView);
        } else if (i == 2) {
            this.a = true;
        } else if (i == 0) {
            if (this.a) {
                b(absListView);
            }
            this.a = false;
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    protected void onDetachedFromWindow() {
        if (this.b != null) {
            this.b.a();
        }
        super.onDetachedFromWindow();
    }

    private void a(AbsListView absListView) {
        float alpha = absListView.getAlpha();
        if (alpha > 0.3f) {
            Animation a = a(200, alpha, 0.3f, new DecelerateInterpolator());
            a.setFillAfter(true);
            startAnimation(a);
        }
    }

    private void b(AbsListView absListView) {
        Animation a = a(800, 0.3f, 1.0f, new DecelerateInterpolator());
        a.setFillAfter(true);
        startAnimation(a);
    }

    private Animation a(long j, float f, float f2, Interpolator interpolator) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setDuration(j);
        return alphaAnimation;
    }

    public void setAnimationController(a aVar) {
        this.b = aVar;
    }

    public void a() {
        if (this.b != null) {
            this.b.b();
        }
    }
}
