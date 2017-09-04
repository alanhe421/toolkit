package com.tencent;

final class gh implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ ad c;

    gh(ad adVar, int i, String str) {
        this.c = adVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
