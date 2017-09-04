package com.tencent.android.tpush.horse;

/* compiled from: ProGuard */
public class f extends a {
    private static f a;

    private f() {
    }

    public static synchronized f i() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    public void e() {
        i().d().clear();
    }

    public void f() {
        i().a(-1);
    }
}
