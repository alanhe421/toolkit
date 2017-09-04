package de.innosystec.unrar.unpack.ppm;

import de.innosystec.unrar.b.b;

/* compiled from: RarNode */
public class g extends d {
    private int a;

    public g(byte[] bArr) {
        super(bArr);
    }

    public int a() {
        if (this.c != null) {
            this.a = b.c(this.c, this.d);
        }
        return this.a;
    }

    public void a(g gVar) {
        a(gVar.e());
    }

    public void a(int i) {
        this.a = i;
        if (this.c != null) {
            b.c(this.c, this.d, i);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("State[");
        stringBuilder.append("\n  pos=");
        stringBuilder.append(this.d);
        stringBuilder.append("\n  size=");
        stringBuilder.append(4);
        stringBuilder.append("\n  next=");
        stringBuilder.append(a());
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
