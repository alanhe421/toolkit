package com.tencent.android.tpush.service.channel.security;

/* compiled from: ProGuard */
public class a {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());

    public static byte[] a(String str, int i) {
        return a(str.getBytes(), i);
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        c cVar = new c(i3, new byte[((i2 * 3) / 4)]);
        if (!cVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (cVar.b == cVar.a.length) {
            return cVar.a;
        } else {
            byte[] bArr2 = new byte[cVar.b];
            System.arraycopy(cVar.a, 0, bArr2, 0, cVar.b);
            return bArr2;
        }
    }

    private a() {
    }
}
