package com.tencent.upload.network.b;

final class q implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ h b;

    q(h hVar, int i) {
        this.b = hVar;
        this.a = i;
    }

    public final void run() {
        h.b(this.b, this.a);
    }
}
