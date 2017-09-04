package com.tencent;

import com.tencent.TIMGroupManager.CreateGroupCallBack;

final class fy implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ CreateGroupCallBack b;

    fy(CreateGroupCallBack createGroupCallBack, String str) {
        this.b = createGroupCallBack;
        this.a = str;
    }

    public final void run() {
        this.b.onDone(this.a);
    }
}
