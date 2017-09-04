package com.tencent;

final class ee implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ae c;

    ee(ae aeVar, int i, String str) {
        this.c = aeVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
