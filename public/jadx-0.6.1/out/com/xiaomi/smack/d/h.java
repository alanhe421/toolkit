package com.xiaomi.smack.d;

import android.content.Context;
import com.xiaomi.channel.commonutils.c.i.b;
import java.util.ArrayList;
import java.util.List;

final class h extends b {
    final /* synthetic */ Context a;

    h(Context context) {
        this.a = context;
    }

    public void b() {
        List arrayList;
        synchronized (g.a()) {
            arrayList = new ArrayList(g.b());
            g.b().clear();
        }
        g.a(this.a, arrayList);
    }
}
