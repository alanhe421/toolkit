package com.tencent;

final class ha implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ gy c;

    ha(gy gyVar, int i, String str) {
        this.c = gyVar;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.a.onError(this.a, this.b);
    }
}
