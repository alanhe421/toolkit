package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.service.a.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
class m implements Runnable {
    final /* synthetic */ b a;

    private m(b bVar) {
        this.a = bVar;
    }

    public void run() {
        try {
            Object obj;
            long j;
            Iterator it;
            o oVar;
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = Long.MAX_VALUE;
            long j3 = (long) a.a(com.tencent.android.tpush.service.m.e()).f;
            Object obj2 = null;
            long j4 = j3 < com.tencent.qalsdk.base.a.ap ? com.tencent.qalsdk.base.a.ap : j3;
            ChannelException channelException = new ChannelException(10107, "TpnsMessage wait for response timeout!");
            for (com.tencent.android.tpush.service.channel.a.a aVar : this.a.k.keySet()) {
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.a.k.get(aVar);
                if (concurrentHashMap == null || concurrentHashMap.size() == 0) {
                    obj = obj2;
                    j = j2;
                } else {
                    it = concurrentHashMap.entrySet().iterator();
                    a f = aVar.f();
                    obj = obj2;
                    j = j2;
                    while (it.hasNext()) {
                        Object obj3;
                        oVar = (o) ((Entry) it.next()).getValue();
                        if (oVar != null) {
                            j2 = currentTimeMillis - oVar.b;
                            f.a(3, Long.valueOf(j2));
                            if (j2 >= 0) {
                                if (j2 > j4) {
                                    p pVar = oVar.d;
                                    if (pVar != null) {
                                        pVar.a(oVar.c, channelException, f);
                                        oVar.d = null;
                                    }
                                    it.remove();
                                    obj3 = 1;
                                } else {
                                    if (j4 - j2 < j) {
                                        j = j4 - j2;
                                        obj3 = obj;
                                    }
                                    obj3 = obj;
                                }
                            }
                        } else {
                            it.remove();
                            obj3 = obj;
                        }
                        obj = obj3;
                    }
                }
                j2 = j;
                obj2 = obj;
            }
            channelException = new ChannelException(10106, "TpnsMessage wait for response timeout!");
            a aVar2 = null;
            synchronized (this.a) {
                it = this.a.j.iterator();
                while (it.hasNext()) {
                    a aVar3;
                    oVar = (o) it.next();
                    if (oVar != null) {
                        long j5 = currentTimeMillis - oVar.a;
                        if (j5 >= 0) {
                            if (j5 > j4) {
                                p pVar2 = oVar.d;
                                if (pVar2 != null) {
                                    if (aVar2 == null) {
                                        if (this.a.m != null) {
                                            aVar2 = this.a.m.f();
                                        } else {
                                            aVar2 = new a();
                                        }
                                        aVar2.a(3, Long.valueOf(j5));
                                    }
                                    pVar2.a(oVar.c, channelException, aVar2);
                                    oVar.d = null;
                                }
                                aVar3 = aVar2;
                                it.remove();
                                obj = 1;
                                j = j2;
                            } else {
                                if (j4 - j5 < j2) {
                                    aVar3 = aVar2;
                                    obj = obj2;
                                    j = j4 - j5;
                                }
                                aVar3 = aVar2;
                                obj = obj2;
                                j = j2;
                            }
                        }
                    } else {
                        it.remove();
                        aVar3 = aVar2;
                        obj = obj2;
                        j = j2;
                    }
                    j2 = j;
                    obj2 = obj;
                    aVar2 = aVar3;
                }
            }
            if (obj2 != null) {
                this.a.d();
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("TpnsChannel", "TimeoutRunnable.run", e);
        }
    }
}
