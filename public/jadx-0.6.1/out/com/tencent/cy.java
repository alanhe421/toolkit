package com.tencent;

import java.util.ArrayList;
import java.util.List;

final class cy extends ah<List<String>> {
    cy(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMFriendResult> list) {
        List arrayList = new ArrayList();
        for (TIMFriendResult identifer : list) {
            arrayList.add(identifer.getIdentifer());
        }
        this.a.onSuccess(arrayList);
    }
}
