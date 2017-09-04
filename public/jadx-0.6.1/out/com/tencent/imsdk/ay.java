package com.tencent.imsdk;

import com.dynamicload.Lib.DLConstants;
import com.tencent.TIMManager;
import com.tencent.TIMUserStatusListener;
import com.tencent.imcore.IMCoreUser;
import com.tencent.qalsdk.QALUserStatusListener;

final class ay implements QALUserStatusListener {
    private /* synthetic */ IMMsfCoreProxy a;

    ay(IMMsfCoreProxy iMMsfCoreProxy) {
        this.a = iMMsfCoreProxy;
    }

    public final void onForceOffline(String str) {
        QLog.i("imsdk.IMMsfCoreProxy", 1, "ForceOffline|1-Recv|Succ|recv forceoffline id: " + str);
        this.a.logout(str);
        QLog.i("imsdk.IMMsfCoreProxy", 1, "ForceOffline|2-Logout|Succ");
        TIMUserStatusListener userStatusListener = TIMManager.getInstanceById(str).getUserStatusListener();
        if (userStatusListener != null) {
            IMMsfCoreProxy.mainHandler.post(new az(this, userStatusListener));
        } else {
            QLog.e("imsdk.IMMsfCoreProxy", 1, "ForceOffline|3-Callback|Fail|no listener found");
        }
    }

    public final void onRegisterFail(String str, int i, String str2) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "reconnected...onRegisterFail error" + i + DLConstants.DEPENDENCY_PACKAGE_DIV + str2 + "/" + str);
    }

    public final void onRegisterSucc(String str) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "reconnected...register success id:" + str);
        if (this.a.mode == 1) {
            IMCoreUser coreUser = TIMManager.getInstanceById(str).getCoreUser();
            coreUser.loginSyncMsg();
            coreUser.loginSyncCache();
        }
    }
}
