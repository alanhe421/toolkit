package com.tencent;

import com.tencent.imsdk.QLog;

final class bd implements Runnable {
    private /* synthetic */ TIMUserDefinedStatusListener a;
    private /* synthetic */ TIMUserDefinedStatus b;

    bd(IMCoreNotify iMCoreNotify, TIMUserDefinedStatusListener tIMUserDefinedStatusListener, TIMUserDefinedStatus tIMUserDefinedStatus) {
        this.a = tIMUserDefinedStatusListener;
        this.b = tIMUserDefinedStatus;
    }

    public final void run() {
        this.a.onStatusChanged(this.b);
        QLog.d("IMCoreNotify", 1, "onUserStatusChanged, callback succ");
    }
}
