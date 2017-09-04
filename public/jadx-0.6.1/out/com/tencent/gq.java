package com.tencent;

import java.util.ArrayList;

final class gq implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ aj b;

    gq(aj ajVar, ArrayList arrayList) {
        this.b = ajVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
