package com.tencent;

final class ba implements Runnable {
    private /* synthetic */ TIMRefreshListener a;

    ba(IMCoreNotify iMCoreNotify, TIMRefreshListener tIMRefreshListener) {
        this.a = tIMRefreshListener;
    }

    public final void run() {
        this.a.onRefresh();
    }
}
