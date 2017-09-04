package com.tencent;

final class dp extends ab {
    dp(TIMFriendshipManager tIMFriendshipManager, TIMCallBack tIMCallBack) {
        super(tIMFriendshipManager, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
