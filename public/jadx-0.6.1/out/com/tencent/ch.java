package com.tencent;

import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.util.QualityReportHelper;
import java.util.List;

final class ch extends bh<TIMValueCallBack<byte[]>> {
    private /* synthetic */ QualityReportHelper b;

    ch(TIMFileElem tIMFileElem, TIMValueCallBack tIMValueCallBack, QualityReportHelper qualityReportHelper) {
        this.b = qualityReportHelper;
        super(tIMValueCallBack);
    }

    public final void a(int i, String str) {
        ((TIMValueCallBack) this.a).onError(i, str);
        this.b.init(i, str);
        this.b.report();
    }

    public final void a(List<String> list) {
        IMMsfCoreProxy.get().downloadToBuff(list, (TIMValueCallBack) this.a, this.b);
    }
}
