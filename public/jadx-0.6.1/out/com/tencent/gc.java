package com.tencent;

final class gc implements Runnable {
    private /* synthetic */ TIMGroupSearchSucc a;
    private /* synthetic */ ab b;

    gc(ab abVar, TIMGroupSearchSucc tIMGroupSearchSucc) {
        this.b = abVar;
        this.a = tIMGroupSearchSucc;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
