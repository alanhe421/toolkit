package com.xiaomi.mipush.sdk;

import com.xiaomi.xmpush.thrift.e;

class ad implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ e$a$a b;

    ad(e$a$a com_xiaomi_mipush_sdk_e_a_a, e eVar) {
        this.b = com_xiaomi_mipush_sdk_e_a_a;
        this.a = eVar;
    }

    public void run() {
        this.b.a.add(this.a);
        this.b.a();
    }
}
