package com.tencent.android.tpush.horse;

/* compiled from: ProGuard */
public class q extends a {
    private static q a;

    private q() {
    }

    public static synchronized q i() {
        q qVar;
        synchronized (q.class) {
            if (a == null) {
                a = new q();
            }
            qVar = a;
        }
        return qVar;
    }

    public void e() {
        f.i().d().clear();
    }

    public void f() {
        f.i().a(-1);
        f.i().a();
    }
}
