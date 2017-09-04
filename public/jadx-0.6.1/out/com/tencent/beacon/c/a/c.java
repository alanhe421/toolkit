package com.tencent.beacon.c.a;

import com.tencent.beacon.f.a;
import com.tencent.beacon.f.b;

/* compiled from: ProGuard */
public final class c extends com.tencent.beacon.f.c {
    private static byte[] j;
    public byte a = (byte) 0;
    public int b = 0;
    public byte[] c = null;
    public String d = "";
    public long e = 0;
    public String f = "";
    private byte g = (byte) 0;
    private byte h = (byte) 0;
    private String i = "";

    public final void a(b bVar) {
        bVar.a(this.a, 0);
        bVar.a(this.b, 1);
        bVar.a(this.c, 2);
        bVar.a(this.d, 3);
        bVar.a(this.g, 4);
        bVar.a(this.h, 5);
        bVar.a(this.e, 6);
        if (this.f != null) {
            bVar.a(this.f, 7);
        }
        if (this.i != null) {
            bVar.a(this.i, 8);
        }
    }

    public final void a(a aVar) {
        byte[] bArr;
        this.a = aVar.a(this.a, 0, true);
        this.b = aVar.a(this.b, 1, true);
        if (j == null) {
            bArr = new byte[1];
            j = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = j;
        this.c = aVar.c(2, true);
        this.d = aVar.b(3, true);
        this.g = aVar.a(this.g, 4, true);
        this.h = aVar.a(this.h, 5, true);
        this.e = aVar.a(this.e, 6, true);
        this.f = aVar.b(7, false);
        this.i = aVar.b(8, false);
    }
}
