package com.tencent.av;

import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.av.TIMAvManager.StreamRes;

final class ae implements TIMValueCallBack<StreamRes> {
    private /* synthetic */ TIMCallBack a;

    ae(TIMAvManager tIMAvManager, TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        this.a.onSuccess();
    }
}
