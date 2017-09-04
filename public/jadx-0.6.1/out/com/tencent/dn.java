package com.tencent;

import java.util.List;

final class dn extends ad {
    dn(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMFriendGroup> list) {
        this.a.onSuccess(list);
    }
}
