package com.tencent;

import com.tencent.TIMConversation.ad;

final class cd implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ad c;

    cd(ad adVar, int i, String str) {
        this.c = adVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
