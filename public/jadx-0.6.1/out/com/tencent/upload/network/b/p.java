package com.tencent.upload.network.b;

final class p implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ h b;

    p(h hVar, int i) {
        this.b = hVar;
        this.a = i;
    }

    public final void run() {
        h.a(this.b, this.a);
    }
}
