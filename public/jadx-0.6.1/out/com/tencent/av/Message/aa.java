package com.tencent.av.Message;

import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.openqq.protocol.imsdk.videoinvitation.RsqMsgBody;

final class aa implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMCallBack a;

    aa(AvMsg0x32 avMsg0x32, TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        try {
            new RsqMsgBody().mergeFrom((byte[]) obj);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
