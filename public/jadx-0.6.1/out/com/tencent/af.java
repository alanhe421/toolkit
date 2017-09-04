package com.tencent;

final class af implements Runnable {
    private /* synthetic */ TIMFriendshipProxyStatus a;
    private /* synthetic */ IMCoreFriendshipProxyCallback b;

    af(IMCoreFriendshipProxyCallback iMCoreFriendshipProxyCallback, TIMFriendshipProxyStatus tIMFriendshipProxyStatus) {
        this.b = iMCoreFriendshipProxyCallback;
        this.a = tIMFriendshipProxyStatus;
    }

    public final void run() {
        TIMManager.getInstanceById(this.b.identify).getFriendshipProxyListener().OnProxyStatusChange(this.a);
    }
}
