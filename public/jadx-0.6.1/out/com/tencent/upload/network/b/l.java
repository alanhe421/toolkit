package com.tencent.upload.network.b;

import com.tencent.upload.network.base.d;

final class l implements Runnable {
    private /* synthetic */ d a;
    private /* synthetic */ h b;

    l(h hVar, d dVar) {
        this.b = hVar;
        this.a = dVar;
    }

    public final void run() {
        h.a(this.b, this.a);
    }
}
