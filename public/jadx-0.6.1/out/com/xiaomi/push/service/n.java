package com.xiaomi.push.service;

import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.xiaomi.push.service.am.b;
import com.xiaomi.push.service.am.c;

class n implements am$b$a {
    final /* synthetic */ b a;

    n(b bVar) {
        this.a = bVar;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.b) {
            b.b(this.a).a(b.a(this.a), BuglyBroadcastRecevier.UPLOADLIMITED);
        } else {
            b.b(this.a).b(b.a(this.a));
        }
    }
}
