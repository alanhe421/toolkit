package com.tencent.qalhttp;

import android.os.RemoteException;
import android.os.SystemClock;
import com.dynamicload.Lib.DLConstants;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseActionListener.Stub;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.sdk.e;
import com.tencent.qalsdk.util.QLog;

/* compiled from: QALHttpHelper */
class b extends Stub {
    final /* synthetic */ QALHttpValueCallBack a;
    final /* synthetic */ QALHttpHelper b;

    b(QALHttpHelper qALHttpHelper, QALHttpValueCallBack qALHttpValueCallBack) {
        this.b = qALHttpHelper;
        this.a = qALHttpValueCallBack;
    }

    public void onResponse(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        if (fromServiceMsg.isSuccess()) {
            this.b.onHttpResp(e.a(fromServiceMsg), toServiceMsg, this.a);
            return;
        }
        String str;
        this.b.removeSendMsg(toServiceMsg);
        if (fromServiceMsg.getResultCode() == 1002) {
            str = "resp timeout!";
        } else {
            str = "server failed!";
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.b.beginTime;
        QLog.e("QALHttpHelper", this.b.uri + "|http resp error:" + fromServiceMsg.getResultCode() + DLConstants.DEPENDENCY_PACKAGE_DIV + str);
        this.b.mainCallFail(this.a, fromServiceMsg.getResultCode(), str);
        this.b.reportHttp(QALHttpHelper.cacheHelper.d, elapsedRealtime, fromServiceMsg.getResultCode(), str, false);
    }

    public void onRecvFromMsg(FromServiceMsg fromServiceMsg) throws RemoteException {
    }

    public void onActionResult(FromServiceMsg fromServiceMsg) throws RemoteException {
    }
}
