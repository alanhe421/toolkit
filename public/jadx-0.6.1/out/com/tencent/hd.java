package com.tencent;

final class hd implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ hb c;

    hd(hb hbVar, int i, String str) {
        this.c = hbVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        hb.a(this.c).onError(this.a, this.b);
    }
}
