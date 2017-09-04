package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.service.channel.a.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
class k implements Runnable {
    final /* synthetic */ b a;
    private a b = null;
    private ChannelException c = null;
    private boolean d = false;

    public k(b bVar, a aVar, ChannelException channelException, boolean z) {
        this.a = bVar;
        this.b = aVar;
        this.c = channelException;
        this.d = z;
    }

    public void run() {
        if (this.b == null) {
            com.tencent.android.tpush.a.a.h("TpnsChannel", "@@RequestFailRunnable currentClient == null");
            return;
        }
        Iterator it;
        p pVar;
        long currentTimeMillis = System.currentTimeMillis();
        a f = this.b.f();
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.a.k.get(this.b);
        if (concurrentHashMap != null) {
            for (o oVar : concurrentHashMap.values()) {
                pVar = oVar.d;
                if (pVar != null) {
                    f.a(3, Long.valueOf(currentTimeMillis - oVar.b));
                    this.a.i.removeCallbacks((m) this.a.l.remove(oVar));
                    pVar.a(oVar.c, this.c, f);
                }
            }
            concurrentHashMap.clear();
        }
        if (!this.b.e()) {
            synchronized (this.a) {
                it = this.a.j.iterator();
                while (it.hasNext()) {
                    o oVar2 = (o) it.next();
                    pVar = oVar2.d;
                    if (pVar != null) {
                        f.a(3, Long.valueOf(currentTimeMillis - oVar2.b));
                        this.a.i.removeCallbacks((m) this.a.l.get(oVar2));
                        pVar.a(oVar2.c, this.c, f);
                    }
                }
                this.a.j.clear();
            }
        }
        b.a = 0;
        if (b.f > b.d) {
            b.f = (b.f / 10) * 9;
        } else {
            b.f = b.d;
        }
        this.a.c();
        if (!this.a.j.isEmpty()) {
            this.a.e();
        }
        if (this.d) {
            this.a.f();
        }
    }
}
