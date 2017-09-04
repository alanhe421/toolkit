package com.tencent.qalsdk.util;

import com.tencent.qalsdk.sdk.e;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSRefreshUserSigListener;
import tencent.tls.platform.TLSUserInfo;

/* compiled from: GuestHelper */
class c implements TLSRefreshUserSigListener {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ GuestHelper c;

    c(GuestHelper guestHelper, String str, boolean z) {
        this.c = guestHelper;
        this.a = str;
        this.b = z;
    }

    public void OnRefreshUserSigFail(TLSErrInfo tLSErrInfo) {
        QLog.e("GuestHelper", 1, "OnRefreshUserSigFail:" + this.a + ":" + this.b);
    }

    public void OnRefreshUserSigSuccess(TLSUserInfo tLSUserInfo) {
        QLog.i("GuestHelper", 1, "TLSRefreshUserSig succ:" + this.a + ":" + this.b);
        if (this.b) {
            e.b().b(this.a, new d(this));
        }
    }

    public void OnRefreshUserSigTimeout(TLSErrInfo tLSErrInfo) {
        QLog.e("GuestHelper", 1, "OnRefreshUserSigTimeout:" + this.a);
    }
}
