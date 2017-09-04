package com.xiaomi.channel.commonutils.c;

import com.xiaomi.channel.commonutils.c.g.a;

class g$b implements Runnable {
    a a;

    public g$b(a aVar) {
        this.a = aVar;
    }

    void a() {
    }

    void b() {
    }

    public void run() {
        a();
        this.a.run();
        b();
    }
}
