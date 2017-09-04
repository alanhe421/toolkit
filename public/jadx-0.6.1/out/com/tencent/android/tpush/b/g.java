package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.m;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class g {
    static ArrayList a;
    private static volatile g b = null;
    private Context c = null;

    public static g a(Context context) {
        if (b == null) {
            synchronized (g.class) {
                if (b == null) {
                    b = new g();
                    b.c = context.getApplicationContext();
                    m.c(b.c);
                }
            }
        }
        return b;
    }

    public void a(Intent intent) {
        com.tencent.android.tpush.common.g.a().a(new h(this, this.c, intent, null));
    }

    protected static synchronized boolean a(Long l) {
        boolean z = false;
        synchronized (g.class) {
            try {
                if (a == null) {
                    a = new ArrayList();
                }
                if (!a.contains(l)) {
                    a.add(l);
                    if (a.size() > 200) {
                        a.remove(0);
                    }
                    z = true;
                }
            } catch (Throwable th) {
                a.c("PushMessageHandler", "addCachedmsgID", th);
            }
        }
        return z;
    }
}
