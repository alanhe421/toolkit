package com.tencent.beacon.c.d;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class d extends c implements Cloneable {
    private static byte[] s;
    public String a = "";
    public String b = "";
    public String c = "";
    public long d = 0;
    public long e = 0;
    public long f = 0;
    public long g = 0;
    public long h = 0;
    public String i = "";
    public long j = 0;
    public String k = "";
    public String l = "";
    public int m = 0;
    public String n = "";
    private String o = "";
    private int p = 0;
    private int q = 0;
    private byte[] r = null;

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
        if (this.o != null) {
            bVar.a(this.o, 9);
        }
        bVar.a(this.j, 10);
        bVar.a(this.p, 11);
        bVar.a(this.q, 12);
        if (this.r != null) {
            bVar.a(this.r, 13);
        }
        if (this.k != null) {
            bVar.a(this.k, 14);
        }
        if (this.l != null) {
            bVar.a(this.l, 15);
        }
        bVar.a(this.m, 16);
        if (this.n != null) {
            bVar.a(this.n, 17);
        }
    }

    public final void a(a aVar) {
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
        this.o = aVar.b(9, false);
        this.j = aVar.a(this.j, 10, true);
        this.p = aVar.a(this.p, 11, false);
        this.q = aVar.a(this.q, 12, false);
        if (s == null) {
            bArr = new byte[1];
            s = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = s;
        this.r = aVar.c(13, false);
        this.k = aVar.b(14, false);
        this.l = aVar.b(15, false);
        this.m = aVar.a(this.m, 16, false);
        this.n = aVar.b(17, false);
    }
}
