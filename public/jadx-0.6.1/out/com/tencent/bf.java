package com.tencent;

import com.tencent.imcore.IMCore;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;

final class bf implements Runnable {
    private /* synthetic */ TIMCallBack a;
    private /* synthetic */ IMMsfUserInfo b;
    private /* synthetic */ be c;

    bf(be beVar, TIMCallBack tIMCallBack, IMMsfUserInfo iMMsfUserInfo) {
        this.c = beVar;
        this.a = tIMCallBack;
        this.b = iMMsfUserInfo;
    }

    public final void run() {
        TIMManager.getInstanceById(this.c.b).getCoreUser().loginSyncMsg();
        this.a.onSuccess();
        QLog.i("imsdk.IMCoreWrapper", 1, "Login|5-Callback|Succ|Login succ");
        QLog.i("imsdk.IMCoreWrapper", 1, this.b.getUser() + " login succ. tinyid: " + this.b.getTinyid() + " env: " + IMMsfCoreProxy.get().getEnv() + " mode: " + IMMsfCoreProxy.get().getMode());
        IMCore.get().clearLog(IMCoreWrapper.get().getLogPath(), System.currentTimeMillis() / 1000);
    }
}
