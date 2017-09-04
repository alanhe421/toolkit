package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.LayoutInflater;

/* compiled from: LayoutInflaterCompat */
public class i {
    static final a a;

    /* compiled from: LayoutInflaterCompat */
    interface a {
        void a(LayoutInflater layoutInflater, m mVar);
    }

    /* compiled from: LayoutInflaterCompat */
    static class b implements a {
        b() {
        }

        public void a(LayoutInflater layoutInflater, m mVar) {
            j.a(layoutInflater, mVar);
        }
    }

    /* compiled from: LayoutInflaterCompat */
    static class c extends b {
        c() {
        }

        public void a(LayoutInflater layoutInflater, m mVar) {
            k.a(layoutInflater, mVar);
        }
    }

    /* compiled from: LayoutInflaterCompat */
    static class d extends c {
        d() {
        }

        public void a(LayoutInflater layoutInflater, m mVar) {
            l.a(layoutInflater, mVar);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            a = new d();
        } else if (i >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }

    public static void a(LayoutInflater layoutInflater, m mVar) {
        a.a(layoutInflater, mVar);
    }
}
