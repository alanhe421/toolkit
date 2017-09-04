package com.tencent;

import java.util.ArrayList;

final class ej implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ ah b;

    ej(ah ahVar, ArrayList arrayList) {
        this.b = ahVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
