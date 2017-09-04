package com.tencent;

import java.util.ArrayList;

final class gg implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ ad b;

    gg(ad adVar, ArrayList arrayList) {
        this.b = adVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
