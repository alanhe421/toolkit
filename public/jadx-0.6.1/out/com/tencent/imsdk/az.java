package com.tencent.imsdk;

import com.tencent.TIMUserStatusListener;

final class az implements Runnable {
    private /* synthetic */ TIMUserStatusListener a;

    az(ay ayVar, TIMUserStatusListener tIMUserStatusListener) {
        this.a = tIMUserStatusListener;
    }

    public final void run() {
        this.a.onForceOffline();
        QLog.e("imsdk.IMMsfCoreProxy", 1, "ForceOffline|3-Callback|Succ");
    }
}
