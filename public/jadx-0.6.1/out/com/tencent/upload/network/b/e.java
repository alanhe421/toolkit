package com.tencent.upload.network.b;

import com.tencent.upload.Const.RetCode;
import com.tencent.upload.common.Global;
import com.tencent.upload.network.b.c.a;

final class e implements Runnable {
    private /* synthetic */ a a;
    private /* synthetic */ int b;
    private /* synthetic */ String c;
    private /* synthetic */ boolean d;
    private /* synthetic */ c e;

    e(c cVar, a aVar, int i, String str, boolean z) {
        this.e = cVar;
        this.a = aVar;
        this.b = i;
        this.c = str;
        this.d = z;
    }

    public final void run() {
        com.tencent.upload.common.e.b(Global.context);
        boolean remove = this.e.i.remove(this.a);
        this.a.b();
        if (remove && this.e.h == a.DETECTING) {
            this.e.a = this.b;
            this.e.b = this.c;
            if (!this.d) {
                this.e.a = RetCode.NETWORK_NOT_AVAILABLE.getCode();
                this.e.b = RetCode.NETWORK_NOT_AVAILABLE.getDesc();
            }
            if (this.e.i.size() == 0) {
                this.e.a(false);
            }
        }
    }
}
