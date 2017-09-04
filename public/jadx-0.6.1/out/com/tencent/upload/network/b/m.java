package com.tencent.upload.network.b;

import com.tencent.upload.Const.RetCode;
import com.tencent.upload.common.a.a;
import com.tencent.upload.network.base.d;

final class m implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ d b;
    private /* synthetic */ h c;

    m(h hVar, int i, d dVar) {
        this.c = hVar;
        this.a = i;
        this.b = dVar;
    }

    public final void run() {
        a.c(this.c.j(), "Session Error. sid=" + this.c.o + " socket_status=" + this.a);
        int i = this.a;
        if (this.a == 113 || this.a == 101 || this.a == 100) {
            RetCode.NETWORK_NOT_AVAILABLE.getCode();
        } else if (this.a == 526) {
            RetCode.SESSION_DIVIDE_PACKET_ERROR.getCode();
        }
        this.c.a(this.b, RetCode.NDK_NETWORK_ERROR.getCode(), "NDK Network Error. status=" + this.a);
    }
}
