package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewGroup;

/* compiled from: ViewGroupCompat */
public class ak {
    static final c a;

    /* compiled from: ViewGroupCompat */
    interface c {
        void a(ViewGroup viewGroup, boolean z);
    }

    /* compiled from: ViewGroupCompat */
    static class f implements c {
        f() {
        }

        public void a(ViewGroup viewGroup, boolean z) {
        }
    }

    /* compiled from: ViewGroupCompat */
    static class a extends f {
        a() {
        }

        public void a(ViewGroup viewGroup, boolean z) {
            al.a(viewGroup, z);
        }
    }

    /* compiled from: ViewGroupCompat */
    static class b extends a {
        b() {
        }
    }

    /* compiled from: ViewGroupCompat */
    static class d extends b {
        d() {
        }
    }

    /* compiled from: ViewGroupCompat */
    static class e extends d {
        e() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            a = new e();
        } else if (i >= 18) {
            a = new d();
        } else if (i >= 14) {
            a = new b();
        } else if (i >= 11) {
            a = new a();
        } else {
            a = new f();
        }
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        a.a(viewGroup, z);
    }
}
