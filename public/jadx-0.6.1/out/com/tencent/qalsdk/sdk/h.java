package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.util.QLog;

/* compiled from: CoreWrapper */
class h implements Runnable {
    final /* synthetic */ w a;
    final /* synthetic */ g b;

    h(g gVar, w wVar) {
        this.b = gVar;
        this.a = wVar;
    }

    public void run() {
        if (!this.b.a.b(this.a.b)) {
            QLog.e("CoreWrapper", 1, "unknown push:" + this.a.b);
        }
    }
}
