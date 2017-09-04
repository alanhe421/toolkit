package com.tencent;

final class db extends ae {
    db(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMGetFriendPendencyListSucc tIMGetFriendPendencyListSucc) {
        this.a.onSuccess(tIMGetFriendPendencyListSucc);
    }
}
