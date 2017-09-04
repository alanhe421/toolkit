package com.tencent.beacon.upload;

import android.content.Context;
import com.tencent.beacon.b.d;
import com.tencent.beacon.c.a.b;
import com.tencent.beacon.net.a;

/* compiled from: ProGuard */
public final class c extends a {
    public c(Context context) {
        super(context, 0, 100);
    }

    public final void b(boolean z) {
    }

    public final b a() {
        try {
            b a = a.a(c(), d.m(), "".getBytes(), -1, -1);
            return a != null ? a : null;
        } catch (Throwable th) {
            com.tencent.beacon.e.a.a(th);
            com.tencent.beacon.e.a.d("encode2RequestPackage failed", new Object[0]);
            return null;
        }
    }
}
