package de.innosystec.unrar.unpack;

import de.innosystec.unrar.b.d;
import de.innosystec.unrar.c;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.g;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ComprDataIO */
public class a {
    private final de.innosystec.unrar.a a;
    private long b;
    private boolean c;
    private boolean d;
    private InputStream e;
    private OutputStream f;
    private g g;
    private boolean h;
    private boolean i;
    private boolean j;
    private long k;
    private long l;
    private long m;
    private long n;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;
    private int u;
    private int v;
    private int w;
    private char x;

    public a(de.innosystec.unrar.a aVar) {
        this.a = aVar;
    }

    public void a(OutputStream outputStream) {
        this.f = outputStream;
        this.b = 0;
        this.c = false;
        this.d = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.u = 0;
        this.v = 0;
        this.k = 0;
        this.o = 0;
        this.n = 0;
        this.m = 0;
        this.l = 0;
        this.t = -1;
        this.s = -1;
        this.r = -1;
        this.w = -1;
        this.g = null;
        this.x = '\u0000';
        this.q = 0;
        this.p = 0;
    }

    public void a(g gVar) throws IOException {
        long h = ((long) gVar.h()) + gVar.e();
        this.b = gVar.t();
        this.e = new d(this.a.b(), h, this.b + h);
        this.g = gVar;
        this.n = 0;
        this.m = 0;
        this.t = -1;
    }

    public int a(byte[] bArr, int i, int i2) throws IOException, RarException {
        int i3 = 0;
        int i4 = 0;
        int i5 = i2;
        while (i5 > 0) {
            if (((long) i5) > this.b) {
                i4 = (int) this.b;
            } else {
                i4 = i5;
            }
            int read = this.e.read(bArr, i, i4);
            if (read < 0) {
                throw new EOFException();
            }
            if (this.g.v()) {
                this.t = (long) de.innosystec.unrar.a.a.a((int) this.t, bArr, i, read);
            }
            this.n += (long) read;
            i4 = i3 + read;
            i += read;
            i5 -= read;
            this.b -= (long) read;
            this.a.a(read);
            if (this.b != 0 || !this.g.v()) {
                i5 = read;
                break;
            } else if (c.a(this.a, this)) {
                i3 = i4;
                i4 = read;
            } else {
                this.j = true;
                return -1;
            }
        }
        i5 = i4;
        i4 = i3;
        if (i5 != -1) {
            return i4;
        }
        return i5;
    }

    public void b(byte[] bArr, int i, int i2) throws IOException {
        if (!this.c) {
            this.f.write(bArr, i, i2);
        }
        this.o += (long) i2;
        if (!this.d) {
            if (this.a.g()) {
                this.s = (long) de.innosystec.unrar.a.a.a((short) ((int) this.s), bArr, i2);
            } else {
                this.s = (long) de.innosystec.unrar.a.a.a((int) this.s, bArr, i, i2);
            }
        }
    }

    public long a() {
        return this.t;
    }

    public long b() {
        return this.s;
    }

    public void a(long j) {
        this.s = j;
    }

    public g c() {
        return this.g;
    }
}
