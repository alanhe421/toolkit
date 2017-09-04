package com.tencent;

final class gs extends aa {
    gs(TIMGroupPendencyItem tIMGroupPendencyItem, TIMCallBack tIMCallBack) {
        super(tIMGroupPendencyItem, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
