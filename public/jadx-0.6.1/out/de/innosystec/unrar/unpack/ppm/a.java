package de.innosystec.unrar.unpack.ppm;

import de.innosystec.unrar.b.b;

/* compiled from: FreqData */
public class a extends d {
    public a(byte[] bArr) {
        super(bArr);
    }

    public a a(byte[] bArr) {
        this.c = bArr;
        this.d = 0;
        return this;
    }

    public int a() {
        return b.b(this.c, this.d) & 65535;
    }

    public void a(int i) {
        b.a(this.c, this.d, (short) i);
    }

    public void b(int i) {
        b.b(this.c, this.d, i);
    }

    public int b() {
        return b.c(this.c, this.d + 2);
    }

    public void a(i iVar) {
        a_(iVar.e());
    }

    public void a_(int i) {
        b.c(this.c, this.d + 2, i);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FreqData[");
        stringBuilder.append("\n  pos=");
        stringBuilder.append(this.d);
        stringBuilder.append("\n  size=");
        stringBuilder.append(6);
        stringBuilder.append("\n  summFreq=");
        stringBuilder.append(a());
        stringBuilder.append("\n  stats=");
        stringBuilder.append(b());
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
