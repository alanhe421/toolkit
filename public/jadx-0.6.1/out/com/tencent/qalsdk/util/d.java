package com.tencent.qalsdk.util;

import com.tencent.qalsdk.QALCallBack;

/* compiled from: GuestHelper */
class d implements QALCallBack {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onError(int i, String str) {
        QLog.e("GuestHelper", 1, this.a.a + "tlsRefreshID bindID fail:" + i + ":" + str);
    }

    public void onSuccess() {
        QLog.i("GuestHelper", 1, this.a.a + "tlsRefreshID bindID succ");
    }
}
