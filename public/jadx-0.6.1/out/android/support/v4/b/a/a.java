package android.support.v4.b.a;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

/* compiled from: DrawableCompat */
public class a {
    static final b a;

    /* compiled from: DrawableCompat */
    interface b {
        void a(Drawable drawable, int i);

        void a(Drawable drawable, ColorStateList colorStateList);

        boolean a(Drawable drawable);

        Drawable b(Drawable drawable);
    }

    /* compiled from: DrawableCompat */
    static class a implements b {
        a() {
        }

        public boolean a(Drawable drawable) {
            return false;
        }

        public void a(Drawable drawable, ColorStateList colorStateList) {
            d.a(drawable, colorStateList);
        }

        public Drawable b(Drawable drawable) {
            return d.a(drawable);
        }

        public void a(Drawable drawable, int i) {
        }
    }

    /* compiled from: DrawableCompat */
    static class c extends a {
        c() {
        }

        public Drawable b(Drawable drawable) {
            return e.a(drawable);
        }
    }

    /* compiled from: DrawableCompat */
    static class d extends c {
        d() {
        }

        public void a(Drawable drawable, int i) {
            f.a(drawable, i);
        }
    }

    /* compiled from: DrawableCompat */
    static class e extends d {
        e() {
        }

        public boolean a(Drawable drawable) {
            return g.a(drawable);
        }

        public Drawable b(Drawable drawable) {
            return g.b(drawable);
        }
    }

    /* compiled from: DrawableCompat */
    static class f extends e {
        f() {
        }

        public void a(Drawable drawable, ColorStateList colorStateList) {
            h.a(drawable, colorStateList);
        }

        public Drawable b(Drawable drawable) {
            return h.a(drawable);
        }
    }

    /* compiled from: DrawableCompat */
    static class g extends f {
        g() {
        }

        public Drawable b(Drawable drawable) {
            return b.a(drawable);
        }
    }

    /* compiled from: DrawableCompat */
    static class h extends g {
        h() {
        }

        public void a(Drawable drawable, int i) {
            c.a(drawable, i);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            a = new h();
        } else if (i >= 22) {
            a = new g();
        } else if (i >= 21) {
            a = new f();
        } else if (i >= 19) {
            a = new e();
        } else if (i >= 17) {
            a = new d();
        } else if (i >= 11) {
            a = new c();
        } else {
            a = new a();
        }
    }

    public static boolean a(Drawable drawable) {
        return a.a(drawable);
    }

    public static void a(Drawable drawable, ColorStateList colorStateList) {
        a.a(drawable, colorStateList);
    }

    public static Drawable b(Drawable drawable) {
        return a.b(drawable);
    }

    public static void a(Drawable drawable, int i) {
        a.a(drawable, i);
    }
}
