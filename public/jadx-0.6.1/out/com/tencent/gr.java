package com.tencent;

final class gr implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ aj c;

    gr(aj ajVar, int i, String str) {
        this.c = ajVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
