package com.tencent.beacon.c.a;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class b extends c {
    private static byte[] m;
    public byte a = (byte) 0;
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public int f = 0;
    public byte[] g = null;
    public byte h = (byte) 0;
    public byte i = (byte) 0;
    public String j = "";
    public String k = "";
    public String l = "";

    public final void a(com.tencent.beacon.f.b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
        bVar.a(this.e, 4);
        bVar.a(this.f, 5);
        bVar.a(this.g, 6);
        bVar.a(this.h, 7);
        bVar.a(this.i, 8);
        if (this.j != null) {
            bVar.a(this.j, 9);
        }
        if (this.k != null) {
            bVar.a(this.k, 10);
        }
        if (this.l != null) {
            bVar.a(this.l, 11);
        }
    }

    public final void a(a aVar) {
        byte[] bArr;
        this.a = aVar.a(this.a, 0, true);
        this.b = aVar.b(1, true);
        this.c = aVar.b(2, true);
        this.d = aVar.b(3, true);
        this.e = aVar.b(4, true);
        this.f = aVar.a(this.f, 5, true);
        if (m == null) {
            bArr = new byte[1];
            m = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = m;
        this.g = aVar.c(6, true);
        this.h = aVar.a(this.h, 7, true);
        this.i = aVar.a(this.i, 8, true);
        this.j = aVar.b(9, false);
        this.k = aVar.b(10, false);
        this.l = aVar.b(11, false);
    }
}
