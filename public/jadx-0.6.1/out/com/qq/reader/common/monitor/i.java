package com.qq.reader.common.monitor;

import android.content.Context;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.s;
import com.tencent.beacon.event.UserAction;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RDM */
public final class i {
    private static e a;

    public static void a(e eVar) {
        a = eVar;
    }

    public static void a(String str, Map<String, String> map, Context context) {
        Map map2;
        if (a == null || !a.a(str)) {
            Map<String, String> map3 = map;
        } else {
            if (map == null) {
                map = new HashMap();
            }
            map.put("statcode", String.valueOf(a.a()));
            map2 = map;
        }
        a(str, true, 0, 0, map2, false, false, context);
    }

    public static void a(String str, boolean z, long j, long j2, Map<String, String> map, Context context) {
        a(str, z, j, j2, map, false, false, context);
    }

    public static void a(String str, boolean z, long j, long j2, Map<String, String> map, boolean z2, boolean z3, Context context) {
        try {
            boolean a = s.a(context);
            if ((z2 && a) || !z2) {
                UserAction.onUserAction(str, z, j, j2, map, z3);
            }
        } catch (Throwable th) {
            c.e("RDM", "onUserAction error : " + th);
        }
    }
}
