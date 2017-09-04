package com.tencent.beacon.c.e;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class f extends c {
    public String a = "";
    public int b = 0;
    public String c = "";
    public String d = "";

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
    }

    public final void a(a aVar) {
        this.a = aVar.b(0, true);
        this.b = aVar.a(this.b, 1, true);
        this.c = aVar.b(2, true);
        this.d = aVar.b(3, true);
    }
}
