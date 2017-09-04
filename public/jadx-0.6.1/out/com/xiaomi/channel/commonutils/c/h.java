package com.xiaomi.channel.commonutils.c;

import com.xiaomi.channel.commonutils.c.g.a;

class h extends g$b {
    final /* synthetic */ String b;
    final /* synthetic */ g c;

    h(g gVar, a aVar, String str) {
        this.c = gVar;
        this.b = str;
        super(aVar);
    }

    void a() {
        super.a();
    }

    void b() {
        g.a(this.c).edit().putLong(this.b, System.currentTimeMillis()).commit();
    }
}
