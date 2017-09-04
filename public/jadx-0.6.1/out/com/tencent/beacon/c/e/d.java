package com.tencent.beacon.c.e;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class d extends c {
    private static Map<String, String> h;
    private static ArrayList<String> i;
    private static e j;
    private static ArrayList<String> k;
    public byte a = (byte) 0;
    public byte b = (byte) 0;
    public String c = "";
    public Map<String, String> d = null;
    public ArrayList<String> e = null;
    public e f = null;
    public ArrayList<String> g = null;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
        if (this.e != null) {
            bVar.a(this.e, 4);
        }
        if (this.f != null) {
            bVar.a(this.f);
        }
        if (this.g != null) {
            bVar.a(this.g, 6);
        }
    }

    public final void a(a aVar) {
        this.a = aVar.a(this.a, 0, true);
        this.b = aVar.a(this.b, 1, true);
        this.c = aVar.b(2, true);
        if (h == null) {
            h = new HashMap();
            h.put("", "");
        }
        this.d = (Map) aVar.a(h, 3, true);
        if (i == null) {
            i = new ArrayList();
            i.add("");
        }
        this.e = (ArrayList) aVar.a(i, 4, false);
        if (j == null) {
            j = new e();
        }
        this.f = (e) aVar.a(j, 5, false);
        if (k == null) {
            k = new ArrayList();
            k.add("");
        }
        this.g = (ArrayList) aVar.a(k, 6, false);
    }
}
