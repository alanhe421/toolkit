package com.qq.reader.module.feed.swipe;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.nhaarman.listviewanimations.b.e;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.h;

/* compiled from: FeedSwipeTouchListener */
public abstract class c implements OnTouchListener, com.nhaarman.listviewanimations.itemmanipulation.a {
    private final int a;
    private final int b;
    private final int c;
    private final long d;
    private final e e;
    private int f = 1;
    private float g;
    private float h;
    private boolean i;
    private boolean j;
    private VelocityTracker k;
    private View l;
    private View m;
    private View n;
    private View o;
    private int p = -1;
    private int q = -1;
    private boolean r;
    private int s;
    private com.nhaarman.listviewanimations.itemmanipulation.b.a t;
    private int u;
    private boolean v = true;
    private Object w;

    /* compiled from: FeedSwipeTouchListener */
    private class a extends AnimatorListenerAdapter {
        final /* synthetic */ c a;
        private final View b;
        private final int c;

        private a(c cVar, View view, int i) {
            this.a = cVar;
            this.b = view;
            this.c = i;
        }

        public void onAnimationEnd(Animator animator) {
            this.a.u = this.a.u - 1;
            this.a.c(this.b, this.c);
        }
    }

    /* compiled from: FeedSwipeTouchListener */
    private class b extends AnimatorListenerAdapter {
        final /* synthetic */ c a;
        private final View b;
        private final int c;

        private b(c cVar, View view, int i) {
            this.a = cVar;
            this.b = view;
            this.c = i;
        }

        public void onAnimationEnd(Animator animator) {
            this.a.u = this.a.u - 1;
            this.a.a(this.b, this.c);
        }
    }

    protected abstract boolean b(View view, int i);

    protected abstract void c(View view, int i);

    protected c(e eVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(eVar.h().getContext().getApplicationContext());
        this.a = viewConfiguration.getScaledTouchSlop();
        this.b = viewConfiguration.getScaledMinimumFlingVelocity() * 32;
        this.c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.d = (long) eVar.h().getContext().getResources().getInteger(17694720);
        this.e = eVar;
    }

    public e b() {
        return this.e;
    }

