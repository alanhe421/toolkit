package com.tencent;

import java.util.List;

final class cw extends ah<List<TIMFriendResult>> {
    cw(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMFriendResult> list) {
        this.a.onSuccess(list);
    }
}
