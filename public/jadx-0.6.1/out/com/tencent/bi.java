package com.tencent;

import java.util.List;

final class bi implements Runnable {
    private /* synthetic */ List a;
    private /* synthetic */ bh b;

    bi(bh bhVar, List list) {
        this.b = bhVar;
        this.a = list;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
