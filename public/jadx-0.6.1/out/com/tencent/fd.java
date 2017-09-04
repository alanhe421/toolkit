package com.tencent;

import java.util.List;

final class fd extends aa {
    fd(TIMGroupManager tIMGroupManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMGroupManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMGroupDetailInfo> list) {
        this.a.onSuccess(list);
    }
}
