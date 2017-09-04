package com.tencent.feedback.proguard;

/* compiled from: RQDSRC */
public final class K extends j implements Cloneable {
    private static byte[] d;
    public byte a = (byte) 0;
    public String b = "";
    public byte[] c = null;

    public K(byte b, String str, byte[] bArr) {
        this.a = b;
        this.b = str;
        this.c = bArr;
    }

    public final void a(i iVar) {
        iVar.a(this.a, 0);
        iVar.a(this.b, 1);
        if (this.c != null) {
            iVar.a(this.c, 2);
        }
    }

    public final void a(h hVar) {
        byte[] bArr;
        this.a = hVar.a(this.a, 0, true);
        this.b = hVar.b(1, true);
        if (d == null) {
            bArr = new byte[1];
            d = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = d;
        this.c = hVar.c(2, false);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
