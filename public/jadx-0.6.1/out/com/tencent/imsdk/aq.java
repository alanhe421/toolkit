package com.tencent.imsdk;

final class aq implements Runnable {
    private /* synthetic */ byte[] a;
    private /* synthetic */ an b;

    aq(an anVar, byte[] bArr) {
        this.b = anVar;
        this.a = bArr;
    }

    public final void run() {
        this.b.a.onSuccess(this.a);
    }
}
