package com.nhaarman.listviewanimations.itemmanipulation.b;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/* compiled from: SwipeTouchListener */
public abstract class e implements OnTouchListener, com.nhaarman.listviewanimations.itemmanipulation.a {
    private final int a;
    private final int b;
    private final int c;
    private final long d;
    private final com.nhaarman.listviewanimations.b.e e;
    private float f;
    private int g = 1;
    private float h;
    private float i;
    private boolean j;
    private boolean k;
    private VelocityTracker l;
    private View m;
    private View n;
    private int o = -1;
    private int p = -1;
    private boolean q;
    private int r;
    private a s;
    private int t;
    private boolean u = true;

    /* compiled from: SwipeTouchListener */
    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ e a;
        private final View b;
        private final int c;

        private a(e eVar, View view, int i) {
            this.a = eVar;
            this.b = view;
            this.c = i;
        }

        public void onAnimationEnd(Animator animator) {
            e eVar = this.a;
            eVar.t = eVar.t - 1;
            this.a.c(this.b, this.c);
        }
    }

    /* compiled from: SwipeTouchListener */
    private class b extends AnimatorListenerAdapter {
        final /* synthetic */ e a;
        private final View b;
        private final int c;

        private b(e eVar, View view, int i) {
            this.a = eVar;
            this.b = view;
            this.c = i;
        }

        public void onAnimationEnd(Animator animator) {
            e eVar = this.a;
            eVar.t = eVar.t - 1;
            this.a.a(this.b, this.c);
        }
    }

    protected abstract boolean b(View view, int i);

    protected abstract void c(View view, int i);

    protected e(com.nhaarman.listviewanimations.b.e eVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(eVar.h().getContext());
        this.a = viewConfiguration.getScaledTouchSlop();
        this.b = viewConfiguration.getScaledMinimumFlingVelocity() * 16;
        this.c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.d = (long) eVar.h().getContext().getResources().getInteger(17694720);
        this.e = eVar;
    }

    public void a(a aVar) {
        this.s = aVar;
    }

    public void a(float f) {
        this.f = f;
    }

    public void c() {
        this.q = true;
        this.r = 0;
    }

    public void c(int i) {
        this.r = i;
        this.q = false;
    }

    public void d() {
        if (this.e.g() != null) {
            this.p = this.e.d() - this.e.f();
        }
    }

    public com.nhaarman.listviewanimations.b.e e() {
        return this.e;
    }

    public void a(int i) {
        int c_ = this.e.c_();
        int d_ = this.e.d_();
        if (i < c_ || i > d_) {
            throw new IllegalArgumentException("View for position " + i + " not visible!");
        }
        View a = com.nhaarman.listviewanimations.b.b.a(this.e, i);
        if (a == null) {
            throw new IllegalStateException("No view found for position " + i);
        }
        a(a, i, true);
        this.t++;
        this.p--;
    }

    public boolean f() {
        return this.j;
    }

    public boolean a(MotionEvent motionEvent) {
        return onTouch(null, motionEvent);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.e.g() == null) {
            return false;
        }
        if (this.p == -1 || this.t == 0) {
            this.p = this.e.d() - this.e.f();
        }
        if (this.g < 2) {
            this.g = this.e.h().getWidth();
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                return a(view, motionEvent);
            case 1:
                return c(motionEvent);
            case 2:
                return b(view, motionEvent);
            case 3:
                return a();
            default:
                return false;
        }
    }

    private boolean a(View view, MotionEvent motionEvent) {
        if (!this.u) {
            return false;
        }
        View b = b(motionEvent);
        if (b == null) {
            return false;
        }
        int a = com.nhaarman.listviewanimations.b.b.a(this.e, b);
        this.k = b(a);
        if (this.o == a || a >= this.p) {
            return false;
        }
        if (view != null) {
            view.onTouchEvent(motionEvent);
        }
        a(motionEvent, b);
        this.h = motionEvent.getX();
        this.i = motionEvent.getY();
        this.m = b;
        this.n = b(b);
        this.o = a;
        this.l = VelocityTracker.obtain();
        this.l.addMovement(motionEvent);
        return true;
    }

    private View b(MotionEvent motionEvent) {
        Rect rect = new Rect();
        int e = this.e.e();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        View view = null;
        int i = 0;
        while (i < e && view == null) {
            View a = this.e.a(i);
            if (a != null) {
                a.getHitRect(rect);
                if (rect.contains(x, y)) {
                    i++;
                    view = a;
                }
            }
            a = view;
            i++;
            view = a;
        }
        return view;
    }

    private boolean b(int i) {
        if (this.e.g() == null) {
            return false;
        }
        if (this.s == null) {
            return true;
        }
        return this.s.a(this.e.g().getItemId(i), i);
    }

    private void a(MotionEvent motionEvent, View view) {
        if (this.q) {
            this.e.h().requestDisallowInterceptTouchEvent(true);
        } else if (this.r != 0) {
            this.q = false;
            View findViewById = view.findViewById(this.r);
            if (findViewById != null && a(this.e.h(), findViewById).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.e.h().requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    private boolean b(View view, MotionEvent motionEvent) {
        if (this.l == null || this.m == null) {
            return false;
        }
        this.l.addMovement(motionEvent);
        float x = motionEvent.getX() - this.h;
        float y = motionEvent.getY() - this.i;
        if (Math.abs(x) > ((float) this.a) && Math.abs(x) > Math.abs(y)) {
            if (!this.j) {
                this.t++;
                e(this.m, this.o);
            }
            this.j = true;
            this.e.h().requestDisallowInterceptTouchEvent(true);
            if (view != null) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                view.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        if (!this.j) {
            return false;
        }
        if (this.k) {
            ViewHelper.setTranslationX(this.n, x);
            ViewHelper.setAlpha(this.n, Math.max(this.f, Math.min(1.0f, 1.0f - ((Math.abs(x) * 2.0f) / ((float) this.g)))));
            return true;
        }
        ViewHelper.setTranslationX(this.n, x * 0.1f);
        return true;
    }

    private boolean a() {
        if (!(this.l == null || this.m == null)) {
            if (this.o != -1 && this.j) {
                f(this.m, this.o);
                b();
            }
            h();
        }
        return false;
    }

    private boolean c(MotionEvent motionEvent) {
        boolean z = true;
        if (!(this.l == null || this.m == null)) {
            if (this.j) {
                boolean z2;
                if (this.k) {
                    float x = motionEvent.getX() - this.h;
                    this.l.addMovement(motionEvent);
                    this.l.computeCurrentVelocity(1000);
                    float abs = Math.abs(this.l.getXVelocity());
                    float abs2 = Math.abs(this.l.getYVelocity());
                    if (Math.abs(x) > ((float) (this.g / 2))) {
                        z2 = x > 0.0f;
                    } else if (((float) this.b) <= abs && abs <= ((float) this.c) && abs2 < abs) {
                        z2 = this.l.getXVelocity() > 0.0f;
                    }
                    if (z) {
                        f(this.m, this.o);
                        b();
                    } else {
                        g(this.m, this.o);
                        if (b(this.m, this.o)) {
                            this.p--;
                        }
                        a(z2);
                    }
                }
                z2 = false;
                z = false;
                if (z) {
                    f(this.m, this.o);
                    b();
                } else {
                    g(this.m, this.o);
                    if (b(this.m, this.o)) {
                        this.p--;
                    }
                    a(z2);
                }
            }
            h();
        }
        return false;
    }

    private void a(boolean z) {
        if (this.m != null) {
            a(this.m, this.o, z);
        }
    }

    private void a(View view, int i, boolean z) {
        if (this.g < 2) {
            this.g = this.e.h().getWidth();
        }
        Object b = b(view);
        String str = "translationX";
        float[] fArr = new float[1];
        fArr[0] = (float) (z ? this.g : -this.g);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(b, str, fArr);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(b, "alpha", 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setDuration(this.d);
        animatorSet.addListener(new a(view, i));
        animatorSet.start();
    }

    private void b() {
        if (this.m != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.n, "translationX", 0.0f);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.n, "alpha", 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofFloat, ofFloat2);
            animatorSet.setDuration(this.d);
            animatorSet.addListener(new b(this.m, this.o));
            animatorSet.start();
        }
    }

    private void h() {
        if (this.l != null) {
            this.l.recycle();
        }
        this.l = null;
        this.h = 0.0f;
        this.i = 0.0f;
        this.m = null;
        this.n = null;
        this.o = -1;
        this.j = false;
        this.k = false;
    }

    protected void e(View view, int i) {
    }

    protected void f(View view, int i) {
    }

    protected void a(View view, int i) {
    }

    protected void g(View view, int i) {
    }

    protected void a(View view) {
        View b = b(view);
        ViewHelper.setAlpha(b, 1.0f);
        ViewHelper.setTranslationX(b, 0.0f);
    }

    protected int g() {
        return this.t;
    }

    protected View b(View view) {
        return view;
    }

    private static Rect a(View view, View view2) {
        Rect rect = new Rect(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
        if (!view.equals(view2)) {
            while (true) {
                View view3 = (ViewGroup) view2.getParent();
                if (view3.equals(view)) {
                    break;
                }
                rect.offset(view3.getLeft(), view3.getTop());
                view2 = view3;
            }
        }
        return rect;
    }
}
