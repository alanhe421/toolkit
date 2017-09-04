package com.tencent;

final class dc extends ac {
    dc(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMGetFriendFutureListSucc tIMGetFriendFutureListSucc) {
        this.a.onSuccess(tIMGetFriendFutureListSucc);
    }
}
