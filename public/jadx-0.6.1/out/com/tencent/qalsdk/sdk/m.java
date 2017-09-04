package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.QALCallBack;
import com.tencent.qalsdk.util.QLog;

/* compiled from: CoreWrapper */
class m implements QALCallBack {
    final /* synthetic */ e a;

    m(e eVar) {
        this.a = eVar;
    }

    public void onError(int i, String str) {
        QLog.e("CoreWrapper", "register resp 1002,unregister error:" + i + ":" + str);
    }

    public void onSuccess() {
        QLog.e("CoreWrapper", "register resp 1002,unregister succ");
    }
}
