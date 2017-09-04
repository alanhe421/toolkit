package com.tencent.imsdk;

final class ar implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ an b;

    ar(an anVar, String str) {
        this.b = anVar;
        this.a = str;
    }

    public final void run() {
        this.b.a.onError(BaseConstants.ERR_IO_OPERATION_FAILED, this.a);
        this.b.b.init(BaseConstants.ERR_IO_OPERATION_FAILED, this.a);
        this.b.b.report();
    }
}
