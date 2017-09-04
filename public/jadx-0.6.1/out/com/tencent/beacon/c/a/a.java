package com.tencent.beacon.c.a;

import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class a extends c implements Cloneable {
    private static Map<Integer, byte[]> b;
    public Map<Integer, byte[]> a = null;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
    }

    public final void a(com.tencent.beacon.f.a aVar) {
        if (b == null) {
            b = new HashMap();
            b.put(Integer.valueOf(0), new byte[]{null});
        }
        this.a = (Map) aVar.a(b, 0, true);
    }
}
