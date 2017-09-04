package com.tencent.qalsdk.util;

import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSSSOGuestLoginListener;
import tencent.tls.platform.TLSUserInfo;

/* compiled from: GuestHelper */
class e implements TLSSSOGuestLoginListener {
    final /* synthetic */ GuestHelper a;

    e(GuestHelper guestHelper) {
        this.a = guestHelper;
    }

    public void OnGuestLoginFail(TLSErrInfo tLSErrInfo) {
        QLog.e("GuestHelper", 1, "OnGuestLoginFail:" + tLSErrInfo.ErrCode + ":" + tLSErrInfo.Msg);
    }

    public void OnGuestLoginSuccess(TLSUserInfo tLSUserInfo) {
        com.tencent.qalsdk.sdk.e.b().a(tLSUserInfo.identifier);
        QLog.d("GuestHelper", 1, "OnGuestLoginSuccess:" + tLSUserInfo.identifier);
        com.tencent.qalsdk.sdk.e.b().b(tLSUserInfo.identifier, new f(this));
    }

    public void OnGuestLoginTimeout(TLSErrInfo tLSErrInfo) {
        QLog.e("GuestHelper", 1, "OnGuestLoginTimeout:" + tLSErrInfo.ErrCode + ":" + tLSErrInfo.Msg);
    }
}
