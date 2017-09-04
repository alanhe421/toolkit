package com.tencent;

final class eh implements Runnable {
    private /* synthetic */ TIMGetFriendListV2Succ a;
    private /* synthetic */ ag b;

    eh(ag agVar, TIMGetFriendListV2Succ tIMGetFriendListV2Succ) {
        this.b = agVar;
        this.a = tIMGetFriendListV2Succ;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
