package com.tencent.beacon.event;

import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class e {
    public String a;
    public boolean b;
    public long c;
    public long d;
    public Map<String, String> e;
    boolean f;

    public e(String str, boolean z, long j, long j2, Map<String, String> map, boolean z2) {
        this.a = str;
        this.b = z;
        this.c = j;
        this.d = j2;
        if (map != null) {
            this.e = new HashMap();
            this.e.putAll(map);
        }
        this.f = z2;
    }
}
