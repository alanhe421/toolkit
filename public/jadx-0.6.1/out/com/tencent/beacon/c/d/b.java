package com.tencent.beacon.c.d;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.c;

/* compiled from: ProGuard */
public final class b extends c implements Cloneable {
    private static byte[] n;
    public String a = "";
    public String b = "";
    public String c = "";
    public int d = 0;
    public long e = 0;
    public long f = 0;
    public String g = "";
    private long h = 0;
    private long i = 0;
    private long j = 0;
    private int k = 0;
    private int l = 0;
    private byte[] m = null;

    public final void a(com.tencent.beacon.f.b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
        bVar.a(this.e, 4);
        bVar.a(this.f, 5);
        bVar.a(this.h, 6);
        bVar.a(this.i, 7);
        bVar.a(this.j, 8);
        bVar.a(this.k, 9);
        bVar.a(this.l, 10);
        if (this.m != null) {
            bVar.a(this.m, 11);
        }
        if (this.g != null) {
            bVar.a(this.g, 12);
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
        this.h = aVar.a(this.h, 6, false);
        this.i = aVar.a(this.i, 7, false);
        this.j = aVar.a(this.j, 8, false);
        this.k = aVar.a(this.k, 9, false);
        this.l = aVar.a(this.l, 10, false);
        if (n == null) {
            bArr = new byte[1];
            n = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = n;
        this.m = aVar.c(11, false);
        this.g = aVar.b(12, false);
    }
}
