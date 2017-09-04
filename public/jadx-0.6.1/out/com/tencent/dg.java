package com.tencent;

import java.util.List;

final class dg extends ah<List<TIMFriendResult>> {
    dg(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMFriendResult> list) {
        this.a.onSuccess(list);
    }
}
