package com.tencent;

import com.tencent.TIMGroupManager.CreateGroupCallBack;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.util.QualityReportHelper;
import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.HashMap;
import java.util.Map;

final class en extends CreateGroupCallBack {
    private /* synthetic */ QualityReportHelper a;

    en(TIMGroupManager tIMGroupManager, TIMValueCallBack tIMValueCallBack, QualityReportHelper qualityReportHelper) {
        this.a = qualityReportHelper;
        super(tIMValueCallBack);
    }

    public final void onDone(String str) {
        this.cb.onSuccess(str);
        BeaconUtil.onEvent(BeaconEvents.createGroup, true, -1, -1, null, false);
        this.a.init(QrEventType.kEventCreateGroup.swigValue(), 0, "");
        this.a.report();
    }

    public final void onFail(int i, String str) {
        Map hashMap = new HashMap();
        hashMap.put("param_FailCode", "bindID failed|code: " + i + " desc: " + str);
        this.cb.onError(i, str);
        BeaconUtil.onEvent(BeaconEvents.createGroup, false, -1, -1, hashMap, false);
        this.a.init(QrEventType.kEventCreateGroup.swigValue(), i, str);
        this.a.report();
    }
}
