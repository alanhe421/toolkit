package com.tencent.qalsdk.util;

import com.tencent.qalsdk.QALCallBack;

/* compiled from: GuestHelper */
class b implements QALCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ GuestHelper b;

    b(GuestHelper guestHelper, String str) {
        this.b = guestHelper;
        this.a = str;
    }

    public void onError(int i, String str) {
        QLog.e("GuestHelper", 1, this.a + " bindID fail:" + i + ":" + str);
    }

    public void onSuccess() {
        QLog.i("GuestHelper", 1, this.a + " bindID succ");
    }
}
