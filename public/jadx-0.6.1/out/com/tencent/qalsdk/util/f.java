package com.tencent.qalsdk.util;

import com.tencent.qalsdk.QALCallBack;

/* compiled from: GuestHelper */
class f implements QALCallBack {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void onError(int i, String str) {
        QLog.e("GuestHelper", 1, "bindID fail:" + i + ":" + str);
    }

    public void onSuccess() {
        QLog.e("GuestHelper", 1, "bindID succ");
    }
}
