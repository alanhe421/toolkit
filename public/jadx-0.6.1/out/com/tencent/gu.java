package com.tencent;

final class gu extends aa {
    gu(TIMGroupSystemElem tIMGroupSystemElem, TIMCallBack tIMCallBack) {
        super(tIMGroupSystemElem, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
