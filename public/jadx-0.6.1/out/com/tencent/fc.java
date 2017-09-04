package com.tencent;

final class fc extends ab {
    fc(TIMGroupManager tIMGroupManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMGroupManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMGroupSearchSucc tIMGroupSearchSucc) {
        this.a.onSuccess(tIMGroupSearchSucc);
    }
}
