package com.tencent.imsdk;

final class bj implements Runnable {
    private /* synthetic */ bi a;

    bj(bi biVar) {
        this.a = biVar;
    }

    public final void run() {
        this.a.b.onSuccess();
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|5-Callback|Succ|login succ");
        QLog.i("imsdk.IMMsfCoreProxy", 1, this.a.a + " login succ. tinyid: " + this.a.c.getTinyid() + " env: " + IMMsfCoreProxy.get().getEnv() + " mode: " + IMMsfCoreProxy.get().getMode());
    }
}
