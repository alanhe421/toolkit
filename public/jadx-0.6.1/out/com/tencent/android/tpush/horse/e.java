package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.service.a.a;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
public class e {
    public static boolean a(long j) {
        if (j != 0 && ((a() * 1000) * 60) + j > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public static long a() {
        return (long) a.a(m.e()).q;
    }

    public static int b() {
        return a.a(m.e()).o;
    }

    public static int c() {
        return a.a(m.e()).p;
    }
}
