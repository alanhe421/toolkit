package com.tencent.android.tpush.service;

import android.app.AlarmManager;
import android.app.PendingIntent;

/* compiled from: ProGuard */
public class u {
    private static u a = new u();
    private static AlarmManager b = null;

    private u() {
    }

    public static u a() {
        if (b == null) {
            b();
        }
        return a;
    }

    public void a(int i, long j, PendingIntent pendingIntent) {
        if (b != null) {
            b.set(i, j, pendingIntent);
        }
    }

    private static synchronized void b() {
        synchronized (u.class) {
            if (b == null && m.e() != null) {
                b = (AlarmManager) m.e().getSystemService("alarm");
            }
        }
    }
}
