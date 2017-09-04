package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.i.b;
import com.xiaomi.network.h;
import com.xiaomi.push.b.a.a;

class w extends b {
    boolean a = false;
    final /* synthetic */ v b;

    w(v vVar) {
        this.b = vVar;
    }

    public void b() {
        try {
            a b = a.b(Base64.decode(h.a(j.a(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), 10));
            if (b != null) {
                v.a(this.b, b);
                this.a = true;
                v.a(this.b);
            }
        } catch (Exception e) {
            c.a("fetch config failure: " + e.getMessage());
        }
    }

    public void c() {
        v.a(this.b, null);
        if (this.a) {
            synchronized (this.b) {
            }
            for (v.a a : (v.a[]) v.b(this.b).toArray(new v.a[v.b(this.b).size()])) {
                a.a(v.c(this.b));
            }
        }
    }
}
