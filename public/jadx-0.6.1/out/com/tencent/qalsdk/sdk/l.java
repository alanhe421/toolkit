package com.tencent.qalsdk.sdk;

import android.os.RemoteException;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseActionListener.Stub;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.util.QLog;

/* compiled from: CoreWrapper */
class l extends Stub {
    final /* synthetic */ String a;
    final /* synthetic */ QALCallBack b;
    final /* synthetic */ e c;

    l(e eVar, String str, QALCallBack qALCallBack) {
        this.c = eVar;
        this.a = str;
        this.b = qALCallBack;
    }

    public void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        QLog.i("CoreWrapper", this.a + " unregister resp." + toServiceMsg.getServiceCmd() + ":" + fromServiceMsg.getResultCode() + ":" + toServiceMsg.getRequestSsoSeq());
        this.c.a(toServiceMsg, fromServiceMsg, this.b);
    }

    public void onRecvFromMsg(FromServiceMsg fromServiceMsg) throws RemoteException {
    }

    public void onActionResult(FromServiceMsg fromServiceMsg) throws RemoteException {
    }
}
