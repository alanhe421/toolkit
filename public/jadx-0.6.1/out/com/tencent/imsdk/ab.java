package com.tencent.imsdk;

import com.tencent.TIMCallBack;

final class ab implements Runnable {
    private /* synthetic */ TIMCallBack a;
    private /* synthetic */ int b;
    private /* synthetic */ String c;

    ab(TIMCallBack tIMCallBack, int i, String str) {
        this.a = tIMCallBack;
        this.b = i;
        this.c = str;
    }

    public final void run() {
        this.a.onError(this.b, this.c);
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|5-Callback|Succ|Login failed! code:" + this.b + ", desc:" + this.c);
    }
}
