package com.tencent;

final class gv extends aa {
    gv(TIMGroupSystemElem tIMGroupSystemElem, TIMCallBack tIMCallBack) {
        super(tIMGroupSystemElem, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
