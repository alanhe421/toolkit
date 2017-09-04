package de.innosystec.unrar.unpack.ppm;

import de.innosystec.unrar.b.b;

/* compiled from: State */
public class i extends d {
    public i(byte[] bArr) {
        super(bArr);
    }

    public i a(byte[] bArr) {
        this.c = bArr;
        this.d = 0;
        return this;
    }

    public int a() {
        return this.c[this.d] & 255;
    }

    public void a(int i) {
        this.c[this.d] = (byte) i;
    }

    public int b() {
        return this.c[this.d + 1] & 255;
    }

    public void b(int i) {
        this.c[this.d + 1] = (byte) i;
    }

    public void d(int i) {
        byte[] bArr = this.c;
        int i2 = this.d + 1;
        bArr[i2] = (byte) (bArr[i2] + i);
    }

    public int c() {
        return b.c(this.c, this.d + 2);
    }

    public void a(c cVar) {
        e(cVar.e());
    }

    public void e(int i) {
        b.c(this.c, this.d + 2, i);
    }

    public void a(j jVar) {
        a(jVar.a());
        b(jVar.b());
        e(jVar.c());
    }

    public void a(i iVar) {
        System.arraycopy(iVar.c, iVar.d, this.c, this.d, 6);
    }

    public i d() {
        c(this.d - 6);
        return this;
    }

    public i f() {
        c(this.d + 6);
        return this;
    }

    public static void a(i iVar, i iVar2) {
        byte[] bArr = iVar.c;
        byte[] bArr2 = iVar2.c;
        int i = 0;
        int i2 = iVar.d;
        int i3 = iVar2.d;
        while (i < 6) {
            byte b = bArr[i2];
            bArr[i2] = bArr2[i3];
            bArr2[i3] = b;
            i++;
            i2++;
            i3++;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("State[");
        stringBuilder.append("\n  pos=");
        stringBuilder.append(this.d);
        stringBuilder.append("\n  size=");
        stringBuilder.append(6);
        stringBuilder.append("\n  symbol=");
        stringBuilder.append(a());
        stringBuilder.append("\n  freq=");
        stringBuilder.append(b());
        stringBuilder.append("\n  successor=");
        stringBuilder.append(c());
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
