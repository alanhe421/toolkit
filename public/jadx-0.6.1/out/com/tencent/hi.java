package com.tencent;

import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.util.QualityReportHelper;
import java.util.List;

final class hi extends bh<TIMCallBack> {
    private /* synthetic */ String b;
    private /* synthetic */ QualityReportHelper c;

    hi(TIMSnapshot tIMSnapshot, TIMCallBack tIMCallBack, String str, QualityReportHelper qualityReportHelper) {
        this.b = str;
        this.c = qualityReportHelper;
        super(tIMCallBack);
    }

    public final void a(int i, String str) {
        ((TIMCallBack) this.a).onError(i, str);
        this.c.init(i, str);
        this.c.report();
    }

    public final void a(List<String> list) {
        IMMsfCoreProxy.get().downloadToFile(list, this.b, (TIMCallBack) this.a, this.c);
    }
}
