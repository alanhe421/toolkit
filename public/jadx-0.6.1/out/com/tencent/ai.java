package com.tencent;

import java.util.List;

final class ai implements Runnable {
    private /* synthetic */ List a;
    private /* synthetic */ IMCoreFriendshipProxyCallback b;

    ai(IMCoreFriendshipProxyCallback iMCoreFriendshipProxyCallback, List list) {
        this.b = iMCoreFriendshipProxyCallback;
        this.a = list;
    }

    public final void run() {
        TIMManager.getInstanceById(this.b.identify).getFriendshipProxyListener().OnFriendProfileUpdate(this.a);
    }
}
