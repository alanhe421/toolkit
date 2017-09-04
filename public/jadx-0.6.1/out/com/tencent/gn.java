package com.tencent;

final class gn implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ah c;

    gn(ah ahVar, int i, String str) {
        this.c = ahVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
