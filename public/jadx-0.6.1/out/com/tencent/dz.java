package com.tencent;

final class dz implements Runnable {
    private /* synthetic */ TIMGetFriendFutureListSucc a;
    private /* synthetic */ ac b;

    dz(ac acVar, TIMGetFriendFutureListSucc tIMGetFriendFutureListSucc) {
        this.b = acVar;
        this.a = tIMGetFriendFutureListSucc;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
