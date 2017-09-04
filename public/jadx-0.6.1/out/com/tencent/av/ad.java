package com.tencent.av;

import com.tencent.IMFunc;
import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.videoinvitation.RsqMsgBody;

final class ad implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMCallBack a;

    ad(TIMAvManager tIMAvManager, TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        try {
            new RsqMsgBody().mergeFrom((byte[]) obj);
        } catch (Throwable th) {
            QLog.e("MSF.C.TIMAvManager", 1, IMFunc.getExceptionInfo(th));
        }
    }
}
