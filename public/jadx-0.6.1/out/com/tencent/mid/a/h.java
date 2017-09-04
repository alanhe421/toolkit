package com.tencent.mid.a;

import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;

final class h implements MidCallback {
    final /* synthetic */ MidCallback a;

    h(MidCallback midCallback) {
        this.a = midCallback;
    }

    public void onFail(int i, String str) {
        g.a.f("failed to get mid, errorcode:" + i + " ,msg:" + str);
        this.a.onFail(i, str);
    }

    public void onSuccess(Object obj) {
        if (obj != null) {
            MidEntity parse = MidEntity.parse(obj.toString());
            g.a.h("success to get mid:" + parse.getMid());
            this.a.onSuccess(parse.getMid());
        }
    }
}
