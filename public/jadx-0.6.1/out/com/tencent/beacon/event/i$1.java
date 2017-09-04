package com.tencent.beacon.event;

import com.tencent.beacon.e.a;

/* compiled from: ProGuard */
class i$1 implements Runnable {
    private /* synthetic */ i a;

    i$1(i iVar) {
        this.a = iVar;
    }

    public final void run() {
        try {
            this.a.a();
        } catch (Throwable th) {
            a.a(th);
        }
    }
}
