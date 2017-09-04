package com.tencent;

import java.util.List;

final class dl implements TIMValueCallBack<List<TIMFriendResult>> {
    private /* synthetic */ TIMCallBack a;

    dl(TIMFriendshipManager tIMFriendshipManager, TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
    }

    public final void onError(int i, String str) {
        this.a.onError(i, str);
    }

    public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        this.a.onSuccess();
    }
}
