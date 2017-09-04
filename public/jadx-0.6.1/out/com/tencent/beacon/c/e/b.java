package com.tencent.beacon.c.e;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class b extends c {
    public String a = "";
    public boolean b = true;
    public boolean c = true;
    private int d = 0;

    public final void a(com.tencent.beacon.f.b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.d, 1);
        bVar.a(this.b, 2);
        bVar.a(this.c, 3);
    }

    public final void a(a aVar) {
        this.a = aVar.b(0, true);
        this.d = aVar.a(this.d, 1, false);
        boolean z = this.b;
        this.b = aVar.a(2, false);
        z = this.c;
        this.c = aVar.a(3, false);
    }
}
