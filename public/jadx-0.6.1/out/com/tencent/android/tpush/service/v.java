package com.tencent.android.tpush.service;

import android.os.PowerManager.WakeLock;

/* compiled from: ProGuard */
public class v {
    private static v a = null;
    private WakeLock b = null;

    private v() {
    }

    public static v a() {
        if (a == null) {
            a = new v();
        }
        return a;
    }

    public WakeLock b() {
        return this.b;
    }

    public void a(WakeLock wakeLock) {
        this.b = wakeLock;
    }
}
