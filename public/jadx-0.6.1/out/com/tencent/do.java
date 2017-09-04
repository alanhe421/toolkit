package com.tencent;

import java.util.ArrayList;
import java.util.List;

final class do extends ah<List<TIMFriendCheckResult>> {
    do(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMFriendResult> list) {
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new TIMFriendCheckResult((TIMFriendResult) list.get(i)));
        }
        this.a.onSuccess(arrayList);
    }
}
