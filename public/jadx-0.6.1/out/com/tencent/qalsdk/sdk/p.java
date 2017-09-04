package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.im_open.logParams.LogParams;

/* compiled from: LogReport */
class p extends Thread {
    final /* synthetic */ LogParams a;
    final /* synthetic */ o b;

    p(o oVar, LogParams logParams) {
        this.b = oVar;
        this.a = logParams;
    }

    public void run() {
        o.a(this.b, this.a);
    }
}
