package com.tencent.android.tpush.rpc;

import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.b.g;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
public class h extends b {
    public void a(String str, d dVar) {
        try {
            g.a(m.e()).a(Intent.parseUri(str, 0));
            dVar.a();
        } catch (Throwable th) {
            a.c(Constants.ServiceLogTag, "Show", th);
        }
    }

    public void a() {
        try {
            m.a(m.e());
        } catch (Throwable th) {
            a.c(Constants.ServiceLogTag, "startService", th);
        }
    }

    public void b() {
    }
}
