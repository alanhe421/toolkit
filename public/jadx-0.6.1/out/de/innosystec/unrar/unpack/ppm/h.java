package de.innosystec.unrar.unpack.ppm;

/* compiled from: SEE2Context */
public class h {
    private int a;
    private int b;
    private int c;

    public void a(int i) {
        this.b = 3;
        this.a = (i << this.b) & 65535;
        this.c = 4;
    }

    public int a() {
        int i = this.a >>> this.b;
        this.a -= i;
        return (i == 0 ? 1 : 0) + i;
    }

    public void b() {
        if (this.b < 7) {
            int i = this.c - 1;
            this.c = i;
            if (i == 0) {
                this.a += this.a;
                int i2 = this.b;
                this.b = i2 + 1;
                this.c = 3 << i2;
            }
        }
        this.a &= 65535;
        this.c &= 255;
        this.b &= 255;
    }

    public void b(int i) {
        this.b = i & 255;
    }

    public int c() {
        return this.a;
    }

    public void c(int i) {
        this.a = 65535 & i;
    }

    public void d(int i) {
        c(c() + i);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SEE2Context[");
        stringBuilder.append("\n  size=");
        stringBuilder.append(4);
        stringBuilder.append("\n  summ=");
        stringBuilder.append(this.a);
        stringBuilder.append("\n  shift=");
        stringBuilder.append(this.b);
        stringBuilder.append("\n  count=");
        stringBuilder.append(this.c);
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
