package com.tencent.qalsdk.sdk;

import android.os.RemoteException;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: CoreWrapper */
class f implements Runnable {
    final /* synthetic */ ToServiceMsg a;
    final /* synthetic */ FromServiceMsg b;
    final /* synthetic */ e c;

    f(e eVar, ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        this.c = eVar;
        this.a = toServiceMsg;
        this.b = fromServiceMsg;
    }

    public void run() {
        try {
            this.a.getActionListener().onResponse(this.a, this.b);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
