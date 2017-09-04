package com.tencent.imsdk.av;

import com.tencent.IMFunc;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.WrapCmdListener;
import com.tencent.openqq.IMError;
import com.tencent.openqq.IMPushListener;
import com.tencent.openqq.IMReqListener;
import com.tencent.openqq.protocol.imsdk.sharp.RspBody;

final class ac extends WrapCmdListener<IMReqListener> {
    private /* synthetic */ SharpTinyId a;

    ac(SharpTinyId sharpTinyId, IMReqListener iMReqListener) {
        this.a = sharpTinyId;
        super(iMReqListener);
    }

    public final void onError(IMError iMError, String str) {
        QLog.e("MSF.C.SharpTinyId", 1, "sharp|req failed: " + iMError + " desc: " + str);
        ((IMReqListener) this.listener).onError(iMError, str);
    }

    public final void onSucc(byte[] bArr) {
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            if (rspBody.msg_cmd_error_code.uint32_code.get() != 0) {
                QLog.e("MSF.C.SharpTinyId", 1, "sharp|svr ret: " + rspBody.msg_cmd_error_code.uint32_code.get() + " err msg: " + rspBody.msg_cmd_error_code.bytes_err_msg.get().toStringUtf8());
                ((IMReqListener) this.listener).onError(IMError.FAIL, rspBody.msg_cmd_error_code.bytes_err_msg.get().toStringUtf8());
                return;
            }
            ((IMReqListener) this.listener).onSucc();
            IMPushListener sharpSvrRspListener = this.a.getSharpSvrRspListener();
            if (sharpSvrRspListener != null) {
                QLog.i("MSF.C.SharpTinyId", 1, "sharp|call rsp listener");
                sharpSvrRspListener.onRecv(rspBody.bytes_rsp_data.get().toByteArray());
            }
        } catch (Throwable th) {
            QLog.e("MSF.C.SharpTinyId", 1, IMFunc.getExceptionInfo(th));
            QLog.e("MSF.C.SharpTinyId", 1, "sharp|parse svr rsp failed");
            ((IMReqListener) this.listener).onError(IMError.FAIL, "");
        }
    }
}
