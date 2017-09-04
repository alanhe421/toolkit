package com.tencent;

import com.tencent.TIMConversation.ad;

final class cc implements Runnable {
    private /* synthetic */ ad a;

    cc(ad adVar) {
        this.a = adVar;
    }

    public final void run() {
        this.a.a();
    }
}
