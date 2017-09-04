package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

/* compiled from: ViewCompat */
public class z {
    static final m a;

    /* compiled from: ViewCompat */
    interface m {
        int a(int i, int i2, int i3);

        int a(View view);

        void a(View view, float f);

        void a(View view, int i, int i2, int i3, int i4);

        void a(View view, int i, Paint paint);

        void a(View view, Paint paint);

        void a(View view, a aVar);

        void a(View view, Runnable runnable);

        void a(View view, Runnable runnable, long j);

        void a(View view, boolean z);

        void a(ViewGroup viewGroup, boolean z);

        boolean a(View view, int i);

        void b(View view, float f);

        boolean b(View view);

        boolean b(View view, int i);

        void c(View view, float f);

        void c(View view, int i);

        boolean c(View view);

        void d(View view);

        void d(View view, float f);

        int e(View view);

        void e(View view, float f);

        float f(View view);

        void f(View view, float f);

        int g(View view);

        void g(View view, float f);

        int h(View view);

        void h(View view, float f);

        ViewParent i(View view);

        boolean j(View view);

        int k(View view);

        boolean l(View view);

        float m(View view);

        float n(View view);

        float o(View view);

        int p(View view);

        int q(View view);

        ao r(View view);

        float s(View view);

        boolean t(View view);

        boolean u(View view);

        void v(View view);
    }

    /* compiled from: ViewCompat */
    static class a implements m {
        WeakHashMap<View, ao> a = null;

        a() {
        }

        public boolean a(View view, int i) {
            return (view instanceof w) && a((w) view, i);
        }

        public boolean b(View view, int i) {
            return (view instanceof w) && b((w) view, i);
        }

        public int a(View view) {
            return 2;
        }

        public void a(View view, a aVar) {
        }

        public boolean b(View view) {
            return false;
        }

        public boolean c(View view) {
            return false;
        }

        public void d(View view) {
            view.invalidate();
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            view.invalidate(i, i2, i3, i4);
        }

        public void a(View view, Runnable runnable) {
            view.postDelayed(runnable, a());
        }

        public void a(View view, Runnable runnable, long j) {
            view.postDelayed(runnable, a() + j);
        }

        long a() {
            return 10;
        }

        public int e(View view) {
            return 0;
        }

        public void c(View view, int i) {
        }

        public float f(View view) {
            return 1.0f;
        }

        public void a(View view, int i, Paint paint) {
        }

        public int g(View view) {
            return 0;
        }

        public void a(View view, Paint paint) {
        }

        public int h(View view) {
            return 0;
        }

        public ViewParent i(View view) {
            return view.getParent();
        }

        public boolean j(View view) {
            Drawable background = view.getBackground();
            if (background == null || background.getOpacity() != -1) {
                return false;
            }
            return true;
        }

        public int a(int i, int i2, int i3) {
            return View.resolveSize(i, i2);
        }

        public int k(View view) {
            return 0;
        }

        public boolean l(View view) {
            return true;
        }

        public float m(View view) {
            return 0.0f;
        }

        public float n(View view) {
            return 0.0f;
        }

        public float o(View view) {
            return 0.0f;
        }

        public int p(View view) {
            return aa.a(view);
        }

        public int q(View view) {
            return aa.b(view);
        }

        public ao r(View view) {
            return new ao(view);
        }

        public void a(View view, float f) {
        }

        public void b(View view, float f) {
        }

        public void c(View view, float f) {
        }

        public void d(View view, float f) {
        }

        public void e(View view, float f) {
        }

        public void f(View view, float f) {
        }

        public void g(View view, float f) {
        }

        public void h(View view, float f) {
        }

        public float s(View view) {
            return 0.0f;
        }

        public void a(ViewGroup viewGroup, boolean z) {
        }

        public boolean t(View view) {
            return false;
        }

        public void a(View view, boolean z) {
        }

        public boolean u(View view) {
            if (view instanceof r) {
                return ((r) view).isNestedScrollingEnabled();
            }
            return false;
        }

        private boolean a(w wVar, int i) {
            int computeHorizontalScrollOffset = wVar.computeHorizontalScrollOffset();
            int computeHorizontalScrollRange = wVar.computeHorizontalScrollRange() - wVar.computeHorizontalScrollExtent();
            if (computeHorizontalScrollRange == 0) {
                return false;
            }
            if (i < 0) {
                if (computeHorizontalScrollOffset <= 0) {
                    return false;
                }
                return true;
            } else if (computeHorizontalScrollOffset >= computeHorizontalScrollRange - 1) {
                return false;
            } else {
                return true;
            }
        }

