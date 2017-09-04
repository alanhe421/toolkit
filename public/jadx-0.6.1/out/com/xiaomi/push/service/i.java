package com.xiaomi.push.service;

import com.xiaomi.d.h;
import java.util.List;

final class i implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;

    i(List list, boolean z) {
        this.a = list;
        this.b = z;
    }

    public void run() {
        boolean a = h.b("www.baidu.com:80");
        boolean z = a;
        for (String a2 : this.a) {
            a = z || h.b(a2);
            if (a && !this.b) {
                break;
            }
            z = a;
        }
        a = z;
        h.a(a ? 1 : 2);
    }
}
