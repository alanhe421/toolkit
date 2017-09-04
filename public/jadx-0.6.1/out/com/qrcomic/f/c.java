package com.qrcomic.f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: PoolingByteArrayOutputStream */
public class c extends ByteArrayOutputStream {
    private final a a;

    public c(a aVar, int i) {
        this.a = aVar;
        this.buf = this.a.a(Math.max(i, 256));
    }

    public void close() throws IOException {
        this.a.a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.a.a(this.buf);
    }

    private void a(int i) {
        if (this.count + i > this.buf.length) {
            Object a = this.a.a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.a.a(this.buf);
            this.buf = a;
        }
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        a(i2);
        super.write(bArr, i, i2);
    }

    public synchronized void write(int i) {
        a(1);
        super.write(i);
    }

    public byte[] a() {
        return this.buf;
    }
}
