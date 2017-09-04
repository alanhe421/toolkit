package com.tencent.imsdk;

import com.tencent.TIMValueCallBack;
import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.IMError;

final class bh implements TIMValueCallBack<byte[]> {
    private /* synthetic */ IMCmdListener a;

    bh(IMMsfCoreProxy iMMsfCoreProxy, IMCmdListener iMCmdListener) {
        this.a = iMCmdListener;
    }

    public final void onError(int i, String str) {
        this.a.onError(IMError.FAIL, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.a.onSucc((byte[]) obj);
    }
}
