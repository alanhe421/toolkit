package org.apache.a.a;

/* compiled from: UnrecognizedExtraField */
public class d implements f {
    private j a;
    private byte[] b;
    private byte[] c;

    public void a(j jVar) {
        this.a = jVar;
    }

    public j a() {
        return this.a;
    }

    public void a(byte[] bArr) {
        this.b = bArr;
    }

    public j b() {
        return new j(this.b.length);
    }

    public byte[] d() {
        return this.b;
    }

    public j c() {
        if (this.c != null) {
            return new j(this.c.length);
        }
        return b();
    }

    public byte[] e() {
        if (this.c != null) {
            return this.c;
        }
        return d();
    }

    public void a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        a(bArr2);
    }
}
