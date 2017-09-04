package com.tencent.android.tpush.stat.b;

import android.content.Context;
import com.tencent.android.tpush.stat.a.h;

/* compiled from: ProGuard */
public class f extends g {
    public f(Context context) {
        super(context);
    }

    protected boolean a() {
        return h.a(this.a, "android.permission.WRITE_SETTINGS");
    }

    protected String b() {
        String a;
        synchronized (this) {
            a = com.tencent.android.tpush.service.channel.c.f.a(this.a).a(f());
        }
        return a;
    }

    protected void a(String str) {
        synchronized (this) {
            com.tencent.android.tpush.service.channel.c.f.a(this.a).a(f(), str);
        }
    }
}
