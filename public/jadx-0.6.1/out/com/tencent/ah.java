package com.tencent;

import java.util.List;

final class ah implements Runnable {
    private /* synthetic */ List a;
    private /* synthetic */ IMCoreFriendshipProxyCallback b;

    ah(IMCoreFriendshipProxyCallback iMCoreFriendshipProxyCallback, List list) {
        this.b = iMCoreFriendshipProxyCallback;
        this.a = list;
    }

    public final void run() {
        TIMManager.getInstanceById(this.b.identify).getFriendshipProxyListener().OnDelFriends(this.a);
    }
}
