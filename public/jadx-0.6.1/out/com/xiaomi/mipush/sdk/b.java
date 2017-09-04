package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.b.a;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.a.e;
import com.xiaomi.push.a.f;

public class b {
    private static boolean a = false;
    private static a b = null;

    private static void a(Context context) {
        Object obj = b != null ? 1 : null;
        a fVar = new f(context);
        if (!a && b(context) && obj != null) {
            c.a(new e(b, fVar));
        } else if (!a && b(context)) {
            c.a(fVar);
        } else if (obj != null) {
            c.a(b);
        } else {
            c.a(new e(null, null));
        }
    }

    public static void a(Context context, a aVar) {
        b = aVar;
        a(context);
    }

    private static boolean b(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (Object equals : strArr) {
                if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
