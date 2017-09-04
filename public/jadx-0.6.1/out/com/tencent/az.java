package com.tencent;

final class az implements Runnable {
    private /* synthetic */ TIMUploadProgressListener a;
    private /* synthetic */ TIMMessage b;
    private /* synthetic */ int c;
    private /* synthetic */ int d;
    private /* synthetic */ int e;

    az(IMCoreNotify iMCoreNotify, TIMUploadProgressListener tIMUploadProgressListener, TIMMessage tIMMessage, int i, int i2, int i3) {
        this.a = tIMUploadProgressListener;
        this.b = tIMMessage;
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public final void run() {
        this.a.onMessagesUpdate(this.b, this.c, this.d, this.e);
    }
}
