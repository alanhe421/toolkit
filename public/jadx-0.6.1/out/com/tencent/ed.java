package com.tencent;

final class ed implements Runnable {
    private /* synthetic */ TIMGetFriendPendencyListSucc a;
    private /* synthetic */ ae b;

    ed(ae aeVar, TIMGetFriendPendencyListSucc tIMGetFriendPendencyListSucc) {
        this.b = aeVar;
        this.a = tIMGetFriendPendencyListSucc;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
