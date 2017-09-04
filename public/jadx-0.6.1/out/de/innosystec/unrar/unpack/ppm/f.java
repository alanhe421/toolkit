package de.innosystec.unrar.unpack.ppm;

import de.innosystec.unrar.b.b;

/* compiled from: RarMemBlock */
public class f extends d {
    private int a;
    private int b;
    private int f;
    private int g;

    public f(byte[] bArr) {
        super(bArr);
    }

    public void a(f fVar) {
        f fVar2 = new f(this.c);
        d(fVar.e());
        fVar2.c(d());
        a(fVar2.b());
        fVar2.b(this);
        fVar2.c(b());
        fVar2.c(this);
    }

    public void a() {
        f fVar = new f(this.c);
        fVar.c(d());
        fVar.a(b());
        fVar.c(b());
        fVar.d(d());
    }

    public int b() {
        if (this.c != null) {
            this.f = b.c(this.c, this.d + 4);
        }
        return this.f;
    }

    public void b(f fVar) {
        a(fVar.e());
    }

    public void a(int i) {
        this.f = i;
        if (this.c != null) {
            b.c(this.c, this.d + 4, i);
        }
    }

    public int c() {
        if (this.c != null) {
            this.b = b.b(this.c, this.d + 2) & 65535;
        }
        return this.b;
    }

    public void b(int i) {
        this.b = 65535 & i;
        if (this.c != null) {
            b.a(this.c, this.d + 2, (short) i);
        }
    }

    public int d() {
        if (this.c != null) {
            this.g = b.c(this.c, this.d + 8);
        }
        return this.g;
    }

    public void c(f fVar) {
        d(fVar.e());
    }

    public void d(int i) {
        this.g = i;
        if (this.c != null) {
            b.c(this.c, this.d + 8, i);
        }
    }

    public int f() {
        if (this.c != null) {
            this.a = b.b(this.c, this.d) & 65535;
        }
        return this.a;
    }

    public void e(int i) {
        this.a = i;
        if (this.c != null) {
            b.a(this.c, this.d, (short) i);
        }
    }
}