        private boolean b(w wVar, int i) {
            int computeVerticalScrollOffset = wVar.computeVerticalScrollOffset();
            int computeVerticalScrollRange = wVar.computeVerticalScrollRange() - wVar.computeVerticalScrollExtent();
            if (computeVerticalScrollRange == 0) {
                return false;
            }
            if (i < 0) {
                if (computeVerticalScrollOffset <= 0) {
                    return false;
                }
                return true;
            } else if (computeVerticalScrollOffset >= computeVerticalScrollRange - 1) {
                return false;
            } else {
                return true;
            }
        }

        public void v(View view) {
            if (view instanceof r) {
                ((r) view).stopNestedScroll();
            }
        }
    }

    /* compiled from: ViewCompat */
    static class b extends a {
        b() {
        }

        public boolean j(View view) {
            return ab.a(view);
        }

        public void a(ViewGroup viewGroup, boolean z) {
            ab.a(viewGroup, z);
        }
    }

    /* compiled from: ViewCompat */
    static class c extends b {
        c() {
        }

        public int a(View view) {
            return ac.a(view);
        }
    }

    /* compiled from: ViewCompat */
    static class d extends c {
        d() {
        }

        long a() {
            return ad.a();
        }

        public float f(View view) {
            return ad.a(view);
        }

        public void a(View view, int i, Paint paint) {
            ad.a(view, i, paint);
        }

        public int g(View view) {
            return ad.b(view);
        }

        public void a(View view, Paint paint) {
            a(view, g(view), paint);
            view.invalidate();
        }

        public int a(int i, int i2, int i3) {
            return ad.a(i, i2, i3);
        }

        public int k(View view) {
            return ad.c(view);
        }

        public float m(View view) {
            return ad.d(view);
        }

        public float n(View view) {
            return ad.e(view);
        }

        public void a(View view, float f) {
            ad.a(view, f);
        }

        public void b(View view, float f) {
            ad.b(view, f);
        }

        public void c(View view, float f) {
            ad.c(view, f);
        }

        public void f(View view, float f) {
            ad.d(view, f);
        }

        public void g(View view, float f) {
            ad.e(view, f);
        }

        public void d(View view, float f) {
            ad.f(view, f);
        }

        public void e(View view, float f) {
            ad.g(view, f);
        }

        public float o(View view) {
            return ad.f(view);
        }

        public void a(View view, boolean z) {
            ad.a(view, z);
        }
    }

    /* compiled from: ViewCompat */
    static class f extends d {
        static Field b;
        static boolean c = false;

        f() {
        }

        public boolean a(View view, int i) {
            return ae.a(view, i);
        }

        public boolean b(View view, int i) {
            return ae.b(view, i);
        }

        public void a(View view, a aVar) {
            ae.a(view, aVar == null ? null : aVar.a());
        }

        public boolean b(View view) {
            boolean z = true;
            if (c) {
                return false;
            }
            if (b == null) {
                try {
                    b = View.class.getDeclaredField("mAccessibilityDelegate");
                    b.setAccessible(true);
                } catch (Throwable th) {
                    c = true;
                    return false;
                }
            }
            try {
                if (b.get(view) == null) {
                    z = false;
                }
                return z;
            } catch (Throwable th2) {
                c = true;
                return false;
            }
        }

        public ao r(View view) {
            if (this.a == null) {
                this.a = new WeakHashMap();
            }
            ao aoVar = (ao) this.a.get(view);
            if (aoVar != null) {
                return aoVar;
            }
            aoVar = new ao(view);
            this.a.put(view, aoVar);
            return aoVar;
        }
    }

    /* compiled from: ViewCompat */
    static class e extends f {
        e() {
        }
    }

    /* compiled from: ViewCompat */
    static class g extends e {
        g() {
        }

        public boolean c(View view) {
            return af.a(view);
        }

        public void d(View view) {
            af.b(view);
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            af.a(view, i, i2, i3, i4);
        }

        public void a(View view, Runnable runnable) {
            af.a(view, runnable);
        }

        public void a(View view, Runnable runnable, long j) {
            af.a(view, runnable, j);
        }

        public int e(View view) {
            return af.c(view);
        }

        public void c(View view, int i) {
            if (i == 4) {
                i = 2;
            }
            af.a(view, i);
        }

