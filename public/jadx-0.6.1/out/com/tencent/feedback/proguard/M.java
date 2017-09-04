package com.tencent.feedback.proguard;

import java.util.ArrayList;

/* compiled from: RQDSRC */
public final class M extends j implements Cloneable {
    private static ArrayList<L> b;
    public ArrayList<L> a = null;

    public final void a(i iVar) {
        iVar.a(this.a, 0);
    }

    public final void a(h hVar) {
        if (b == null) {
            b = new ArrayList();
            b.add(new L());
        }
        this.a = (ArrayList) hVar.a(b, 0, true);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
