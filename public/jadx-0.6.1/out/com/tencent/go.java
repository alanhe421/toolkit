package com.tencent;

final class go implements Runnable {
    private /* synthetic */ TIMGroupPendencyListGetSucc a;
    private /* synthetic */ ai b;

    go(ai aiVar, TIMGroupPendencyListGetSucc tIMGroupPendencyListGetSucc) {
        this.b = aiVar;
        this.a = tIMGroupPendencyListGetSucc;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