        public ViewParent i(View view) {
            return af.d(view);
        }

        public int p(View view) {
            return af.e(view);
        }

        public int q(View view) {
            return af.f(view);
        }

        public boolean t(View view) {
            return af.g(view);
        }

        public boolean l(View view) {
            return af.h(view);
        }
    }

    /* compiled from: ViewCompat */
    static class h extends g {
        h() {
        }

        public void a(View view, Paint paint) {
            ag.a(view, paint);
        }

        public int h(View view) {
            return ag.a(view);
        }
    }

    /* compiled from: ViewCompat */
    static class i extends h {
        i() {
        }
    }

    /* compiled from: ViewCompat */
    static class j extends i {
        j() {
        }

        public void c(View view, int i) {
            af.a(view, i);
        }
    }

    /* compiled from: ViewCompat */
    static class k extends j {
        k() {
        }

        public void h(View view, float f) {
            ah.a(view, f);
        }

        public float s(View view) {
            return ah.a(view);
        }

        public boolean u(View view) {
            return ah.b(view);
        }

        public void v(View view) {
            ah.c(view);
        }
    }

    /* compiled from: ViewCompat */
    static class l extends k {
        l() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            a = new l();
        } else if (i >= 21) {
            a = new k();
        } else if (i >= 19) {
            a = new j();
        } else if (i >= 17) {
            a = new h();
        } else if (i >= 16) {
            a = new g();
        } else if (i >= 15) {
            a = new e();
        } else if (i >= 14) {
            a = new f();
        } else if (i >= 11) {
            a = new d();
        } else if (i >= 9) {
            a = new c();
        } else if (i >= 7) {
            a = new b();
        } else {
            a = new a();
        }
    }

    public static boolean a(View view, int i) {
        return a.a(view, i);
    }

    public static boolean b(View view, int i) {
        return a.b(view, i);
    }

    public static int a(View view) {
        return a.a(view);
    }

    public static void a(View view, a aVar) {
        a.a(view, aVar);
    }

    public static boolean b(View view) {
        return a.b(view);
    }

    public static boolean c(View view) {
        return a.c(view);
    }

    public static void d(View view) {
        a.d(view);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        a.a(view, i, i2, i3, i4);
    }

    public static void a(View view, Runnable runnable) {
        a.a(view, runnable);
    }

    public static void a(View view, Runnable runnable, long j) {
        a.a(view, runnable, j);
    }

    public static int e(View view) {
        return a.e(view);
    }

    public static void c(View view, int i) {
        a.c(view, i);
    }

    public static float f(View view) {
        return a.f(view);
    }

    public static void a(View view, int i, Paint paint) {
        a.a(view, i, paint);
    }

    public static int g(View view) {
        return a.g(view);
    }

    public static void a(View view, Paint paint) {
        a.a(view, paint);
    }

    public static int h(View view) {
        return a.h(view);
    }

    public static ViewParent i(View view) {
        return a.i(view);
    }

    public static boolean j(View view) {
        return a.j(view);
    }

    public static int a(int i, int i2, int i3) {
        return a.a(i, i2, i3);
    }

    public static int k(View view) {
        return a.k(view);
    }

    public static float l(View view) {
        return a.m(view);
    }

    public static float m(View view) {
        return a.n(view);
    }

    public static int n(View view) {
        return a.p(view);
    }

    public static int o(View view) {
        return a.q(view);
    }

    public static ao p(View view) {
        return a.r(view);
    }

    public static void a(View view, float f) {
        a.a(view, f);
    }

    public static void b(View view, float f) {
        a.b(view, f);
    }

    public static void c(View view, float f) {
        a.c(view, f);
    }

    public static void d(View view, float f) {
        a.f(view, f);
    }

    public static void e(View view, float f) {
        a.g(view, f);
    }

    public static void f(View view, float f) {
        a.d(view, f);
    }

    public static void g(View view, float f) {
        a.e(view, f);
    }

    public static float q(View view) {
        return a.o(view);
    }

    public static void h(View view, float f) {
        a.h(view, f);
    }

    public static float r(View view) {
        return a.s(view);
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        a.a(viewGroup, z);
    }

    public static boolean s(View view) {
        return a.t(view);
    }

    public static void a(View view, boolean z) {
        a.a(view, z);
    }

    public static boolean t(View view) {
        return a.l(view);
    }

    public static boolean u(View view) {
        return a.u(view);
    }

    public static void v(View view) {
        a.v(view);
    }
}
