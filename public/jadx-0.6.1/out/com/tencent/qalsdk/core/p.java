package com.tencent.qalsdk.core;

import com.tencent.qalsdk.sdk.q;

/* compiled from: Sender */
class p extends Thread {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public void run() {
        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l.a().removeConfig(q.e);
        l.a().removeConfig(q.f);
    }
}
