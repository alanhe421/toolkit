package com.tencent;

import com.tencent.imcore.EnvRequestClosure;
import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;

final class ac implements TIMValueCallBack<byte[]> {
    private /* synthetic */ EnvRequestClosure a;
    private /* synthetic */ String b;

    ac(aa aaVar, EnvRequestClosure envRequestClosure, String str) {
        this.a = envRequestClosure;
        this.b = str;
    }

    public final void onError(int i, String str) {
        if (BaseConstants.ERR_USER_SIG_EXPIRED == i) {
            IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(this.b);
            if (msfUserInfo == null) {
                QLog.e(aa.a, 1, "sSORequest no user found: " + this.b);
                msfUserInfo = new IMMsfUserInfo();
            }
            QLog.i(aa.a, 1, "usersig expired");
            if (!msfUserInfo.isSigExpire()) {
                TIMUserStatusListener userStatusListener = TIMManager.getInstanceById(this.b).getUserStatusListener();
                if (userStatusListener != null) {
                    userStatusListener.onUserSigExpired();
                }
                msfUserInfo.setIsSigExpire(true);
            }
        }
        if (str == null) {
            try {
                str = "";
            } catch (Throwable th) {
                r0 = IMFunc.getExceptionInfo(th);
                QLog.e(aa.a, 1, r0);
                r1 = TIMManager.getInstance().getExceptionListener();
                TIMExceptionListener exceptionListener;
                if (exceptionListener != null) {
                    String exceptionInfo;
                    exceptionListener.onException(exceptionInfo);
                    return;
                }
                return;
            }
        }
        this.a.fail(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        try {
            this.a.done((byte[]) obj);
        } catch (Throwable th) {
            String exceptionInfo = IMFunc.getExceptionInfo(th);
            QLog.e(aa.a, 1, exceptionInfo);
            TIMExceptionListener exceptionListener = TIMManager.getInstance().getExceptionListener();
            if (exceptionListener != null) {
                exceptionListener.onException(exceptionInfo);
            }
        }
    }
}
