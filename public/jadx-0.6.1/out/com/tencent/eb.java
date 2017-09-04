package com.tencent;

import java.util.List;

final class eb implements Runnable {
    private /* synthetic */ List a;
    private /* synthetic */ ad b;

    eb(ad adVar, List list) {
        this.b = adVar;
        this.a = list;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
