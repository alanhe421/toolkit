package com.tencent.upload.network.b;

import com.tencent.upload.network.base.d;

final class n implements Runnable {
    private /* synthetic */ d a;
    private /* synthetic */ int b;
    private /* synthetic */ int c;
    private /* synthetic */ h d;

    n(h hVar, d dVar, int i, int i2) {
        this.d = hVar;
        this.a = dVar;
        this.b = i;
        this.c = i2;
    }

    public final void run() {
        h.a(this.d, this.a, this.b, this.c);
    }
}
