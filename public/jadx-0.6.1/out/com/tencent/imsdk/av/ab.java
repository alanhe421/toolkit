package com.tencent.imsdk.av;

import com.tencent.IMFunc;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.relay.RspBody;

final class ab implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMValueCallBack a;
    private /* synthetic */ String b;

    ab(MultiVideoTinyId multiVideoTinyId, TIMValueCallBack tIMValueCallBack, String str) {
        this.a = tIMValueCallBack;
        this.b = str;
    }

    public final void onError(int i, String str) {
        QLog.e("IMSdk.MultiVideoTinyId", 1, "multivideo|failed: " + i + " desc: " + str);
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            QLog.i("IMSdk.MultiVideoTinyId", 1, "multivideo|callback: " + this.b);
            this.a.onSuccess(rspBody.rspbody.get().toByteArray());
        } catch (Throwable th) {
            QLog.e("IMSdk.MultiVideoTinyId", 1, IMFunc.getExceptionInfo(th));
            QLog.e("IMSdk.MultiVideoTinyId", 1, "multivideo|parse failed");
            this.a.onError(6001, "parse rsp failed");
        }
    }
}
