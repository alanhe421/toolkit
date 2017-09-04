package com.tencent.android.tpush.common;

import android.content.Context;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
final class q implements Runnable {
    final /* synthetic */ Context a;

    q(Context context) {
        this.a = context;
    }

    public void run() {
        if (p.d(this.a) != 1) {
            try {
                m.a(this.a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
