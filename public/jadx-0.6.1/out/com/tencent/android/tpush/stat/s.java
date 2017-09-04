package com.tencent.android.tpush.stat;

import com.tencent.android.tpush.stat.event.c;
import com.tencent.android.tpush.stat.event.d;
import java.util.Arrays;

/* compiled from: ProGuard */
class s implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ Throwable b;
    final /* synthetic */ Thread c;
    final /* synthetic */ r d;

    s(r rVar, long j, Throwable th, Thread thread) {
        this.d = rVar;
        this.a = j;
        this.b = th;
        this.c = thread;
    }

    public void run() {
        c cVar = new c(h.f, h.b(h.f, this.a), 2, this.b, this.c, this.a);
        h.b(Arrays.asList(new d[]{cVar}));
    }
}
