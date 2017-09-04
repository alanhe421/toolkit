package com.tencent.android.tpush.service.a;

import android.content.Context;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
public class c {
    public static void a(Context context, d dVar) {
        if (context != null) {
            d a = a(context);
            String packageName = context.getPackageName();
            if (a.a != dVar.a || a.b != dVar.b) {
                try {
                    f.a(context, packageName + ".com.tencent.tpush.cache.ver", dVar.a);
                    f.a(context, packageName + ".com.tencent.tpush.cache.pri", dVar.b);
                    return;
                } catch (Throwable th) {
                    a.c(Constants.ServiceLogTag, "setSetting", th);
                    return;
                }
            }
            return;
        }
        a.h(Constants.ServiceLogTag, ">> context is null");
    }

    public static d a(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            return new d(f.b(context, packageName + ".com.tencent.tpush.cache.ver", 0.0f), f.b(context, packageName + ".com.tencent.tpush.cache.pri", 0));
        }
        a.h(Constants.LogTag, ">>> get version and priority from Settings error");
        return new d(0.0f, 0);
    }

    public static d a(Context context, String str) {
        d dVar = null;
        if (context != null) {
            try {
                dVar = a(context.createPackageContext(str, 2));
            } catch (Throwable e) {
                a.c(Constants.LogTag, "Create package context exception:" + str, e);
            }
        }
        return dVar;
    }
}
