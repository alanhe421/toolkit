package com.tencent;

import java.util.ArrayList;

final class ef implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ af b;

    ef(af afVar, ArrayList arrayList) {
        this.b = afVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
