package com.tencent;

import java.util.List;

final class bb implements Runnable {
    private /* synthetic */ TIMRefreshListener a;
    private /* synthetic */ List b;

    bb(IMCoreNotify iMCoreNotify, TIMRefreshListener tIMRefreshListener, List list) {
        this.a = tIMRefreshListener;
        this.b = list;
    }

    public final void run() {
        this.a.onRefreshConversation(this.b);
    }
}
