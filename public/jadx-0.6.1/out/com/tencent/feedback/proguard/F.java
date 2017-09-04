package com.tencent.feedback.proguard;

import com.tencent.feedback.eup.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RQDSRC */
public final class f extends j {
    private static byte[] k = null;
    private static Map<String, String> l = null;
    private static /* synthetic */ boolean m;
    public short a = (short) 0;
    public int b = 0;
    public String c = null;
    public String d = null;
    public byte[] e;
    private byte f = (byte) 0;
    private int g = 0;
    private int h = 0;
    private Map<String, String> i;
    private Map<String, String> j;

    static {
        boolean z;
        if (f.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        m = z;
    }

    public final boolean equals(Object obj) {
        f fVar = (f) obj;
        if (k.a(1, fVar.a) && k.a(1, fVar.f) && k.a(1, fVar.g) && k.a(1, fVar.b) && k.a(Integer.valueOf(1), fVar.c) && k.a(Integer.valueOf(1), fVar.d) && k.a(Integer.valueOf(1), fVar.e) && k.a(1, fVar.h) && k.a(Integer.valueOf(1), fVar.i) && k.a(Integer.valueOf(1), fVar.j)) {
            return true;
        }
        return false;
    }

    public final Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!m) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public final void a(i iVar) {
        iVar.a(this.a, 1);
        iVar.a(this.f, 2);
        iVar.a(this.g, 3);
        iVar.a(this.b, 4);
        iVar.a(this.c, 5);
        iVar.a(this.d, 6);
        iVar.a(this.e, 7);
        iVar.a(this.h, 8);
        iVar.a(this.i, 9);
        iVar.a(this.j, 10);
    }

    public final void a(h hVar) {
        try {
            Map hashMap;
            this.a = hVar.a(this.a, 1, true);
            this.f = hVar.a(this.f, 2, true);
            this.g = hVar.a(this.g, 3, true);
            this.b = hVar.a(this.b, 4, true);
            this.c = hVar.b(5, true);
            this.d = hVar.b(6, true);
            if (k == null) {
                k = new byte[]{(byte) 0};
            }
            byte[] bArr = k;
            this.e = hVar.c(7, true);
            this.h = hVar.a(this.h, 8, true);
            if (l == null) {
                hashMap = new HashMap();
                l = hashMap;
                hashMap.put("", "");
            }
            this.i = (Map) hVar.a(l, 9, true);
            if (l == null) {
                hashMap = new HashMap();
                l = hashMap;
                hashMap.put("", "");
            }
            this.j = (Map) hVar.a(l, 10, true);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + e.a(this.e));
            throw new RuntimeException(e);
        }
    }

    public final void a(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "iVersion");
        bVar.a(this.f, "cPacketType");
        bVar.a(this.g, "iMessageType");
        bVar.a(this.b, "iRequestId");
        bVar.a(this.c, "sServantName");
        bVar.a(this.d, "sFuncName");
        bVar.a(this.e, "sBuffer");
        bVar.a(this.h, "iTimeout");
        bVar.a(this.i, "context");
        bVar.a(this.j, "status");
    }
}
