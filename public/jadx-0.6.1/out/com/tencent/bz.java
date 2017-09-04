package com.tencent;

import com.tencent.TIMConversation.ab;

final class bz implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ab c;

    bz(ab abVar, int i, String str) {
        this.c = abVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a.onError(this.a, this.b);
    }
}
