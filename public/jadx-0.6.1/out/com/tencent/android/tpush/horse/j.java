package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.horse.data.StrategyItem;
import java.nio.channels.SocketChannel;

/* compiled from: ProGuard */
class j implements b {
    final /* synthetic */ g a;

    j(g gVar) {
        this.a = gVar;
    }

    public void a(SocketChannel socketChannel, StrategyItem strategyItem) {
        g.a(0);
        if (socketChannel == null || strategyItem == null) {
            g.a(this.a, 10101, "create channel fail!");
            return;
        }
        if (!socketChannel.isConnected()) {
            g.a(this.a, 10101, "create channel fail!");
        } else if (g.b(this.a) != null) {
            if (!strategyItem.j() || g.e(this.a)) {
                g.b(this.a).a(socketChannel, strategyItem);
            } else {
                try {
                    socketChannel.close();
                } catch (Throwable e) {
                    a.c(Constants.HorseLogTag, "socketChannel.close()", e);
                }
            }
        }
        if (g.e(this.a)) {
            g.a(this.a, false);
        }
        synchronized (g.c(this.a)) {
            g.a(this.a, 2);
            g.c(this.a).notify();
        }
    }

    public void a(StrategyItem strategyItem) {
        if (g.e(this.a)) {
            g.a(this.a, false);
            this.a.b();
        } else if (q.i().b()) {
            a.d(Constants.HorseLogTag, ">> tcp has remain");
        } else {
            if (g.d(this.a) == 0 && !f.i().b()) {
                g.a(this.a, 2);
                if (g.b(this.a) != null) {
                    g.a(this.a, 10101, "create channel fail!");
                }
            }
            if (g.d(this.a) == 1) {
                synchronized (g.c(this.a)) {
                    g.a(this.a, 2);
                    g.c(this.a).notify();
                }
            }
        }
    }
}
