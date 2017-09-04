package com.tencent.beacon.c.d;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class c extends com.tencent.beacon.f.c implements Cloneable {
    private static ArrayList<b> d;
    private static ArrayList<a> e;
    private static ArrayList<d> f;
    public ArrayList<b> a = null;
    public ArrayList<a> b = null;
    public ArrayList<d> c = null;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        if (this.c != null) {
            bVar.a(this.c, 2);
        }
    }

    public final void a(a aVar) {
        if (d == null) {
            d = new ArrayList();
            d.add(new b());
        }
        this.a = (ArrayList) aVar.a(d, 0, true);
        if (e == null) {
            e = new ArrayList();
            e.add(new a());
        }
        this.b = (ArrayList) aVar.a(e, 1, true);
        if (f == null) {
            f = new ArrayList();
            f.add(new d());
        }
        this.c = (ArrayList) aVar.a(f, 2, false);
    }
}
