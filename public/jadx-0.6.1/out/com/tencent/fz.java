package com.tencent;

import com.tencent.TIMGroupManager.CreateGroupCallBack;

final class fz implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ String b;
    private /* synthetic */ CreateGroupCallBack c;

    fz(CreateGroupCallBack createGroupCallBack, int i, String str) {
        this.c = createGroupCallBack;
        this.a = i;
        this.b = str;
    }

    public final void run() {
        this.c.onFail(this.a, this.b);
    }
}
