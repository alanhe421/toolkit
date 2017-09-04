package com.tencent;

final class cn extends aa {
    cn(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMUserSearchSucc tIMUserSearchSucc) {
        this.a.onSuccess(tIMUserSearchSucc);
    }
}
