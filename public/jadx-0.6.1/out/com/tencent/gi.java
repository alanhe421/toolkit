package com.tencent;

final class gi implements Runnable {
    private /* synthetic */ TIMGroupMemberSuccV2 a;
    private /* synthetic */ ae b;

    gi(ae aeVar, TIMGroupMemberSuccV2 tIMGroupMemberSuccV2) {
        this.b = aeVar;
        this.a = tIMGroupMemberSuccV2;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
