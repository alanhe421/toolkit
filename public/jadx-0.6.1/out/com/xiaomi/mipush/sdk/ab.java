package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.q.a;
import com.xiaomi.xmpush.thrift.ah;
import com.xiaomi.xmpush.thrift.ao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ab {
    private static Map<String, a> a = new HashMap();
    private static ac b;

    public static void a(Context context, ah ahVar) {
        List list;
        String k = ahVar.k();
        if (ahVar.f() == 0) {
            a aVar = (a) a.get(k);
            if (aVar != null) {
                aVar.b(ahVar.h, ahVar.i);
                q.a(context).a(k, aVar);
            }
        }
        if (TextUtils.isEmpty(ahVar.h)) {
            list = null;
        } else {
            list = new ArrayList();
            list.add(ahVar.h);
        }
        MiPushCommandMessage a = f.a("register", list, ahVar.f, ahVar.g, null);
        if (b != null) {
            b.a(k, a);
        }
    }

    public static void a(Context context, ao aoVar) {
        MiPushCommandMessage a = f.a("unregister", null, aoVar.f, aoVar.g, null);
        String h = aoVar.h();
        if (b != null) {
            b.b(h, a);
        }
    }

    public static void a(Context context, String str, MiPushMessage miPushMessage) {
        if (!TextUtils.isEmpty(str) && b != null) {
            b.a(str, miPushMessage);
        }
    }
}
