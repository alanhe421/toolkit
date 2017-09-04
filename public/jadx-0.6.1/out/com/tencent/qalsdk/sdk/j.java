package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.base.remote.FromServiceMsg;

/* compiled from: CoreWrapper */
class j implements Runnable {
    final /* synthetic */ FromServiceMsg a;
    final /* synthetic */ i b;

    j(i iVar, FromServiceMsg fromServiceMsg) {
        this.b = iVar;
        this.a = fromServiceMsg;
    }

    public void run() {
        if (!this.b.b.a(this.a, this.b.a)) {
            this.b.a.onSuccess(e.a(this.a));
        }
    }
}
