package com.tencent;

final class fk extends ah {
    fk(TIMGroupManager tIMGroupManager, TIMCallBack tIMCallBack) {
        super(tIMGroupManager, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
