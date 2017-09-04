package com.tencent.imsdk;

final class am implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ ai b;

    am(ai aiVar, String str) {
        this.b = aiVar;
        this.a = str;
    }

    public final void run() {
        this.b.a.onError(BaseConstants.ERR_IO_OPERATION_FAILED, this.a);
    }
}
