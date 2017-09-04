package com.tencent.android.tpush.stat;

import android.content.Context;
import com.tencent.android.tpush.stat.event.a;
import com.tencent.android.tpush.stat.event.b;
import com.tencent.android.tpush.stat.event.d;

/* compiled from: ProGuard */
final class l implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ long b;
    final /* synthetic */ b c;
    final /* synthetic */ long d;

    l(Context context, long j, b bVar, long j2) {
        this.a = context;
        this.b = j;
        this.c = bVar;
        this.d = j2;
    }

    public void run() {
        try {
            d aVar = new a(this.a, h.b(this.a, this.b), this.c.a, this.b, this.d);
            aVar.a().c = this.c.c;
            h.a(aVar);
        } catch (Throwable th) {
            h.d.b(th);
        }
    }
}
