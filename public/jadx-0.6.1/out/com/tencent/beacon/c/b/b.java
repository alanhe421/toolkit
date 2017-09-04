package com.tencent.beacon.c.b;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.c;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class b extends c implements Cloneable {
    private static ArrayList<a> b;
    public ArrayList<a> a = null;

    public final void a(com.tencent.beacon.f.b bVar) {
        bVar.a(this.a, 0);
    }

    public final void a(a aVar) {
        if (b == null) {
            b = new ArrayList();
            b.add(new a());
        }
        this.a = (ArrayList) aVar.a(b, 0, true);
    }
}
