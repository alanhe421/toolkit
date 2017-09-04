package com.xiaomi.channel.commonutils.c;

import com.xiaomi.channel.commonutils.c.i.b;

class k implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ i b;

    k(i iVar, b bVar) {
        this.b = iVar;
        this.a = bVar;
    }

    public void run() {
        this.b.a(this.a);
    }
}
