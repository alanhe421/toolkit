package com.xiaomi.push.a;

import com.xiaomi.channel.commonutils.c.i.b;

class d extends b {
    b a;
    final /* synthetic */ b b;

    d(b bVar) {
        this.b = bVar;
    }

    public void b() {
        b$b com_xiaomi_push_a_b_b = (b$b) b.b(this.b).peek();
        if (com_xiaomi_push_a_b_b != null && com_xiaomi_push_a_b_b.d()) {
            this.a = (b) b.b(this.b).remove();
            this.a.b();
        }
    }

    public void c() {
        if (this.a != null) {
            this.a.c();
        }
    }
}
