package com.tencent.imsdk.av;

import com.tencent.IMFunc;
import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.sharp.RspBody;

final class ad implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMCallBack a;
    private /* synthetic */ SharpTinyId b;

    ad(SharpTinyId sharpTinyId, TIMCallBack tIMCallBack) {
        this.b = sharpTinyId;
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        QLog.e("MSF.C.SharpTinyId", 1, "sharp|req failed: " + i + " desc: " + str);
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            if (rspBody.msg_cmd_error_code.uint32_code.get() != 0) {
                QLog.e("MSF.C.SharpTinyId", 1, "sharp|svr ret: " + rspBody.msg_cmd_error_code.uint32_code.get() + " err msg: " + rspBody.msg_cmd_error_code.bytes_err_msg.get().toStringUtf8());
                this.a.onError(rspBody.msg_cmd_error_code.uint32_code.get(), rspBody.msg_cmd_error_code.bytes_err_msg.get().toStringUtf8());
                return;
            }
            this.a.onSuccess();
            TIMValueCallBack sharpSvrPushCallBack = this.b.getSharpSvrPushCallBack();
            if (sharpSvrPushCallBack != null) {
                QLog.i("MSF.C.SharpTinyId", 1, "sharp|call rsp callback");
                sharpSvrPushCallBack.onSuccess(rspBody.bytes_rsp_data.get().toByteArray());
            }
        } catch (Throwable th) {
            QLog.e("MSF.C.SharpTinyId", 1, IMFunc.getExceptionInfo(th));
            QLog.e("MSF.C.SharpTinyId", 1, "sharp|parse svr rsp failed");
            this.a.onError(6001, "parse rsp failed");
        }
    }
}
