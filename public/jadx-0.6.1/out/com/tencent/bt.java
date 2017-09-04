package com.tencent;

final class bt extends ad {
    bt(TIMConversation tIMConversation, TIMCallBack tIMCallBack) {
        super(tIMConversation, tIMCallBack);
    }

    public final void a() {
        this.a.onSuccess();
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }
}
