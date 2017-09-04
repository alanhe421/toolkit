package com.tencent.mid.a;

import com.tencent.mid.api.MidCallback;

class k implements MidCallback {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void onFail(int i, String str) {
        this.a.d.b("request new mid failed, errCode:" + i + ",msg:" + str);
    }

    public void onSuccess(Object obj) {
        this.a.d.b("request new mid success:" + obj);
    }
}
