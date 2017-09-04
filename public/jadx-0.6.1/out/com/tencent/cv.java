package com.tencent;

final class cv extends ag<TIMGetFriendListV2Succ> {
    cv(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMGetFriendListV2Succ tIMGetFriendListV2Succ) {
        this.a.onSuccess(tIMGetFriendListV2Succ);
    }
}
