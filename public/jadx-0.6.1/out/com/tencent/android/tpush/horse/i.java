package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import java.nio.channels.SocketChannel;

/* compiled from: ProGuard */
class i implements b {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    public void a(SocketChannel socketChannel, StrategyItem strategyItem) {
        g.a(0);
        if (q.i().b()) {
            g.a(this.a, 1);
        }
        synchronized (g.c(this.a)) {
            if (g.d(this.a) == 1) {
                try {
                    g.c(this.a).wait();
                } catch (Throwable e) {
                    a.c(Constants.HorseLogTag, "lock.wait", e);
                }
            }
        }
        if (!socketChannel.isConnected() || q.i().c()) {
            if (!socketChannel.isConnected() && !q.i().c()) {
                g.a(this.a, 10101, "create channel fail httpChannelCallback !");
            }
        } else if (g.b(this.a) == null) {
            a.h(Constants.HorseLogTag, ">> mcreateSocket channelCallback is null ");
        } else if (strategyItem.j()) {
            try {
                socketChannel.close();
            } catch (Throwable e2) {
                a.c(Constants.HorseLogTag, "socketChannel.close()", e2);
            }
        } else {
            g.b(this.a).a(socketChannel, strategyItem);
        }
    }

    public void a(StrategyItem strategyItem) {
        if (!q.i().b() && !f.i().b() && g.d(this.a) == 0) {
            g.a(this.a, 2);
            if (g.b(this.a) != null) {
                g.b(this.a).a(10101, "create http channel fail!");
            }
        }
    }
}
