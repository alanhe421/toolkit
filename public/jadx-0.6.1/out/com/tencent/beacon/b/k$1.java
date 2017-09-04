package com.tencent.beacon.b;

import com.tencent.beacon.e.a;
import com.tencent.beacon.event.o;

/* compiled from: ProGuard */
class k$1 implements Runnable {
    k$1() {
    }

    public final void run() {
        a.b(" db events to up on screen_on", new Object[0]);
        try {
            o.d(true);
        } catch (Throwable th) {
            a.a(th);
        }
    }
}
