package com.tencent.mid.a;

import com.tencent.mid.api.MidCallback;

class l implements MidCallback {
    final /* synthetic */ j a;

    l(j jVar) {
        this.a = jVar;
    }

    public void onFail(int i, String str) {
        this.a.d.b("checkServer failed, errCode:" + i + ",msg:" + str);
    }

    public void onSuccess(Object obj) {
        this.a.d.b("checkServer success:" + obj);
    }
}
