package com.tencent;

final class bj implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ bh c;

    bj(bh bhVar, int i, String str) {
        this.c = bhVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
