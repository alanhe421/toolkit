package com.tencent;

import java.util.ArrayList;

final class ga implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ aa b;

    ga(aa aaVar, ArrayList arrayList) {
        this.b = aaVar;
        this.a = arrayList;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
