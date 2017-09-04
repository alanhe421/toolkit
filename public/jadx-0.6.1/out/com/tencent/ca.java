package com.tencent;

import com.tencent.TIMConversation.ac;

final class ca implements Runnable {
    private /* synthetic */ ac a;

    ca(ac acVar) {
        this.a = acVar;
    }

    public final void run() {
        this.a.a.onSuccess(this.a.b);
    }
}
