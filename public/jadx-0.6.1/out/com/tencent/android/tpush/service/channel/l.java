package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.channel.a.a;
import com.tencent.android.tpush.service.channel.b.i;
import com.tencent.android.tpush.service.channel.c.d;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
class l implements Runnable {
    final /* synthetic */ b a;
    private a b = null;
    private i c = null;

    public l(b bVar, a aVar, i iVar) {
        this.a = bVar;
        this.b = aVar;
        this.c = iVar;
    }

    public void run() {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.a.k.get(this.b);
        if (concurrentHashMap != null) {
            o oVar = (o) concurrentHashMap.get(Integer.valueOf(this.c.i()));
            if (oVar == null) {
                com.tencent.android.tpush.a.a.h("TpnsChannel", ">> NetCallBackRunnable >>> 请求已被回调过，响应对应的request不存在。" + this.c);
                try {
                    com.tencent.android.tpush.a.a.h("TpnsChannel", "onRequestSuccRunnable unhandle message type" + d.a(this.c.h(), this.c.k()).getClass().getName());
                    return;
                } catch (Throwable e) {
                    com.tencent.android.tpush.a.a.c("TpnsChannel", "", e);
                    return;
                }
            }
            this.a.i.removeCallbacks((m) this.a.l.remove(oVar));
            concurrentHashMap.remove(Integer.valueOf(this.c.i()));
            p pVar = oVar.d;
            if (pVar == null) {
                com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, ">> messageHandler is null");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - oVar.b;
            a f = this.b.f();
            f.a(3, Long.valueOf(currentTimeMillis));
            try {
                pVar.a(oVar.c, this.c.l(), d.a(this.c.h(), this.c.k()), f);
            } catch (Throwable e2) {
                com.tencent.android.tpush.a.a.c("TpnsChannel", "", e2);
            }
        }
    }
}
