package com.tencent;

import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.util.QualityReportHelper;

final class fx extends ah {
    private /* synthetic */ QualityReportHelper b;

    fx(TIMGroupManager tIMGroupManager, TIMCallBack tIMCallBack, QualityReportHelper qualityReportHelper) {
        this.b = qualityReportHelper;
        super(tIMGroupManager, tIMCallBack);
    }

    public final void a() {
        QLog.i("MSF.C.TIMGroupManager", 1, "JoinGroup|3-Callback|Succ|code=0");
        this.a.onSuccess();
        this.b.init(QrEventType.kEventJoinGroup.swigValue(), 0, "");
        this.b.report();
    }

    public final void a(int i, String str) {
        QLog.i("MSF.C.TIMGroupManager", 1, "JoinGroup|3-Callback|Succ|code=" + i + ", msg=" + str);
        this.a.onError(i, str);
        this.b.init(QrEventType.kEventJoinGroup.swigValue(), i, str);
        this.b.report();
    }
}
