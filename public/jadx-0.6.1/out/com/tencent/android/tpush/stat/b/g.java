package com.tencent.android.tpush.stat.b;

import android.content.Context;
import com.tencent.android.tpush.stat.a.h;

/* compiled from: ProGuard */
public abstract class g {
    protected Context a = null;

    protected abstract void a(String str);

    protected abstract boolean a();

    protected abstract String b();

    public static String c() {
        return h.a("6X8Y4XdM2Vhvn0I=");
    }

    public static String d() {
        return h.a("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=");
    }

    protected g(Context context) {
        this.a = context;
    }

    private String g() {
        if (a()) {
            return c(b());
        }
        return null;
    }

    public d e() {
        String g = g();
        if (g != null) {
            return d.a(g);
        }
        return null;
    }

    private void d(String str) {
        if (a()) {
            a(b(str));
        }
    }

    public void a(d dVar) {
        if (dVar != null) {
            d(dVar.toString());
        }
    }

    protected String b(String str) {
        return h.b(str);
    }

    protected String c(String str) {
        return h.a(str);
    }

    protected String f() {
        return h.a("4kU71lN96TJUomD1vOU9lgj9Tw==");
    }
}
