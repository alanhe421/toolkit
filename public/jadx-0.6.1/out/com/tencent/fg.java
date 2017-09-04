package com.tencent;

final class fg extends ah {
    fg(TIMGroupManager tIMGroupManager, TIMCallBack tIMCallBack) {
        super(tIMGroupManager, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
