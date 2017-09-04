package com.tencent.imsdk;

final class bg implements Runnable {
    private /* synthetic */ byte[] a;
    private /* synthetic */ bf b;

    bg(bf bfVar, byte[] bArr) {
        this.b = bfVar;
        this.a = bArr;
    }

    public final void run() {
        this.b.a.onSuccess(this.a);
    }
}
