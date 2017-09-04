package com.tencent.qalsdk;

import com.tencent.qalsdk.util.QLog;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSExchangeTicketListener;
import tencent.tls.platform.TLSUserInfo;

/* compiled from: QALSDKManager */
class b implements TLSExchangeTicketListener {
    final /* synthetic */ String a;
    final /* synthetic */ QALCallBack b;
    final /* synthetic */ QALSDKManager c;

    b(QALSDKManager qALSDKManager, String str, QALCallBack qALCallBack) {
        this.c = qALSDKManager;
        this.a = str;
        this.b = qALCallBack;
    }

    public void OnExchangeTicketFail(TLSErrInfo tLSErrInfo) {
        QLog.e("QALSDKManager", 1, "[TLSExchangeTicket] fail:" + this.a + ":" + tLSErrInfo.ErrCode + ":" + tLSErrInfo.ExtraMsg);
        this.b.onError(tLSErrInfo.ErrCode, tLSErrInfo.ExtraMsg);
    }

    public void OnExchangeTicketSuccess(TLSUserInfo tLSUserInfo) {
        this.c.bindID(tLSUserInfo.identifier, this.b);
    }

    public void OnExchangeTicketTimeout(TLSErrInfo tLSErrInfo) {
        this.b.onError(tLSErrInfo.ErrCode, tLSErrInfo.ExtraMsg);
        QLog.e("QALSDKManager", 1, "[TLSExchangeTicket] Timeout:" + this.a + ":" + tLSErrInfo.ErrCode + ":" + tLSErrInfo.ExtraMsg);
    }
}
