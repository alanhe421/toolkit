package com.tencent;

final class fq extends ai {
    fq(TIMGroupManager tIMGroupManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMGroupManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMGroupPendencyListGetSucc tIMGroupPendencyListGetSucc) {
        this.a.onSuccess(tIMGroupPendencyListGetSucc);
    }
}
