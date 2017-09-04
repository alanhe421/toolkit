package com.tencent;

import java.util.ArrayList;

final class ax implements Runnable {
    private /* synthetic */ ArrayList a;
    private /* synthetic */ IMCoreNotify b;

    ax(IMCoreNotify iMCoreNotify, ArrayList arrayList) {
        this.b = iMCoreNotify;
        this.a = arrayList;
    }

    public final void run() {
        for (TIMMessageUpdateListener tIMMessageUpdateListener : TIMManager.getInstanceById(IMCoreNotify.a(this.b)).getMessageUpdateListeners()) {
            if (tIMMessageUpdateListener != null && tIMMessageUpdateListener.onMessagesUpdate(this.a)) {
                return;
            }
        }
    }
}
