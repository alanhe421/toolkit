package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.horse.k;
import com.tencent.android.tpush.service.channel.a.d;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: ProGuard */
class c implements k {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void a(int i, String str) {
        a.h("TpnsChannel", "ICreateSocketChannelCallback onFailure(" + i + "," + str + ")");
        synchronized (this.a) {
            this.a.n = false;
            if (!this.a.f()) {
                a.e("TpnsChannel", "Connect to Xinge Server failed!");
                ChannelException channelException = new ChannelException(i, str);
                Iterator it = this.a.j.iterator();
                while (it.hasNext()) {
                    o oVar = (o) it.next();
                    if (oVar.d != null) {
                        oVar.d.a(oVar.c, channelException, a.a());
                    } else {
                        a.h("TpnsChannel", oVar.toString());
                    }
                }
                this.a.j.clear();
            }
            b.a = 0;
        }
    }

    public void a(SocketChannel socketChannel, StrategyItem strategyItem) {
        a.a("TpnsChannel", "ICreateSocketChannelCallback onSuccess(" + socketChannel + "," + socketChannel + ")");
        synchronized (this.a) {
            this.a.n = false;
            b.g = 0;
            try {
                if (!b.t.equals(strategyItem.a())) {
                    switch (f.f(m.e())) {
                        case (byte) 1:
                            b.f = b.d;
                            break;
                        case (byte) 2:
                            b.f = b.c;
                            break;
                        case (byte) 3:
                            b.f = b.c;
                            break;
                        case (byte) 4:
                            b.f = b.c;
                            break;
                    }
                    b.t = strategyItem.a();
                }
                b.a = 0;
                b bVar = this.a;
                com.tencent.android.tpush.service.channel.a.a dVar = strategyItem.i() ? strategyItem.h() ? new d(socketChannel, b.a(), strategyItem.a(), strategyItem.b()) : new com.tencent.android.tpush.service.channel.a.c(socketChannel, b.a()) : new com.tencent.android.tpush.service.channel.a.a(socketChannel, b.a());
                bVar.m = dVar;
                this.a.a(true);
                this.a.m.start();
                this.a.k.clear();
                this.a.k.put(this.a.m, new ConcurrentHashMap());
                this.a.q = true;
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "", e);
            }
        }
    }
}
