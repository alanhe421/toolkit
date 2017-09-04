package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;

/* compiled from: EdgeEffectCompat */
public class d {
    private static final c b;
    private Object a;

    /* compiled from: EdgeEffectCompat */
    interface c {
        Object a(Context context);

        void a(Object obj, int i, int i2);

        boolean a(Object obj);

        boolean a(Object obj, float f);

        boolean a(Object obj, float f, float f2);

        boolean a(Object obj, int i);

        boolean a(Object obj, Canvas canvas);

        void b(Object obj);

        boolean c(Object obj);
    }

    /* compiled from: EdgeEffectCompat */
    static class a implements c {
        a() {
        }

        public Object a(Context context) {
            return null;
        }

        public void a(Object obj, int i, int i2) {
        }

        public boolean a(Object obj) {
            return true;
        }

        public void b(Object obj) {
        }

        public boolean a(Object obj, float f) {
            return false;
        }

        public boolean c(Object obj) {
            return false;
        }

        public boolean a(Object obj, int i) {
            return false;
        }

        public boolean a(Object obj, Canvas canvas) {
            return false;
        }

        public boolean a(Object obj, float f, float f2) {
            return false;
        }
    }

    /* compiled from: EdgeEffectCompat */
    static class b implements c {
        b() {
        }

        public Object a(Context context) {
            return e.a(context);
        }

        public void a(Object obj, int i, int i2) {
            e.a(obj, i, i2);
        }

        public boolean a(Object obj) {
            return e.a(obj);
        }

        public void b(Object obj) {
            e.b(obj);
        }

        public boolean a(Object obj, float f) {
            return e.a(obj, f);
        }

        public boolean c(Object obj) {
            return e.c(obj);
        }

        public boolean a(Object obj, int i) {
            return e.a(obj, i);
        }

        public boolean a(Object obj, Canvas canvas) {
            return e.a(obj, canvas);
        }

        public boolean a(Object obj, float f, float f2) {
            return e.a(obj, f);
        }
    }

    /* compiled from: EdgeEffectCompat */
    static class d extends b {
        d() {
        }

        public boolean a(Object obj, float f, float f2) {
            return f.a(obj, f, f2);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            b = new d();
        } else if (VERSION.SDK_INT >= 14) {
            b = new b();
        } else {
            b = new a();
        }
    }

    public d(Context context) {
        this.a = b.a(context);
    }

    public void a(int i, int i2) {
        b.a(this.a, i, i2);
    }

    public boolean a() {
        return b.a(this.a);
    }

    public void b() {
        b.b(this.a);
    }

    public boolean a(float f) {
        return b.a(this.a, f);
    }

    public boolean a(float f, float f2) {
        return b.a(this.a, f, f2);
    }

    public boolean c() {
        return b.c(this.a);
    }

    public boolean a(int i) {
        return b.a(this.a, i);
    }

    public boolean a(Canvas canvas) {
        return b.a(this.a, canvas);
    }
}
