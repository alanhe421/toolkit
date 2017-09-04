package com.tencent;

final class gp implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ai c;

    gp(ai aiVar, int i, String str) {
        this.c = aiVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
