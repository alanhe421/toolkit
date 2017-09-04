package com.tencent.imsdk;

import com.tencent.IMErrInfo;
import com.tencent.TIMCallBack;
import com.tencent.qalsdk.QALCallBack;
import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.HashMap;
import java.util.Map;

final class ac implements QALCallBack {
    private /* synthetic */ TIMCallBack a;

    ac(IMMsfCoreProxy iMMsfCoreProxy, TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        IMErrInfo iMErrInfo = new IMErrInfo(i, str);
        BaseConstants.covertErrorCode(iMErrInfo);
        QLog.e("imsdk.IMMsfCoreProxy", 1, "unBindId failed. code: " + iMErrInfo.getCode() + "|desc: " + iMErrInfo.getMsg());
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "bindID failed|code: " + iMErrInfo.getCode() + " desc: " + iMErrInfo.getMsg());
        BeaconUtil.onEvent(BeaconEvents.logoutEvent, false, -1, -1, hashMap, false);
        if (this.a != null) {
            this.a.onError(i, str);
            QLog.i("imsdk.IMMsfCoreProxy", 1, "Logout|2-Callback|Succ|logout failed! code: " + i + ", desc: " + str);
            return;
        }
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Logout|2-Callback|Fail|user not set logout callback");
    }

    public final void onSuccess() {
        QLog.d("imsdk.IMMsfCoreProxy", 1, "unBindId succ");
        BeaconUtil.onEvent(BeaconEvents.logoutEvent, true, -1, -1, null, false);
        if (this.a != null) {
            this.a.onSuccess();
            QLog.i("imsdk.IMMsfCoreProxy", 1, "Logout|2-Callback|Succ|logout succ");
            return;
        }
        QLog.e("imsdk.IMMsfCoreProxy", 1, "Logout|2-Callback|Fail|user not set logout callback");
    }
}
