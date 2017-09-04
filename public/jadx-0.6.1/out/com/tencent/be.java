package com.tencent;

import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;

final class be extends au {
    final /* synthetic */ String b;

    be(IMCoreWrapper iMCoreWrapper, TIMCallBack tIMCallBack, String str) {
        this.b = str;
        super(tIMCallBack);
    }

    public final void a() {
        QLog.e("imsdk.IMCoreWrapper", 1, "Login|4-InitIMCore|Succ|imcore init succ");
        IMCoreWrapper.get().setReady(true);
        if (this.a != null) {
            TIMCallBack tIMCallBack = this.a;
            IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(this.b);
            if (msfUserInfo == null || msfUserInfo.getUser() == null || msfUserInfo.getTinyid() == 0) {
                IMMsfCoreProxy.errorOnMainThread(this.a, BaseConstants.ERR_LOGGED_OUT_BEFORE_LOGIN_FINISHED, "logout explicitly or kicked off by other device");
                return;
            }
            aa.a().a(new bf(this, tIMCallBack, msfUserInfo));
        }
    }

    public final void a(int i, String str) {
        QLog.e("imsdk.IMCoreWrapper", 1, "Login|4-InitIMCore|Fail|imcore init failed! code: " + i + " desc: " + str);
        IMMsfCoreProxy.errorOnMainThread(this.a, i, str);
    }
}
