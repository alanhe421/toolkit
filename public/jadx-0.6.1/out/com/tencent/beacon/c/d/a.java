package com.tencent.beacon.c.d;

import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class a extends c implements Cloneable {
    private static byte[] p;
    public String a = "";
    public String b = "";
    public String c = "";
    public long d = 0;
    public long e = 0;
    public long f = 0;
    public long g = 0;
    public long h = 0;
    public String i = "";
    public String j = "";
    public long k = 0;
    public String l = "";
    private int m = 0;
    private int n = 0;
    private byte[] o = null;

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
        bVar.a(this.e, 4);
        bVar.a(this.f, 5);
        bVar.a(this.g, 6);
        bVar.a(this.h, 7);
        if (this.i != null) {
            bVar.a(this.i, 8);
        }
        if (this.j != null) {
            bVar.a(this.j, 9);
        }
        bVar.a(this.k, 10);
        bVar.a(this.m, 11);
        bVar.a(this.n, 12);
        if (this.o != null) {
            bVar.a(this.o, 13);
        }
        if (this.l != null) {
            bVar.a(this.l, 14);
        }
    }

    public final void a(com.tencent.beacon.f.a aVar) {
        byte[] bArr;
        this.a = aVar.b(0, true);
        this.b = aVar.b(1, true);
        this.c = aVar.b(2, true);
        this.d = aVar.a(this.d, 3, true);
        this.e = aVar.a(this.e, 4, true);
        this.f = aVar.a(this.f, 5, true);
        this.g = aVar.a(this.g, 6, true);
        this.h = aVar.a(this.h, 7, true);
        this.i = aVar.b(8, false);
        this.j = aVar.b(9, false);
        this.k = aVar.a(this.k, 10, true);
        this.m = aVar.a(this.m, 11, false);
        this.n = aVar.a(this.n, 12, false);
        if (p == null) {
            bArr = new byte[1];
            p = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = p;
        this.o = aVar.c(13, false);
        this.l = aVar.b(14, false);
    }
}
