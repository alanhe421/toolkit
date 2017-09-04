package com.tencent.feedback.proguard;

import java.util.ArrayList;

/* compiled from: RQDSRC */
public final class J extends j implements Cloneable {
    private static ArrayList<String> c;
    private String a = "";
    private ArrayList<String> b = null;

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        if (this.b != null) {
            iVar.a(this.b, 1);
        }
    }

    public final void a(h hVar) {
        this.a = hVar.b(0, true);
        if (c == null) {
            c = new ArrayList();
            c.add("");
        }
        this.b = (ArrayList) hVar.a(c, 1, false);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
