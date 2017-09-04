package com.tencent;

import java.util.List;

final class bc implements Runnable {
    private /* synthetic */ TIMMessageReceiptListener a;
    private /* synthetic */ List b;

    bc(IMCoreNotify iMCoreNotify, TIMMessageReceiptListener tIMMessageReceiptListener, List list) {
        this.a = tIMMessageReceiptListener;
        this.b = list;
    }

    public final void run() {
        this.a.onRecvReceipt(this.b);
    }
}
