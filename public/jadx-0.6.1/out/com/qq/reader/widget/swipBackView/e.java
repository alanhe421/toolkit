package com.qq.reader.widget.swipBackView;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.n;
import android.support.v4.view.x;
import android.support.v4.widget.h;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.qq.reader.common.monitor.debug.c;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* compiled from: ViewDragHelper */
public class e {
    private static final Interpolator B = new Interpolator() {
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    };
    private static int a = 400;
    private boolean A;
    private final Runnable C = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d(0);
        }
    };
    private int b;
    private int c;
    private int d = -1;
    private float[] e;
    private float[] f;
    private float[] g;
    private float[] h;
    private int[] i;
    private int[] j;
    private int[] k;
    private int l;
    private VelocityTracker m;
    private float n;
    private float o;
    private int p;
    private int q;
    private h r;
    private final a s;
    private View t;
    private boolean u;
    private final ViewGroup v;
    private WeakReference<Activity> w;
    private int x = 0;
    private int y = 0;
    private double z = 0.0d;

    /* compiled from: ViewDragHelper */
    public static abstract class a {
        public abstract boolean a(View view, int i);

        public void a(int i) {
        }

        public void a(View view, int i, int i2, int i3, int i4) {
        }

        public void b(View view, int i) {
        }

        public void a(View view, float f, float f2) {
        }

        public void a(int i, int i2) {
        }

        public boolean b(int i) {
            return false;
        }

        public void b(int i, int i2) {
        }

        public int c(int i) {
            return i;
        }

        public int a(View view) {
            return 0;
        }

        public int b(View view) {
            return 0;
        }

        public int a(View view, int i, int i2) {
            return 0;
        }

        public int b(View view, int i, int i2) {
            return 0;
        }
    }

    public boolean a() {
        return this.A;
    }

    public void a(boolean z) {
        if (this.A != z && z) {
            this.A = z;
            c.e("ViewDragHelper", "onDrawComplete->setLastActivityDrawComplete");
        }
    }

    private void e() {
        if (this.w != null && this.w.get() != null) {
            View decorView = ((Activity) this.w.get()).getWindow().getDecorView();
            if (decorView != null) {
                decorView.getLeft();
                c.e("ViewDragHelper", "onDrawComplete->mLastBackViewOffset:" + this.y);
                if (this.y == 0) {
                    decorView.setTranslationX((float) ((-decorView.getWidth()) / 4));
                    this.y = (-decorView.getWidth()) / 4;
                    this.x = this.y;
                    c.e("ViewDragHelper", "onDrawComplete->getTranslationX() = " + decorView.getTranslationX());
                }
            }
        }
    }

    public static e a(ViewGroup viewGroup, a aVar) {
        return new e(viewGroup.getContext(), viewGroup, aVar);
    }

    private e(Context context, ViewGroup viewGroup, a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (aVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.v = viewGroup;
            this.s = aVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context.getApplicationContext());
            this.p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.c = viewConfiguration.getScaledTouchSlop();
            this.n = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.o = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.r = h.a(context, B);
        }
    }

    public void a(WeakReference<Activity> weakReference) {
        this.w = weakReference;
    }

    public int b() {
        return this.y;
    }

    public void a(Context context, float f) {
        this.c = (int) ((1.0f / Math.max(0.0f, Math.min(1.0f, f))) * ((float) ViewConfiguration.get(context.getApplicationContext()).getScaledTouchSlop()));
    }

    public void a(float f) {
        this.o = f;
    }

    public void b(float f) {
        this.n = f;
    }

    public int c() {
        return this.b;
    }

    public void a(int i) {
        this.q = i;
    }

    public void b(int i) {
        this.p = i;
    }

    public void a(View view, int i) {
        if (view.getParent() != this.v) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.v + ")");
        }
        this.t = view;
        this.d = i;
        this.s.b(view, i);
        if (this.b != 1) {
            c.e("ViewDragHelper", "captureChildView->");
            e();
        }
        d(1);
    }

    public void d() {
        this.d = -1;
        f();
        if (this.m != null) {
            this.m.recycle();
            this.m = null;
        }
    }

    public boolean a(View view, int i, int i2) {
        this.t = view;
        this.d = -1;
        return a(i, i2, 0, 0);
    }

    public boolean a(int i, int i2) {
        if (this.u) {
            return a(i, i2, (int) x.a(this.m, this.d), (int) x.b(this.m, this.d));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    private boolean a(int i, int i2, int i3, int i4) {
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        c.e("ViewDragHelper", "forceSettleCapturedViewAt->finalLeft : " + i + "|startLeft : " + left + " |dx : " + i5);
        if (i5 == 0 && i6 == 0) {
            this.r.h();
            d(0);
            return false;
        }
        int a = a(this.t, i5, i6, i3, i4);
        if (!(this.w == null || this.w.get() == null)) {
            ValueAnimator valueAnimator;
            final ViewGroup viewGroup = (ViewGroup) ((Activity) this.w.get()).getWindow().getDecorView();
            c.e("ViewDragHelper", "onAnimationUpdate->mLastActivityOffset : " + viewGroup.getLeft());
            ValueAnimator ofInt;
            if (i5 > 0) {
                ofInt = ValueAnimator.ofInt(new int[]{this.y, 0});
                c.e("ViewDragHelper", "onAnimationUpdate->mLastBackViewOffset : " + this.y);
                ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
                    final /* synthetic */ e b;

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        c.e("ViewDragHelper", "right->onAnimationUpdate->getAnimatedValue : " + intValue);
                        c.e("ViewDragHelper", "right->onAnimationUpdate->offsetValue : " + (intValue - this.b.y));
                        viewGroup.setTranslationX((float) intValue);
                        c.e("ViewDragHelper", "right->onAnimationUpdate->mLastActivityOffset : " + viewGroup.getLeft());
                        this.b.y = intValue;
                        c.e("ViewDragHelper", "right->onAnimationUpdate->mLastBackViewOffset : " + this.b.y);
                    }
                });
                valueAnimator = ofInt;
            } else {
                ofInt = ValueAnimator.ofInt(new int[]{this.y, this.x});
                ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
                    final /* synthetic */ e b;

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        if (viewGroup.getTranslationX() != 0.0f) {
                            viewGroup.setTranslationX((float) intValue);
                        }
                        this.b.y = intValue;
                        c.e("ViewDragHelper", "left->onAnimationUpdate->mLastBackViewOffset : " + this.b.y);
                    }
                });
                valueAnimator = ofInt;
            }
            valueAnimator.setInterpolator(B);
            valueAnimator.setDuration((long) (a - 50));
            valueAnimator.start();
        }
        this.r.a(left, top, i5, i6, a);
        d(2);
        return true;
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        int b = b(i3, (int) this.o, (int) this.n);
        int b2 = b(i4, (int) this.o, (int) this.n);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) a(i2, b2, this.s.b(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) a(i, b, this.s.a(view)))));
    }

    private int a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.v.getWidth();
        int i4 = width / 2;
        float c = (c(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        if (i4 > 0) {
            width = Math.round(Math.abs(c / ((float) i4)) * 1000.0f) * 4;
        } else {
            width = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 200.0f);
        }
        return Math.min(width, a);
    }

    private int b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            return -i3;
        }
        return i3;
    }

    private float a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            return -f3;
        }
        return f3;
    }

    private float c(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    public boolean b(boolean z) {
        if (this.b == 2) {
            boolean a;
            boolean g = this.r.g();
            int b = this.r.b();
            int c = this.r.c();
            int left = b - this.t.getLeft();
            int top = c - this.t.getTop();
            if (left != 0) {
                this.t.offsetLeftAndRight(left);
            }
            if (top != 0) {
                this.t.offsetTopAndBottom(top);
            }
            if (!(left == 0 && top == 0)) {
                this.s.a(this.t, b, c, left, top);
            }
            if (g && b == this.r.d() && c == this.r.e()) {
                this.r.h();
                a = this.r.a();
            } else {
                a = g;
            }
            if (!a) {
                if (z) {
                    this.v.post(this.C);
                } else {
                    d(0);
                }
            }
        }
        return this.b == 2;
    }

    private void a(float f, float f2) {
        this.u = true;
        this.s.a(this.t, f, f2);
        this.u = false;
        if (this.b == 1) {
            d(0);
        }
    }

    private void f() {
        if (this.e != null) {
            Arrays.fill(this.e, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0.0f);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            Arrays.fill(this.k, 0);
            this.l = 0;
        }
    }

    private void e(int i) {
        if (this.e != null) {
            this.e[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
            this.h[i] = 0.0f;
            this.i[i] = 0;
            this.j[i] = 0;
            this.k[i] = 0;
            this.l &= (1 << i) ^ -1;
        }
    }

    private void f(int i) {
        if (this.e == null || this.e.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.e != null) {
                System.arraycopy(this.e, 0, obj, 0, this.e.length);
                System.arraycopy(this.f, 0, obj2, 0, this.f.length);
                System.arraycopy(this.g, 0, obj3, 0, this.g.length);
                System.arraycopy(this.h, 0, obj4, 0, this.h.length);
                System.arraycopy(this.i, 0, obj5, 0, this.i.length);
                System.arraycopy(this.j, 0, obj6, 0, this.j.length);
                System.arraycopy(this.k, 0, obj7, 0, this.k.length);
            }
            this.e = obj;
            this.f = obj2;
            this.g = obj3;
            this.h = obj4;
            this.i = obj5;
            this.j = obj6;
            this.k = obj7;
        }
    }

    private void a(float f, float f2, int i) {
        f(i);
        float[] fArr = this.e;
        this.g[i] = f;
        fArr[i] = f;
        fArr = this.f;
        this.h[i] = f2;
        fArr[i] = f2;
        this.i[i] = f((int) f, (int) f2);
        this.l |= 1 << i;
    }

    private void c(MotionEvent motionEvent) {
        int c = n.c(motionEvent);
        for (int i = 0; i < c; i++) {
            int b = n.b(motionEvent, i);
            float c2 = n.c(motionEvent, i);
            float d = n.d(motionEvent, i);
            if (this.g != null) {
                this.g[b] = c2;
                this.h[b] = d;
            }
        }
    }

    public boolean c(int i) {
        return (this.l & (1 << i)) != 0;
    }

    void d(int i) {
        if (this.b != i) {
            this.b = i;
            this.s.a(i);
            if (i == 0) {
                this.t = null;
            }
        }
    }

    boolean b(View view, int i) {
        if (view == this.t && this.d == i) {
            return true;
        }
        if (view == null || !this.s.a(view, i)) {
            return false;
        }
        this.d = i;
        a(view, i);
        return true;
    }

    public boolean a(MotionEvent motionEvent) {
        int a = n.a(motionEvent);
        int b = n.b(motionEvent);
        if (a == 0) {
            d();
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        float y;
        int b2;
        switch (a) {
            case 0:
                float x = motionEvent.getX();
                y = motionEvent.getY();
                b2 = n.b(motionEvent, 0);
                a(x, y, b2);
                View e = e((int) x, (int) y);
                if (e == this.t && this.b == 2) {
                    b(e, b2);
                }
                a = this.i[b2];
                if ((this.q & a) != 0) {
                    this.s.a(a & this.q, b2);
                    break;
                }
                break;
            case 1:
            case 3:
                d();
                break;
            case 2:
                b = n.c(motionEvent);
                for (a = 0; a < b; a++) {
                    b2 = n.b(motionEvent, a);
                    float c = n.c(motionEvent, a);
                    float d = n.d(motionEvent, a);
                    if (this.e != null && this.f != null && b2 < this.e.length && b2 < this.f.length) {
                        float f = c - this.e[b2];
                        float f2 = d - this.f[b2];
                        b(f, f2, b2);
                        if (this.b != 1) {
                            View e2 = e((int) c, (int) d);
                            if (e2 != null && a(e2, f, f2) && b(e2, b2)) {
                            }
                        }
                        c(motionEvent);
                        break;
                    }
                }
                c(motionEvent);
            case 5:
                a = n.b(motionEvent, b);
                float c2 = n.c(motionEvent, b);
                y = n.d(motionEvent, b);
                a(c2, y, a);
                if (this.b != 0) {
                    if (this.b == 2) {
                        View e3 = e((int) c2, (int) y);
                        if (e3 == this.t) {
                            b(e3, a);
                            break;
                        }
                    }
                }
                b = this.i[a];
                if ((this.q & b) != 0) {
                    this.s.a(b & this.q, a);
                    break;
                }
                break;
            case 6:
                e(n.b(motionEvent, b));
                break;
        }
        if (this.b == 1) {
            return true;
        }
        return false;
    }

    public void b(MotionEvent motionEvent) {
        int i = 0;
        int a = n.a(motionEvent);
        int b = n.b(motionEvent);
        if (a == 0) {
            d();
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        float x;
        float y;
        View e;
        int i2;
        switch (a) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                i = n.b(motionEvent, 0);
                e = e((int) x, (int) y);
                a(x, y, i);
                b(e, i);
                i2 = this.i[i];
                if ((this.q & i2) != 0) {
                    this.s.a(i2 & this.q, i);
                    return;
                }
                return;
            case 1:
                if (this.b == 1) {
                    g();
                }
                d();
                return;
            case 2:
                if (this.b == 1 && a()) {
                    i = n.a(motionEvent, this.d);
                    x = n.c(motionEvent, i);
                    i2 = (int) (x - this.g[this.d]);
                    i = (int) (n.d(motionEvent, i) - this.h[this.d]);
                    b(this.t.getLeft() + i2, this.t.getTop() + i, i2, i);
                    c(motionEvent);
                    return;
                }
                i2 = n.c(motionEvent);
                while (i < i2) {
                    a = n.b(motionEvent, i);
                    float c = n.c(motionEvent, i);
                    float d = n.d(motionEvent, i);
                    float f = c - this.e[a];
                    float f2 = d - this.f[a];
                    b(f, f2, a);
                    if (this.b != 1) {
                        e = e((int) c, (int) d);
                        if (!a(e, f, f2) || !b(e, a)) {
                            i++;
                        }
                    }
                    c(motionEvent);
                    return;
                }
                c(motionEvent);
                return;
            case 3:
                if (this.b == 1) {
                    a(0.0f, 0.0f);
                }
                d();
                return;
            case 5:
                i = n.b(motionEvent, b);
                x = n.c(motionEvent, b);
                y = n.d(motionEvent, b);
                a(x, y, i);
                if (this.b == 0) {
                    b(e((int) x, (int) y), i);
                    i2 = this.i[i];
                    if ((this.q & i2) != 0) {
                        this.s.a(i2 & this.q, i);
                        return;
                    }
                    return;
                } else if (d((int) x, (int) y)) {
                    b(this.t, i);
                    return;
                } else {
                    return;
                }
            case 6:
                a = n.b(motionEvent, b);
                if (this.b == 1 && a == this.d) {
                    b = n.c(motionEvent);
                    while (i < b) {
                        int b2 = n.b(motionEvent, i);
                        if (b2 != this.d) {
                            if (e((int) n.c(motionEvent, i), (int) n.d(motionEvent, i)) == this.t && b(this.t, b2)) {
                                i = this.d;
                                if (i == -1) {
                                    g();
                                }
                            }
                        }
                        i++;
                    }
                    i = -1;
                    if (i == -1) {
                        g();
                    }
                }
                e(a);
                return;
            default:
                return;
        }
    }

    private void b(float f, float f2, int i) {
        int i2 = 1;
        if (!a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] | i2;
            this.s.b(i2, i);
        }
    }

    private boolean a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.i[i] & i2) != i2 || (this.q & i2) == 0 || (this.k[i] & i2) == i2 || (this.j[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.c) && abs2 <= ((float) this.c)) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.s.b(i2)) {
            int[] iArr = this.k;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.j[i] & i2) != 0 || abs <= ((float) this.c)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z;
        boolean z2;
        if (this.s.a(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.s.b(view) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            if ((f * f) + (f2 * f2) <= ((float) (this.c * this.c))) {
                return false;
            }
            return true;
        } else if (z) {
            if (Math.abs(f) <= ((float) this.c)) {
                return false;
            }
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.c)) {
                return false;
            }
            return true;
        }
    }

    public boolean b(int i, int i2) {
        if (!c(i2)) {
            return false;
        }
        boolean z;
        boolean z2 = (i & 1) == 1;
        if ((i & 2) == 2) {
            z = true;
        } else {
            z = false;
        }
        float f = this.g[i2] - this.e[i2];
        float f2 = this.h[i2] - this.f[i2];
        if (z2 && z) {
            if ((f * f) + (f2 * f2) <= ((float) (this.c * this.c))) {
                return false;
            }
            return true;
        } else if (z2) {
            if (Math.abs(f) <= ((float) this.c)) {
                return false;
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.c)) {
                return false;
            }
            return true;
        }
    }

    public boolean c(int i, int i2) {
        return c(i2) && (this.i[i2] & i) != 0;
    }

    private void g() {
        this.m.computeCurrentVelocity(1000, this.n);
        a(a(x.a(this.m, this.d), this.o, this.n), a(x.b(this.m, this.d), this.o, this.n));
    }

    private void b(int i, int i2, int i3, int i4) {
        int a;
        int b;
        int left = this.t.getLeft();
        int top = this.t.getTop();
        if (i3 != 0) {
            a = this.s.a(this.t, i, i3);
            this.t.offsetLeftAndRight(a - left);
        } else {
            a = i;
        }
        if (i4 != 0) {
            b = this.s.b(this.t, i2, i4);
            this.t.offsetTopAndBottom(b - top);
        } else {
            b = i2;
        }
        if (!(this.w == null || this.w.get() == null)) {
            View decorView = ((Activity) this.w.get()).getWindow().getDecorView();
            if (i3 != 0) {
                c.a("ViewDragHelper", "TranslationX = " + decorView.getTranslationX());
                if (this.z >= 1.0d || this.z < -1.0d) {
                    decorView.setTranslationX((float) ((this.y + ((a - left) / 4)) + ((int) this.z)));
                    c.e("ViewDragHelper", "mOffsetSum clampedX = " + a + ", offset = " + this.y + ((a - left) / 4) + ((int) this.z));
                    this.y += ((a - left) / 4) + ((int) this.z);
                    this.z -= (double) ((int) this.z);
                } else {
                    decorView.setTranslationX((float) (this.y + ((a - left) / 4)));
                    c.e("ViewDragHelper", "mOffsetSum clampedX = " + a + ", offset = " + this.y + ((a - left) / 4));
                    this.y += (a - left) / 4;
                }
                this.z += (double) ((((float) (a - left)) / 4.0f) - ((float) ((a - left) / 4)));
            }
        }
        if (i3 != 0 || i4 != 0) {
            this.s.a(this.t, a, b, a - left, b - top);
        }
    }

    public boolean d(int i, int i2) {
        return b(this.t, i, i2);
    }

    public boolean b(View view, int i, int i2) {
        if (view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public View e(int i, int i2) {
        for (int childCount = this.v.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.v.getChildAt(this.s.c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int f(int i, int i2) {
        int i3 = 0;
        if (i < this.v.getLeft() + this.p) {
            i3 = 1;
        }
        if (i2 < this.v.getTop() + this.p) {
            i3 = 4;
        }
        if (i > this.v.getRight() - this.p) {
            i3 = 2;
        }
        if (i2 > this.v.getBottom() - this.p) {
            return 8;
        }
        return i3;
    }
}
