package com.tencent.feedback.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: RQDSRC */
public final class R extends j implements Cloneable {
    private static Map<String, String> g = new HashMap();
    public long a = 0;
    public byte b = (byte) 0;
    public String c = "";
    public String d = "";
    public Map<String, String> e = null;
    private String f = "";

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        if (this.c != null) {
            iVar.a(this.c, 2);
        }
        if (this.d != null) {
            iVar.a(this.d, 3);
        }
        if (this.f != null) {
            iVar.a(this.f, 4);
        }
        if (this.e != null) {
            iVar.a(this.e, 5);
        }
    }

    static {
        g.put("", "");
    }

    public final void a(h hVar) {
        this.a = hVar.a(this.a, 0, true);
        this.b = hVar.a(this.b, 1, true);
        this.c = hVar.b(2, false);
        this.d = hVar.b(3, false);
        this.f = hVar.b(4, false);
        this.e = (Map) hVar.a(g, 5, false);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
