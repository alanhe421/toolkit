package com.tencent.android.tpush.service;

import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.service.a.a;

/* compiled from: ProGuard */
class y implements Runnable {
    final /* synthetic */ w a;

    y(w wVar) {
        this.a = wVar;
    }

    public void run() {
        try {
            if (a.a(w.a(this.a)).z != 0) {
                long currentTimeMillis = System.currentTimeMillis();
                if (w.b(this.a) == 0) {
                    w.a(this.a, m.a(w.a(this.a), "last.monitor.ts", 0));
                }
                if (currentTimeMillis - w.b(this.a) >= ((long) a.a(w.a(this.a)).A)) {
                    if (w.c(this.a)) {
                        w.e(this.a);
                    } else {
                        w.d(this.a);
                    }
                    w.a(this.a, currentTimeMillis);
                    m.b(w.a(this.a), "last.monitor.ts", w.b(this.a));
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c(w.b(), "monotor error.", th);
        }
    }
}
