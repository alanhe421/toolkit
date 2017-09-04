package com.qq.reader.module.rookie.presenter;

/* compiled from: RookieGiftHelper */
class a$3 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ a b;

    a$3(a aVar, boolean z) {
        this.b = aVar;
        this.a = z;
    }

    public void run() {
        for (a$a a : a.e(this.b)) {
            a.a(this.a);
        }
    }
}
