package com.tencent.upload.network.b;

import com.tencent.upload.network.base.d;

final class k implements Runnable {
    private /* synthetic */ d a;
    private /* synthetic */ boolean b;
    private /* synthetic */ int c;
    private /* synthetic */ String d;
    private /* synthetic */ h e;

    k(h hVar, d dVar, boolean z, int i, String str) {
        this.e = hVar;
        this.a = dVar;
        this.b = z;
        this.c = i;
        this.d = str;
    }

    public final void run() {
        h.a(this.e, this.a, this.b, this.c, this.d);
    }
}
