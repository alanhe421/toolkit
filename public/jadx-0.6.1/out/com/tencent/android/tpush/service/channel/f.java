package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.service.ab;

/* compiled from: ProGuard */
class f implements ab {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public void a(String str) {
        if (str == null) {
            b.b++;
        } else {
            b.b = 0;
        }
    }
}
