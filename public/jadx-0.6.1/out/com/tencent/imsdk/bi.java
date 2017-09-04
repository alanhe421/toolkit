package com.tencent.imsdk;

import com.tencent.IMCoreWrapper;
import com.tencent.IMErrInfo;
import com.tencent.TIMCallBack;
import com.tencent.TIMNetworkStatus;
import com.tencent.TIMUser;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.util.QualityReportHelper;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.HashMap;
import java.util.Map;

final class bi implements QALCallBack {
    final /* synthetic */ TIMUser a;
    final /* synthetic */ TIMCallBack b;
    final /* synthetic */ IMMsfUserInfo c;
    private /* synthetic */ QualityReportHelper d;
    private /* synthetic */ IMMsfCoreProxy e;

    bi(IMMsfCoreProxy iMMsfCoreProxy, TIMUser tIMUser, TIMCallBack tIMCallBack, QualityReportHelper qualityReportHelper, IMMsfUserInfo iMMsfUserInfo) {
        this.e = iMMsfCoreProxy;
        this.a = tIMUser;
        this.b = tIMCallBack;
        this.d = qualityReportHelper;
        this.c = iMMsfUserInfo;
    }

    public final void onError(int i, String str) {
        IMErrInfo iMErrInfo = new IMErrInfo(i, str);
        BaseConstants.covertErrorCode(iMErrInfo);
        if (iMErrInfo.getCode() == BaseConstants.ERR_LOGIN_KICKED_OFF_BY_OTHER) {
            this.e.logout(this.a.getIdentifier());
        }
        IMMsfCoreProxy.errorOnMainThread(this.b, iMErrInfo.getCode(), iMErrInfo.getMsg());
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|3-Online|Fail|bindID failed, code: " + iMErrInfo.getCode() + ", desc: " + iMErrInfo.getMsg());
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "bindID failed|code: " + iMErrInfo.getCode() + " desc: " + iMErrInfo.getMsg());
        BeaconUtil.onEvent(BeaconEvents.loginEvent, false, -1, -1, hashMap, false);
        this.d.init(QrEventType.kEventLogin.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
        this.d.report();
    }

    public final void onSuccess() {
        this.c.setIsLoggedIn(true);
        this.e.networkStatus = TIMNetworkStatus.TIM_NETWORK_STATUS_CONNECTED;
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Login|3-Online|Succ|bindID succ");
        if (this.e.mode == 1) {
            IMCoreWrapper.get().initIMCore(this.e.context, this.a.getIdentifier(), this.b);
        } else if (this.c.getUser() == null || this.c.getTinyid() == 0) {
            String str = "logout explicitly or kicked off by other device";
            IMMsfCoreProxy.errorOnMainThread(this.b, BaseConstants.ERR_LOGGED_OUT_BEFORE_LOGIN_FINISHED, str);
            this.d.init(QrEventType.kEventLogin.swigValue(), BaseConstants.ERR_LOGGED_OUT_BEFORE_LOGIN_FINISHED, str);
            this.d.report();
            return;
        } else {
            IMMsfCoreProxy.mainHandler.post(new bj(this));
        }
        this.d.init(QrEventType.kEventLogin.swigValue(), 0, "");
        this.d.report();
        BeaconUtil.onEvent(BeaconEvents.loginEvent, true, -1, -1, null, false);
    }
}
