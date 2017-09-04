package com.tencent.android.tpush.stat.b;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ProGuard */
class h {
    private static h b = null;
    private Map a;

    private h(Context context) {
        this.a = null;
        this.a = new HashMap(3);
        this.a.put(Integer.valueOf(1), new f(context));
        this.a.put(Integer.valueOf(2), new b(context));
        this.a.put(Integer.valueOf(4), new e(context));
    }

    static synchronized h a(Context context) {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h(context);
            }
            hVar = b;
        }
        return hVar;
    }

    d a() {
        return a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4)})));
    }

    d a(List list) {
        if (list != null && list.size() >= 0) {
            for (Integer num : list) {
                g gVar = (g) this.a.get(num);
                if (gVar != null) {
                    d e = gVar.e();
                    if (e != null && e.a()) {
                        return e;
                    }
                }
            }
        }
        return new d();
    }

    void a(d dVar) {
        for (Entry value : this.a.entrySet()) {
            ((g) value.getValue()).a(dVar);
        }
    }
}
