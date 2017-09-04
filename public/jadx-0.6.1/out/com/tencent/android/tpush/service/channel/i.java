package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.service.channel.a.a;

/* compiled from: ProGuard */
class i implements Runnable {
    final /* synthetic */ b a;
    private a b = null;
    private com.tencent.android.tpush.service.channel.b.i c = null;

    public i(b bVar, a aVar, com.tencent.android.tpush.service.channel.b.i iVar) {
        this.a = bVar;
        this.b = aVar;
        this.c = iVar;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis() - this.a.p.b;
        a f = this.b.f();
        f.a(3, Long.valueOf(currentTimeMillis));
        p pVar = this.a.p.d;
        if (pVar == null) {
            com.tencent.android.tpush.a.a.h("TpnsChannel", ">> messageHandler is null");
            this.a.w.a(this.a.p.c, this.c.l(), null, f);
            return;
        }
        this.a.i.removeCallbacks((m) this.a.l.remove(this.a.p));
        pVar.a(this.a.p.c, this.c.l(), null, f);
    }
}
