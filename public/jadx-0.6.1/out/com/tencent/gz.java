package com.tencent;

import java.util.List;

final class gz implements Runnable {
    private /* synthetic */ List a;
    private /* synthetic */ gy b;

    gz(gy gyVar, List list) {
        this.b = gyVar;
        this.a = list;
    }

    public final void run() {
        this.b.a.onSuccess(this.a);
    }
}
