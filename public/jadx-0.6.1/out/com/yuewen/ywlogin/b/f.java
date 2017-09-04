package com.yuewen.ywlogin.b;

final class f implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;

    f(b bVar, int i, String str) {
        this.a = bVar;
        this.b = i;
        this.c = str;
    }

    public void run() {
        if (this.a != null) {
            this.a.a(this.b, this.c);
        }
    }
}
