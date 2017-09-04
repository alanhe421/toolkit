package com.tencent.qalsdk.sdk;

import android.os.RemoteException;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IMsfServiceCallbacker.Stub;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: MsfServiceProxy */
class aa extends Stub {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) throws RemoteException {
        this.a.d(toServiceMsg, fromServiceMsg);
    }

    public void onRecvPushResp(FromServiceMsg fromServiceMsg) throws RemoteException {
        this.a.a(fromServiceMsg);
    }
}
