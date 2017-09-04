package com.tencent.imsdk;

import com.tencent.IMErrInfo;
import com.tencent.IMFunc;
import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.util.QualityReportHelper;
import com.tencent.openqq.protocol.imsdk.common.CmdErrorCode;
import com.tencent.openqq.protocol.imsdk.stat_settoken.RspBody;

final class ad implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMCallBack a;
    private /* synthetic */ QualityReportHelper b;

    ad(IMMsfCoreProxy iMMsfCoreProxy, TIMCallBack tIMCallBack, QualityReportHelper qualityReportHelper) {
        this.a = tIMCallBack;
        this.b = qualityReportHelper;
    }

    public final void onError(int i, String str) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "settoken request failed, code: " + i + "|desc: " + str);
        if (this.a != null) {
            this.a.onError(i, str);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            if (rspBody.enum_cmd_error_code.has()) {
                int i = ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).uint32_code.get();
                if (i != 0) {
                    String toStringUtf8 = ((CmdErrorCode) rspBody.enum_cmd_error_code.get()).bytes_err_msg.get().toStringUtf8();
                    QLog.e("imsdk.IMMsfCoreProxy", 1, "settoken failed, code: " + i + "|desc: " + toStringUtf8);
                    this.b.init(i, toStringUtf8);
                    this.b.report();
                    if (this.a != null) {
                        this.a.onError(i, toStringUtf8);
                        return;
                    }
                    return;
                }
            }
            QLog.i("imsdk.IMMsfCoreProxy", 1, "settoken succ");
            this.b.init(0, "");
            this.b.report();
            if (this.a != null) {
                this.a.onSuccess();
            }
        } catch (Throwable th) {
            IMErrInfo iMErrInfo = new IMErrInfo(6001, "setToken failed, parse response failed, " + IMFunc.getExceptionInfo(th));
            QLog.e("imsdk.IMMsfCoreProxy", 1, iMErrInfo.getMsg());
            this.b.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
            this.b.report();
            QLog.e("imsdk.IMMsfCoreProxy", 1, iMErrInfo.getMsg());
            if (this.a != null) {
                this.a.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            }
        }
    }
}
