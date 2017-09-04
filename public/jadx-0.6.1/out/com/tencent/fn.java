package com.tencent;

final class fn extends ae {
    fn(TIMGroupManager tIMGroupManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMGroupManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(TIMGroupMemberSuccV2 tIMGroupMemberSuccV2) {
        this.a.onSuccess(tIMGroupMemberSuccV2);
    }
}
