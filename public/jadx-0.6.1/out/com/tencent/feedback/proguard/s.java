package com.tencent.feedback.proguard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RQDSRC */
public final class S extends j implements Cloneable {
    private static ArrayList<R> f;
    private static Map<String, String> g;
    public byte a = (byte) 0;
    public String b = "";
    public ArrayList<R> c = null;
    public Map<String, String> d = null;
    private String e = "";

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        if (this.b != null) {
            iVar.a(this.b, 1);
        }
        if (this.e != null) {
            iVar.a(this.e, 2);
        }
        if (this.c != null) {
            iVar.a(this.c, 3);
        }
        if (this.d != null) {
            iVar.a(this.d, 4);
        }
    }

    public final void a(h hVar) {
        this.a = hVar.a(this.a, 0, true);
        this.b = hVar.b(1, false);
        this.e = hVar.b(2, false);
        if (f == null) {
            f = new ArrayList();
            f.add(new R());
        }
        this.c = (ArrayList) hVar.a(f, 3, false);
        if (g == null) {
            g = new HashMap();
            g.put("", "");
        }
        this.d = (Map) hVar.a(g, 4, false);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
