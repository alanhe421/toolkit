package com.tencent.qalsdk.service;

import android.os.Binder;
import android.os.RemoteException;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseService.Stub;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.util.QLog;

/* compiled from: QalService */
class i extends Stub {
    final /* synthetic */ QalService a;

    i(QalService qalService) {
        this.a = qalService;
    }

    public FromServiceMsg sendSyncToServiceMsg(ToServiceMsg toServiceMsg) throws RemoteException {
        return null;
    }

    public int sendToServiceMsg(ToServiceMsg toServiceMsg) throws RemoteException {
        if (toServiceMsg != null) {
            QLog.d(QalService.tag, "service rcvmsg. ssoCmd:" + toServiceMsg.getServiceCmd() + " msfCmd:" + toServiceMsg.getMsfCommand() + " appSeq:" + toServiceMsg.getAppSeq());
            int f = j.f();
            if (toServiceMsg.getRequestSsoSeq() == -1) {
                toServiceMsg.setRequestSsoSeq(f);
            }
            if (toServiceMsg.getTimeout() == -1) {
                toServiceMsg.setTimeout(a.ap);
            }
            try {
                int callingUid = Binder.getCallingUid();
                toServiceMsg.addAttribute(a.ao, Boolean.valueOf(true));
                if (!toServiceMsg.getServiceCmd().startsWith(a.I)) {
                    if (!QalService.isSamePackage(this.a, callingUid, toServiceMsg.getServiceCmd())) {
                        return -2;
                    }
                    QalService.msfServiceReqHandler.a(this.a, toServiceMsg, callingUid);
                }
            } catch (Throwable e) {
                QLog.w(QalService.tag, 1, "service handle msg error " + e, e);
            }
            return f;
        } else if (!QLog.isColorLevel()) {
            return -1;
        } else {
            QLog.w(QalService.tag, 2, "sendToServiceMsg toServiceMsg null!");
            return -1;
        }
    }
}
