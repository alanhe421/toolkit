package com.tencent.beacon.c.e;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;

/* compiled from: ProGuard */
public final class c extends com.tencent.beacon.f.c {
    public String a = "";
    public int b = 0;
    public boolean c = true;
    private int d = 0;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.d, 2);
        bVar.a(this.c, 3);
    }

    public final void a(a aVar) {
        this.a = aVar.b(0, true);
        this.b = aVar.a(this.b, 1, true);
        this.d = aVar.a(this.d, 2, false);
        boolean z = this.c;
        this.c = aVar.a(3, false);
    }
}
