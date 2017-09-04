package com.tencent;

import java.util.List;

final class fp extends aj<TIMGroupSelfInfo> {
    fp(TIMGroupManager tIMGroupManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMGroupManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMGroupSelfInfo> list) {
        this.a.onSuccess(list.get(0));
    }
}
