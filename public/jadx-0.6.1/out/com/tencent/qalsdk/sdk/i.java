package com.tencent.qalsdk.sdk;

import android.os.RemoteException;
import com.tencent.qalsdk.QALValueCallBack;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseActionListener.Stub;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: CoreWrapper */
class i extends Stub {
    final /* synthetic */ QALValueCallBack a;
    final /* synthetic */ e b;

    i(e eVar, QALValueCallBack qALValueCallBack) {
        this.b = eVar;
        this.a = qALValueCallBack;
    }

    public void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        e.K.post(new j(this, fromServiceMsg));
    }

    public void onRecvFromMsg(FromServiceMsg fromServiceMsg) throws RemoteException {
    }

    public void onActionResult(FromServiceMsg fromServiceMsg) throws RemoteException {
    }
}
