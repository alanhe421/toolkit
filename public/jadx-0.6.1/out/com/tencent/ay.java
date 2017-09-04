package com.tencent;

final class ay implements Runnable {
    private /* synthetic */ TIMUploadProgressListener a;
    private /* synthetic */ int b;
    private /* synthetic */ int c;

    ay(IMCoreNotify iMCoreNotify, TIMUploadProgressListener tIMUploadProgressListener, int i, int i2) {
        this.a = tIMUploadProgressListener;
        this.b = i;
        this.c = i2;
    }

    public final void run() {
        this.a.onMessagesUpdate(null, 0, this.b, this.c);
    }
}
