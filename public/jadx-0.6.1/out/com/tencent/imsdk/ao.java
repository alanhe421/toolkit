package com.tencent.imsdk;

final class ao implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ an b;

    ao(an anVar, String str) {
        this.b = anVar;
        this.a = str;
    }

    public final void run() {
        this.b.a.onError(6010, this.a);
    }
}
