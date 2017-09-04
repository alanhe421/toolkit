package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* compiled from: GzipSource */
public final class h implements p {
    private int a = 0;
    private final e b;
    private final Inflater c;
    private final i d;
    private final CRC32 e = new CRC32();

    public h(p pVar) {
        if (pVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.c = new Inflater(true);
        this.b = j.a(pVar);
        this.d = new i(this.b, this.c);
    }

    public long a(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0;
        } else {
            if (this.a == 0) {
                b();
                this.a = 1;
            }
            if (this.a == 1) {
                long j2 = cVar.b;
                long a = this.d.a(cVar, j);
                if (a != -1) {
                    a(cVar, j2, a);
                    return a;
                }
                this.a = 2;
            }
            if (this.a == 2) {
                c();
                this.a = 3;
                if (!this.b.e()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void b() throws IOException {
        Object obj;
        long a;
        this.b.a(10);
        byte b = this.b.c().b(3);
        if (((b >> 1) & 1) == 1) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a(this.b.c(), 0, 10);
        }
        a("ID1ID2", 8075, this.b.i());
        this.b.g(8);
        if (((b >> 2) & 1) == 1) {
            this.b.a(2);
            if (obj != null) {
                a(this.b.c(), 0, 2);
            }
            short k = this.b.c().k();
            this.b.a((long) k);
            if (obj != null) {
                a(this.b.c(), 0, (long) k);
            }
            this.b.g((long) k);
        }
        if (((b >> 3) & 1) == 1) {
            a = this.b.a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                a(this.b.c(), 0, 1 + a);
            }
            this.b.g(1 + a);
        }
        if (((b >> 4) & 1) == 1) {
            a = this.b.a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (obj != null) {
                a(this.b.c(), 0, 1 + a);
            }
            this.b.g(1 + a);
        }
        if (obj != null) {
            a("FHCRC", this.b.k(), (short) ((int) this.e.getValue()));
            this.e.reset();
        }
    }

    private void c() throws IOException {
        a("CRC", this.b.l(), (int) this.e.getValue());
        a("ISIZE", this.b.l(), (int) this.c.getBytesWritten());
    }

    public q a() {
        return this.b.a();
    }

    public void close() throws IOException {
        this.d.close();
    }

    private void a(c cVar, long j, long j2) {
        m mVar = cVar.a;
        while (j >= ((long) (mVar.c - mVar.b))) {
            j -= (long) (mVar.c - mVar.b);
            mVar = mVar.f;
        }
        while (j2 > 0) {
            int i = (int) (((long) mVar.b) + j);
            int min = (int) Math.min((long) (mVar.c - i), j2);
            this.e.update(mVar.a, i, min);
            j2 -= (long) min;
            mVar = mVar.f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
