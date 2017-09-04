package com.tencent.imsdk;

final class ak implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ ai b;

    ak(ai aiVar, String str) {
        this.b = aiVar;
        this.a = str;
    }

    public final void run() {
        this.b.a.onError(6010, this.a);
    }
}
