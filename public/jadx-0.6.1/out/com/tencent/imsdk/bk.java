package com.tencent.imsdk;

import com.tencent.IMErrInfo;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.util.QualityReportHelper;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.HashMap;
import java.util.Map;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSExchangeTicketListener;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSUserInfo;

final class bk implements TLSExchangeTicketListener {
    private /* synthetic */ TIMUser a;
    private /* synthetic */ IMMsfUserInfo b;
    private /* synthetic */ QALCallBack c;
    private /* synthetic */ TIMCallBack d;
    private /* synthetic */ QualityReportHelper e;
    private /* synthetic */ IMMsfCoreProxy f;

    bk(IMMsfCoreProxy iMMsfCoreProxy, TIMUser tIMUser, IMMsfUserInfo iMMsfUserInfo, QALCallBack qALCallBack, TIMCallBack tIMCallBack, QualityReportHelper qualityReportHelper) {
        this.f = iMMsfCoreProxy;
        this.a = tIMUser;
        this.b = iMMsfUserInfo;
        this.c = qALCallBack;
        this.d = tIMCallBack;
        this.e = qualityReportHelper;
    }

    public final void OnExchangeTicketFail(TLSErrInfo tLSErrInfo) {
        IMErrInfo iMErrInfo = new IMErrInfo(tLSErrInfo.ErrCode, tLSErrInfo.Msg);
        BaseConstants.covertErrorCode(iMErrInfo);
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|2-Ticket|Fail|OnExchangeTicketFail|code: " + iMErrInfo.getCode() + " desc: " + iMErrInfo.getMsg());
        IMMsfCoreProxy.errorOnMainThread(this.d, iMErrInfo.getCode(), iMErrInfo.getMsg());
        this.e.init(QrEventType.kEventLogin.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
        this.e.report();
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "OnExchangeTicketFail|code: " + iMErrInfo.getCode() + " desc: " + iMErrInfo.getMsg());
        BeaconUtil.onEvent(BeaconEvents.loginEvent, false, -1, -1, hashMap, false);
    }

    public final void OnExchangeTicketSuccess(TLSUserInfo tLSUserInfo) {
        try {
            Map sSOTicket = TLSLoginHelper.getInstance().getSSOTicket(this.a.getIdentifier());
            this.b.setTinyid(((Long) sSOTicket.get("tinyID")).longValue());
            QLog.w("imsdk.IMMsfCoreProxy", 1, "IMMsfCoreProxy|login update id:" + this.a.getIdentifier() + "/" + sSOTicket.get("identifier").toString());
            TIMManager instanceById = TIMManager.getInstanceById(this.a.getIdentifier());
            this.a.setIdentifier(sSOTicket.get("identifier").toString());
            this.f.mutiUserMap.put(this.a.getIdentifier(), this.b);
            instanceById.setIdentification(this.a.getIdentifier(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|2-Ticket|Succ|OnExchangeTicketSuccess, tinyid:" + this.b.getTinyid());
        QALSDKManager.getInstance().bindID(tLSUserInfo.identifier, this.c);
    }

    public final void OnExchangeTicketTimeout(TLSErrInfo tLSErrInfo) {
        IMErrInfo iMErrInfo = new IMErrInfo(tLSErrInfo.ErrCode, tLSErrInfo.Msg);
        BaseConstants.covertErrorCode(iMErrInfo);
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|2-Ticket|Fail|OnExchangeTicketTimeout|code:" + iMErrInfo.getCode() + " desc: " + iMErrInfo.getMsg());
        IMMsfCoreProxy.errorOnMainThread(this.d, iMErrInfo.getCode(), iMErrInfo.getMsg());
        this.e.init(QrEventType.kEventLogin.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
        this.e.report();
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "OnExchangeTicketTimeout|code: " + iMErrInfo.getCode() + " desc: " + iMErrInfo.getMsg());
        BeaconUtil.onEvent(BeaconEvents.loginEvent, false, -1, -1, hashMap, false);
    }
}
