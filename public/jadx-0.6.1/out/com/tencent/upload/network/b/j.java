package com.tencent.upload.network.b;

import android.util.SparseArray;
import com.tencent.upload.Const.RetCode;
import com.tencent.upload.c.a;
import com.tencent.upload.network.b.a.b;

final class j implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ a b;
    private /* synthetic */ h c;

    j(h hVar, int i, a aVar) {
        this.c = hVar;
        this.a = i;
        this.b = aVar;
    }

    public final void run() {
        SparseArray e = this.c.n;
        a aVar = (a) e.get(this.a);
        if (aVar == null || aVar.b != this) {
            com.tencent.upload.common.a.a.c(this.c.j(), "execute timeout runnable has been removed. reqId=" + this.a + " sid:" + this.c.o);
            return;
        }
        com.tencent.upload.common.a.a.c(this.c.j(), "Request Timeout! actionId=" + aVar.a.b() + " reqId=" + this.a + " cmd=" + aVar.a.d() + " sid=" + this.c.o + " currState=" + this.c.h.toString());
        e.remove(this.a);
        this.c.k.removeCallbacks(aVar.b);
        aVar.b = null;
        if (this.c.h == b.ESTABLISHED) {
            if (this.c.c == null || this.c.c.f() != 1) {
                if (!(this.b == null || this.b.g() == null)) {
                    this.b.g().onRequestTimeout(this.b);
                }
            } else if (!(this.b == null || this.b.g() == null)) {
                this.b.g().onRequestTimeout(this.b);
            }
        } else if (this.c.h == b.HANDSHAKE) {
            this.c.a = null;
            b bVar = (b) this.c.e.get();
            if (bVar != null) {
                bVar.a(this.c, RetCode.HANDSHAKE_TIMEOUT.getCode(), RetCode.HANDSHAKE_TIMEOUT.getDesc());
            }
        }
        this.c.a(false);
    }
}
