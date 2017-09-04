package com.tencent;

import com.tencent.TIMConversation.ac;

final class cb implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ac c;

    cb(ac acVar, int i, String str) {
        this.c = acVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a.onError(this.a, this.b);
    }
}
