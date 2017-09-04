package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;

/* compiled from: RemoteInput */
public class ac extends android.support.v4.app.ae.a {
    public static final android.support.v4.app.ae.a.a a = new android.support.v4.app.ae.a.a() {
    };
    private static final a g;
    private final String b;
    private final CharSequence c;
    private final CharSequence[] d;
    private final boolean e;
    private final Bundle f;

    /* compiled from: RemoteInput */
    interface a {
    }

    /* compiled from: RemoteInput */
    static class b implements a {
        b() {
        }
    }

    /* compiled from: RemoteInput */
    static class c implements a {
        c() {
        }
    }

    /* compiled from: RemoteInput */
    static class d implements a {
        d() {
        }
    }

    public String a() {
        return this.b;
    }

    public CharSequence b() {
        return this.c;
    }

    public CharSequence[] c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    public Bundle e() {
        return this.f;
    }

    static {
        if (VERSION.SDK_INT >= 20) {
            g = new b();
        } else if (VERSION.SDK_INT >= 16) {
            g = new d();
        } else {
            g = new c();
        }
    }
}
