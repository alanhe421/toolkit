package com.tencent;

import java.util.List;

final class dk extends ah<List<TIMFriendResult>> {
    dk(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMFriendResult> list) {
        this.a.onSuccess(list);
    }
}
