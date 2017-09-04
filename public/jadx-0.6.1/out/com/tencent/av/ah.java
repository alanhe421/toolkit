package com.tencent.av;

import com.tencent.IMFunc;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.gv_comm_operate.RspBody;

final class ah implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMValueCallBack a;

    ah(TIMAvManager tIMAvManager, TIMValueCallBack tIMValueCallBack) {
        this.a = tIMValueCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        byte[] parseRsp = NetworkUtil.parseRsp(bArr);
        if (parseRsp == null) {
            this.a.onError(6001, "parse recorder rsp failed");
            return;
        }
        try {
            rspBody.mergeFrom(parseRsp);
            if (rspBody.rsp_0x5.uint32_result.get() != 0) {
                QLog.d("MSF.C.TIMAvManager", 1, "recorder svr ret: " + rspBody.rsp_0x5.uint32_result.get() + " err: " + rspBody.rsp_0x5.str_errorinfo.get());
                this.a.onError(rspBody.rsp_0x5.uint32_result.get(), rspBody.rsp_0x5.str_errorinfo.get());
                return;
            }
            this.a.onSuccess(rspBody.rsp_0x5.str_fileID.get());
        } catch (Throwable th) {
            QLog.e("MSF.C.TIMAvManager", 1, IMFunc.getExceptionInfo(th));
            QLog.e("MSF.C.TIMAvManager", 1, "parse recorder svr rsp failed");
            this.a.onError(6001, "parse recorder rsp failed");
        }
    }
}
