package com.tencent.upload.network.b;

import com.tencent.upload.network.base.d;

final class o implements Runnable {
    private /* synthetic */ d a;
    private /* synthetic */ byte[] b;
    private /* synthetic */ h c;

    o(h hVar, d dVar, byte[] bArr) {
        this.c = hVar;
        this.a = dVar;
        this.b = bArr;
    }

    public final void run() {
        h.a(this.c, this.a, this.b);
    }
}
