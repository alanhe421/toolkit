package com.tencent.imsdk.av;

import com.tencent.IMFunc;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.WrapCmdListener;
import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.IMError;
import com.tencent.openqq.protocol.imsdk.relay.RspBody;

final class aa extends WrapCmdListener<IMCmdListener> {
    private /* synthetic */ String a;

    aa(MultiVideoTinyId multiVideoTinyId, IMCmdListener iMCmdListener, String str) {
        this.a = str;
        super(iMCmdListener);
    }

    public final void onError(IMError iMError, String str) {
        QLog.e("IMSdk.MultiVideoTinyId", 1, "multivideo|failed: " + iMError + " desc: " + str);
        ((IMCmdListener) this.listener).onError(iMError, str);
    }

    public final void onSucc(byte[] bArr) {
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            QLog.i("IMSdk.MultiVideoTinyId", 1, "multivideo|callback: " + this.a);
            ((IMCmdListener) this.listener).onSucc(rspBody.rspbody.get().toByteArray());
        } catch (Throwable th) {
            QLog.e("IMSdk.MultiVideoTinyId", 1, IMFunc.getExceptionInfo(th));
            QLog.e("IMSdk.MultiVideoTinyId", 1, "multivideo|parse failed");
            ((IMCmdListener) this.listener).onError(IMError.FAIL, "parse rsp failed");
        }
    }
}
