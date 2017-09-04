package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* compiled from: ScrollerCompat */
public class h {
    Object a;
    a b;

    /* compiled from: ScrollerCompat */
    interface a {
        Object a(Context context, Interpolator interpolator);

        void a(Object obj, int i, int i2, int i3, int i4);

        void a(Object obj, int i, int i2, int i3, int i4, int i5);

        void a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

        void a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

        boolean a(Object obj);

        boolean a(Object obj, int i, int i2, int i3, int i4, int i5, int i6);

        int b(Object obj);

        int c(Object obj);

        float d(Object obj);

        boolean e(Object obj);

        void f(Object obj);

        int g(Object obj);

        int h(Object obj);
    }

    /* compiled from: ScrollerCompat */
    static class b implements a {
        b() {
        }

        public Object a(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        public boolean a(Object obj) {
            return ((Scroller) obj).isFinished();
        }

        public int b(Object obj) {
            return ((Scroller) obj).getCurrX();
        }

        public int c(Object obj) {
            return ((Scroller) obj).getCurrY();
        }

        public float d(Object obj) {
            return 0.0f;
        }

        public boolean e(Object obj) {
            return ((Scroller) obj).computeScrollOffset();
        }

        public void a(Object obj, int i, int i2, int i3, int i4) {
            ((Scroller) obj).startScroll(i, i2, i3, i4);
        }

        public void a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
        }

        public void a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            ((Scroller) obj).fling(i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void f(Object obj) {
            ((Scroller) obj).abortAnimation();
        }

        public int g(Object obj) {
            return ((Scroller) obj).getFinalX();
        }

        public int h(Object obj) {
            return ((Scroller) obj).getFinalY();
        }

        public boolean a(Object obj, int i, int i2, int i3, int i4, int i5, int i6) {
            return false;
        }
    }

    /* compiled from: ScrollerCompat */
    static class c implements a {
        c() {
        }

        public Object a(Context context, Interpolator interpolator) {
            return i.a(context, interpolator);
        }

        public boolean a(Object obj) {
            return i.a(obj);
        }

        public int b(Object obj) {
            return i.b(obj);
        }

        public int c(Object obj) {
            return i.c(obj);
        }

        public float d(Object obj) {
            return 0.0f;
        }

        public boolean e(Object obj) {
            return i.d(obj);
        }

        public void a(Object obj, int i, int i2, int i3, int i4) {
            i.a(obj, i, i2, i3, i4);
        }

        public void a(Object obj, int i, int i2, int i3, int i4, int i5) {
            i.a(obj, i, i2, i3, i4, i5);
        }

        public void a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            i.a(obj, i, i2, i3, i4, i5, i6, i7, i8);
        }

        public void a(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            i.a(obj, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
        }

        public void f(Object obj) {
            i.e(obj);
        }

        public int g(Object obj) {
            return i.f(obj);
        }

        public int h(Object obj) {
            return i.g(obj);
        }

        public boolean a(Object obj, int i, int i2, int i3, int i4, int i5, int i6) {
            return i.a(obj, i, i2, i3, i4, i5, i6);
        }
    }

    /* compiled from: ScrollerCompat */
    static class d extends c {
        d() {
        }

        public float d(Object obj) {
            return j.a(obj);
        }
    }

    public static h a(Context context, Interpolator interpolator) {
        return new h(context, interpolator);
    }

    h(Context context, Interpolator interpolator) {
        this(VERSION.SDK_INT, context, interpolator);
    }

    private h(int i, Context context, Interpolator interpolator) {
        if (i >= 14) {
            this.b = new d();
        } else if (i >= 9) {
            this.b = new c();
        } else {
            this.b = new b();
        }
        this.a = this.b.a(context, interpolator);
    }

    public boolean a() {
        return this.b.a(this.a);
    }

    public int b() {
        return this.b.b(this.a);
    }

    public int c() {
        return this.b.c(this.a);
    }

    public int d() {
        return this.b.g(this.a);
    }

    public int e() {
        return this.b.h(this.a);
    }

    public float f() {
        return this.b.d(this.a);
    }

    public boolean g() {
        return this.b.e(this.a);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.b.a(this.a, i, i2, i3, i4);
    }

    public void a(int i, int i2, int i3, int i4, int i5) {
        this.b.a(this.a, i, i2, i3, i4, i5);
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.b.a(this.a, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.b.a(this.a, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    public boolean a(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.b.a(this.a, i, i2, i3, i4, i5, i6);
    }

    public void h() {
        this.b.f(this.a);
    }
}
