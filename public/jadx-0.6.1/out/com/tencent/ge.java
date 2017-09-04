package com.tencent;

import java.util.ArrayList;

final class ge implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ ac b;

    ge(ac acVar, ArrayList arrayList) {
        this.b = acVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
