package com.tencent.beacon.runinfo;

import android.content.Context;
import com.tencent.beacon.b.f;
import com.tencent.beacon.event.o;
import com.tencent.beacon.net.a;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class b implements Runnable {
    private Context a;

    public b(Context context) {
        this.a = context;
    }

    public final void run() {
        a f = a.f(this.a);
        if (f != null) {
            Map hashMap = new HashMap();
            f.a(this.a);
            hashMap.put("A33", f.l(this.a));
            hashMap.put("A43", f.c());
            hashMap.put("A44", f.d());
            hashMap.put("A41", f.a());
            hashMap.put("A42", f.b());
            o.a("rqd_useInfoEvent", true, 0, hashMap);
            Context context = this.a;
            if (context != null) {
                com.tencent.beacon.b.a.a.a(context, new int[]{8}, Long.MAX_VALUE);
            }
            com.tencent.beacon.e.a.e("%s %d %d", new Object[]{"rqd_useInfoEvent", Long.valueOf(f.a()), Long.valueOf(f.b())});
        }
    }
}
