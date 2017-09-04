package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.util.QLog;

/* compiled from: RemoteServiceProxy */
class al extends Thread {
    final /* synthetic */ aj a;

    al(aj ajVar) {
        this.a = ajVar;
    }

    public void run() {
        while (!aj.f.isEmpty()) {
            ToServiceMsg toServiceMsg = (ToServiceMsg) aj.f.poll();
            if (toServiceMsg != null) {
                try {
                    this.a.a(toServiceMsg);
                } catch (Exception e) {
                    FromServiceMsg a = this.a.a(toServiceMsg, toServiceMsg.getServiceName() + "sendMsgToServiceFailed，" + e.toString());
                    QLog.i("MSF.D.RemoteServiceProxy", "sendMsgToServiceFailed " + toServiceMsg.getServiceCmd());
                    this.a.c(toServiceMsg, a);
                }
            }
        }
    }
}
