package de.innosystec.unrar.unpack.ppm;

import com.tencent.upload.log.trace.TracerConfig;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.unpack.b;
import java.io.IOException;

/* compiled from: RangeCoder */
public class e {
    private long a;
    private long b;
    private long c;
    private final a d = new a();
    private b e;

    /* compiled from: RangeCoder */
    public static class a {
        private long a;
        private long b;
        private long c;

        public long a() {
            return this.b;
        }

        public void a(long j) {
            this.b = 4294967295L & j;
        }

        public long b() {
            return this.a & 4294967295L;
        }

        public void b(long j) {
            this.a = 4294967295L & j;
        }

        public long c() {
            return this.c;
        }

        public void c(long j) {
            this.c = 4294967295L & j;
        }

        public void a(int i) {
            c(c() + ((long) i));
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SubRange[");
            stringBuilder.append("\n  lowCount=");
            stringBuilder.append(this.a);
            stringBuilder.append("\n  highCount=");
            stringBuilder.append(this.b);
            stringBuilder.append("\n  scale=");
            stringBuilder.append(this.c);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public a a() {
        return this.d;
    }

    public void a(b bVar) throws IOException, RarException {
        this.e = bVar;
        this.b = 0;
        this.a = 0;
        this.c = 4294967295L;
        for (int i = 0; i < 4; i++) {
            this.b = ((this.b << 8) | ((long) e())) & 4294967295L;
        }
    }

    public int b() {
        this.c = (this.c / this.d.c()) & 4294967295L;
        return (int) ((this.b - this.a) / this.c);
    }

    public long a(int i) {
        this.c >>>= i;
        return ((this.b - this.a) / this.c) & 4294967295L;
    }

    public void c() {
        this.a = (this.a + (this.c * this.d.b())) & 4294967295L;
        this.c = (this.c * (this.d.a() - this.d.b())) & 4294967295L;
    }

    private int e() throws IOException, RarException {
        return this.e.a();
    }

    public void d() throws IOException, RarException {
        Object obj = null;
        while (true) {
            if ((this.a ^ (this.a + this.c)) >= 16777216) {
                obj = this.c < TracerConfig.MEMORY_SIZE ? 1 : null;
                if (obj == null) {
                    return;
                }
            }
            if (obj != null) {
                this.c = ((-this.a) & 32767) & 4294967295L;
                obj = null;
            }
            this.b = ((this.b << 8) | ((long) e())) & 4294967295L;
            this.c = (this.c << 8) & 4294967295L;
            this.a = (this.a << 8) & 4294967295L;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RangeCoder[");
        stringBuilder.append("\n  low=");
        stringBuilder.append(this.a);
        stringBuilder.append("\n  code=");
        stringBuilder.append(this.b);
        stringBuilder.append("\n  range=");
        stringBuilder.append(this.c);
        stringBuilder.append("\n  subrange=");
        stringBuilder.append(this.d);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
