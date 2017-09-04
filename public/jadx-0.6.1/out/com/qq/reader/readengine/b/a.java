package com.qq.reader.readengine.b;

import com.qq.reader.readengine.fileparse.d;
import java.io.IOException;

/* compiled from: ByteReader */
public abstract class a {
    int a = 0;
    byte[] b;
    d c;
    int d;
    long e;
    String f;
    int g = -1;

    public abstract byte a() throws IOException;

    public abstract long a(long j);

    public abstract String a(long j, long j2, long j3);

    public abstract long b(long j);

    public abstract long c();

    public abstract void d();

    public a(d dVar, String str) {
        this.c = dVar;
        this.f = str;
    }

    public long b() {
        return this.c.t().getLength();
    }

    public int e() {
        return this.g;
    }
}
