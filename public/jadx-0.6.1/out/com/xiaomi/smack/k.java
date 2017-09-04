package com.xiaomi.smack;

import com.xiaomi.network.f;

class k implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ h b;

    k(h hVar, String str) {
        this.b = hVar;
        this.a = str;
    }

    public void run() {
        f.a().a(this.a, true);
    }
}
