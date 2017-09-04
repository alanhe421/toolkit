package com.tencent.beacon.c.b;

import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class a extends c implements Cloneable {
    public String a = "";
    public String b = "";
    public String c = "";
    public boolean d = true;
    public long e = 0;
    public long f = 0;
    public String g = "";
    public long h = 0;
    public int i = 0;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
        bVar.a(this.e, 4);
        bVar.a(this.f, 5);
        bVar.a(this.g, 6);
        bVar.a(this.h, 7);
        bVar.a(this.i, 8);
    }

    public final void a(com.tencent.beacon.f.a aVar) {
        this.a = aVar.b(0, true);
        this.b = aVar.b(1, true);
        this.c = aVar.b(2, true);
        boolean z = this.d;
        this.d = aVar.a(3, true);
        this.e = aVar.a(this.e, 4, true);
        this.f = aVar.a(this.f, 5, true);
        this.g = aVar.b(6, true);
        this.h = aVar.a(this.h, 7, true);
        this.i = aVar.a(this.i, 8, false);
    }
}
