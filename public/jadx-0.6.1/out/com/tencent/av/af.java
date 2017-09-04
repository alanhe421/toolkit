package com.tencent.av;

import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import java.util.List;

final class af implements TIMValueCallBack<List<String>> {
    private /* synthetic */ TIMCallBack a;

    af(TIMAvManager tIMAvManager, TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        this.a.onSuccess();
    }
}
