package com.tencent;

final class hc implements Runnable {
    private /* synthetic */ hb a;

    hc(hb hbVar) {
        this.a = hbVar;
    }

    public final void run() {
        hb.a(this.a).onSuccess();
    }
}
