package com.tencent.mid.a;

import com.tencent.mid.api.MidCallback;
import com.tencent.mid.api.MidEntity;

final class i implements MidCallback {
    i() {
    }

    public void onFail(int i, String str) {
        g.a.f("failed to get mid, errorcode:" + i + " ,msg:" + str);
    }

    public void onSuccess(Object obj) {
        if (obj != null) {
            g.a.h("success to get mid:" + MidEntity.parse(obj.toString()).getMid());
        }
    }
}
