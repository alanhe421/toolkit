package com.tencent.beacon.c.e;

import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class a extends c {
    private static ArrayList<d> e;
    private static Map<String, String> f;
    public ArrayList<d> a = null;
    public int b = 0;
    public String c = "";
    public Map<String, String> d = null;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        if (this.d != null) {
            bVar.a(this.d, 3);
        }
    }

    public final void a(com.tencent.beacon.f.a aVar) {
        if (e == null) {
            e = new ArrayList();
            e.add(new d());
        }
        this.a = (ArrayList) aVar.a(e, 0, true);
        this.b = aVar.a(this.b, 1, true);
        this.c = aVar.b(2, true);
        if (f == null) {
            f = new HashMap();
            f.put("", "");
        }
        this.d = (Map) aVar.a(f, 3, false);
    }
}
