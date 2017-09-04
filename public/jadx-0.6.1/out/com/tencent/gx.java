package com.tencent;

import com.tencent.imsdk.QLog;

final class gx extends au {
    gx(TIMManager tIMManager, TIMCallBack tIMCallBack) {
        super(tIMCallBack);
    }

    public final void a() {
        QLog.e("imsdk.TIMManager", 1, "initStorage succ");
        IMCoreWrapper.get().setReady(true);
        if (this.a != null) {
            this.a.onSuccess();
        }
    }

    public final void a(int i, String str) {
        QLog.e("imsdk.TIMManager", 1, "initStorage failed|code: " + i + " desc: " + str);
        if (this.a != null) {
            this.a.onError(i, str);
        }
    }
}