    public boolean a(MotionEvent motionEvent) {
        return onTouch(null, motionEvent);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        if (this.e.g() == null) {
            return z;
        }
        if (this.q == -1 || this.u == 0) {
            this.q = this.e.d() - this.e.f();
        }
        if (this.f < 2) {
            this.f = this.e.h().getWidth();
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                try {
                    return a(view, motionEvent);
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), e.getMessage());
                    return z;
                }
            case 1:
                return c(motionEvent);
            case 2:
                return b(view, motionEvent);
            case 3:
                return a();
            default:
                return z;
        }
    }

    private boolean a(View view, MotionEvent motionEvent) {
        if (!this.v) {
            return false;
        }
        View b = b(motionEvent);
        if (b == null) {
            return false;
        }
        int a = com.nhaarman.listviewanimations.b.b.a(this.e, b);
        this.j = a(a);
        Object item = this.e.g().getItem(this.e.f() + a);
        this.w = item;
        if (item != null && (item instanceof FeedBaseCard) && !((FeedBaseCard) item).swipeEnable()) {
            return false;
        }
        if (this.w instanceof h) {
            ((h) this.w).onDown(motionEvent);
        }
        if (item != null && !(item instanceof FeedBaseCard)) {
            return false;
        }
        if (this.p == a || a >= this.q) {
            return false;
        }
        if (view != null) {
            view.onTouchEvent(motionEvent);
        }
        a(motionEvent, b);
        this.g = motionEvent.getX();
        this.h = motionEvent.getY();
        this.l = b;
        this.m = a(b);
        this.n = b(b);
        this.o = c(this.n);
        this.p = a;
        this.k = VelocityTracker.obtain();
        this.k.addMovement(motionEvent);
        return true;
    }

    protected View b(MotionEvent motionEvent) {
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

    private boolean a(int i) {
        if (this.e.g() == null) {
            return false;
        }
        if (this.t == null) {
            return true;
        }
        return this.t.a(this.e.g().getItemId(i), i);
    }

    private void a(MotionEvent motionEvent, View view) {
        if (this.r) {
            this.e.h().requestDisallowInterceptTouchEvent(true);
        } else if (this.s != 0) {
            this.r = false;
            View findViewById = view.findViewById(this.s);
            if (findViewById != null && a(this.e.h(), findViewById).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.e.h().requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    private boolean b(View view, MotionEvent motionEvent) {
        if (this.w instanceof h) {
            ((h) this.w).onMove(motionEvent);
        }
        if (this.k == null || this.l == null) {
            return false;
        }
        this.k.addMovement(motionEvent);
        float x = motionEvent.getX() - this.g;
        float y = motionEvent.getY() - this.h;
        if (x > 0.0f) {
            x = 0.0f;
        }
        if (Math.abs(x) > ((float) this.a) && Math.abs(x) > Math.abs(y)) {
            if (!this.i) {
                this.u++;
                e(this.l, this.p);
            }
            this.i = true;
            this.e.h().requestDisallowInterceptTouchEvent(true);
            this.n.setVisibility(0);
            if (view != null) {
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                view.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        if (!this.i) {
            return false;
        }
        if (this.j) {
            ViewHelper.setTranslationX(this.m, x);
            int i = this.f / 3;
            if (Math.abs(x) <= ((float) i)) {
                x = ((Math.abs(x) * 0.5f) / ((float) i)) + 0.5f;
                ViewHelper.setScaleX(this.o, x);
                ViewHelper.setScaleY(this.o, x);
            }
        } else {
            ViewHelper.setTranslationX(this.m, x * 0.1f);
        }
        return true;
    }

    private boolean a() {
        if (!(this.k == null || this.l == null)) {
            if (this.p != -1 && this.i) {
                f(this.l, this.p);
                d();
            }
            e();
            if (this.w instanceof h) {
                ((h) this.w).onUpOrCancel(null);
            }
            this.w = null;
        }
        return false;
    }

    private boolean c(MotionEvent motionEvent) {
        if (this.w instanceof h) {
            ((h) this.w).onUpOrCancel(motionEvent);
        }
        this.w = null;
        if (!(this.k == null || this.l == null)) {
            if (this.i) {
                boolean z;
                if (this.j) {
                    float x = motionEvent.getX() - this.g;
                    this.k.addMovement(motionEvent);
                    this.k.computeCurrentVelocity(1000);
                    float abs = Math.abs(this.k.getXVelocity());
                    float abs2 = Math.abs(this.k.getYVelocity());
                    if (Math.abs(x) > ((float) (this.f / 2)) && x < 0.0f) {
                        z = true;
                        if (z) {
                            f(this.l, this.p);
                            d();
                        } else {
                            g(this.l, this.p);
                            if (b(this.l, this.p)) {
                                this.q--;
                            }
                            a(false);
                        }
                    } else if (x < 0.0f && this.k.getXVelocity() < 0.0f && ((float) this.b) <= abs && abs <= ((float) this.c) && abs2 < abs) {
                        z = true;
                        if (z) {
                            g(this.l, this.p);
                            if (b(this.l, this.p)) {
                                this.q--;
                            }
                            a(false);
                        } else {
                            f(this.l, this.p);
                            d();
                        }
                    }
                }
                z = false;
                if (z) {
                    g(this.l, this.p);
                    if (b(this.l, this.p)) {
                        this.q--;
                    }
                    a(false);
                } else {
                    f(this.l, this.p);
                    d();
                }
            }
            e();
        }
        return false;
    }

    private void a(boolean z) {
        if (this.l != null) {
            a(this.l, this.p, z);
        }
    }

    private void a(View view, int i, boolean z) {
        if (this.f < 2) {
            this.f = this.e.h().getWidth();
        }
        View a = a(view);
        String str = "translationX";
        float[] fArr = new float[1];
        fArr[0] = z ? (float) this.f : (float) (-this.f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(a, str, fArr);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(a, "alpha", new float[]{0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(this.d);
        animatorSet.addListener(new a(view, i));
        animatorSet.start();
    }

    private void d() {
        if (this.l != null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.m, "translationX", new float[]{0.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.m, "alpha", new float[]{1.0f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(this.d);
            animatorSet.addListener(new b(this.l, this.p));
            animatorSet.start();
        }
    }

    private void e() {
        if (this.k != null) {
            this.k.recycle();
        }
        this.k = null;
        this.g = 0.0f;
        this.h = 0.0f;
        this.l = null;
        this.m = null;
        this.p = -1;
        this.i = false;
        this.j = false;
    }

    protected void e(View view, int i) {
    }

    protected void f(View view, int i) {
    }

    protected void a(View view, int i) {
    }

    protected void g(View view, int i) {
    }

    protected void d(View view) {
        View a = a(view);
        ViewHelper.setAlpha(a, 1.0f);
        ViewHelper.setTranslationX(a, 0.0f);
    }

    protected int c() {
        return this.u;
    }

    protected View a(View view) {
        return view;
    }

    protected View b(View view) {
        return view;
    }

    protected View c(View view) {
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
