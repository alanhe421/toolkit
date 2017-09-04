package com.tencent.beacon.f;

import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public final class e extends c {
    private static byte[] k = null;
    private static Map<String, String> l = null;
    public short a = (short) 3;
    public int b = 0;
    public String c = null;
    public String d = null;
    public byte[] e;
    private byte f = (byte) 0;
    private int g = 0;
    private int h = 0;
    private Map<String, String> i;
    private Map<String, String> j;

    public final void a(b bVar) {
        bVar.a(this.a, 1);
        bVar.a(this.f, 2);
        bVar.a(this.g, 3);
        bVar.a(this.b, 4);
        bVar.a(this.c, 5);
        bVar.a(this.d, 6);
        bVar.a(this.e, 7);
        bVar.a(this.h, 8);
        bVar.a(this.i, 9);
        bVar.a(this.j, 10);
    }

    public final void a(a aVar) {
        try {
            Map hashMap;
            this.a = aVar.a(this.a, 1, true);
            this.f = aVar.a(this.f, 2, true);
            this.g = aVar.a(this.g, 3, true);
            this.b = aVar.a(this.b, 4, true);
            this.c = aVar.b(5, true);
            this.d = aVar.b(6, true);
            if (k == null) {
                k = new byte[1];
            }
            byte[] bArr = k;
            this.e = aVar.c(7, true);
            this.h = aVar.a(this.h, 8, true);
            if (l == null) {
                hashMap = new HashMap();
                l = hashMap;
                hashMap.put("", "");
            }
            this.i = (Map) aVar.a(l, 9, true);
            if (l == null) {
                hashMap = new HashMap();
                l = hashMap;
                hashMap.put("", "");
            }
            this.j = (Map) aVar.a(l, 10, true);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
