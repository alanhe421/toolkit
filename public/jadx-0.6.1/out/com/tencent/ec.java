package com.tencent;

final class ec implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ad c;

    ec(ad adVar, int i, String str) {
        this.c = adVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
