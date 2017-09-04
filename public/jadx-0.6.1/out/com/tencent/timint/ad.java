package com.tencent.timint;

import com.tencent.IMFunc;
import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.quality_report.ReqBody;
import com.tencent.openqq.protocol.imsdk.quality_report.RspBody;

final class ad implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMCallBack a;
    private /* synthetic */ ReqBody b;
    private /* synthetic */ TIMIntManager c;

    ad(TIMIntManager tIMIntManager, TIMCallBack tIMCallBack, ReqBody reqBody) {
        this.c = tIMIntManager;
        this.a = tIMCallBack;
        this.b = reqBody;
    }

    public final void onError(int i, String str) {
        QLog.d("MSF.C.TIMIntManager", 1, "request faild! code: " + i + " desc: " + str);
        this.a.onError(i, str);
        this.c.storeQualityReportToLocal(this.b);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
        } catch (Throwable th) {
            QLog.e("MSF.C.TIMIntManager", 1, IMFunc.getExceptionInfo(th));
            QLog.d("MSF.C.TIMIntManager", 1, "parse quality_report svr rsp failed");
            this.a.onError(6001, "parse rsp failed");
        }
        if (rspBody.uint32_result.get() != 0) {
            this.a.onError(rspBody.uint32_result.get(), rspBody.str_err_msg.get());
        } else {
            this.a.onSuccess();
        }
        this.c.resendLocalQualityReport("AVQualityReportSvc.C2S");
    }
}
