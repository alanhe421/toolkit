package com.tencent;

final class ex extends ah {
    ex(TIMGroupManager tIMGroupManager, TIMCallBack tIMCallBack) {
        super(tIMGroupManager, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
