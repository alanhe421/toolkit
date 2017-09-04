package com.tencent.beacon.c.e;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class e extends c {
    private static ArrayList<c> d;
    private static ArrayList<b> e;
    private static ArrayList<f> f;
    public ArrayList<c> a = null;
    public ArrayList<b> b = null;
    public ArrayList<f> c = null;

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
            d.add(new c());
        }
        this.a = (ArrayList) aVar.a(d, 0, true);
        if (e == null) {
            e = new ArrayList();
            e.add(new b());
        }
        this.b = (ArrayList) aVar.a(e, 1, true);
        if (f == null) {
            f = new ArrayList();
            f.add(new f());
        }
        this.c = (ArrayList) aVar.a(f, 2, false);
    }
}
