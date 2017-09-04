package com.tencent.qalsdk.sdk;

import android.os.RemoteException;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseActionListener.Stub;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.util.QLog;

/* compiled from: CoreWrapper */
class k extends Stub {
    final /* synthetic */ a a;
    final /* synthetic */ QALCallBack b;
    final /* synthetic */ e c;

    k(e eVar, a aVar, QALCallBack qALCallBack) {
        this.c = eVar;
        this.a = aVar;
        this.b = qALCallBack;
    }

    public void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        QLog.i("CoreWrapper", this.a.a + " register resp." + toServiceMsg.getServiceCmd() + ":" + fromServiceMsg.getResultCode() + ":" + toServiceMsg.getRequestSsoSeq());
        this.c.e(this.a.b);
        this.c.a(toServiceMsg, fromServiceMsg, this.b);
    }

    public void onRecvFromMsg(FromServiceMsg fromServiceMsg) throws RemoteException {
    }

    public void onActionResult(FromServiceMsg fromServiceMsg) throws RemoteException {
    }
}
