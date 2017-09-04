package com.tencent;

final class gj implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ae c;

    gj(ae aeVar, int i, String str) {
        this.c = aeVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
