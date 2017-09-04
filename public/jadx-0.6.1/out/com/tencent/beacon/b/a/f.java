package com.tencent.beacon.b.a;

import com.tencent.beacon.e.a;
import java.io.Serializable;
import java.util.Locale;

/* compiled from: ProGuard */
public class f implements Serializable {
    public int a;
    public long b;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    private long j = -1;

    public f(int i, long j, long j2, long j3, long j4, long j5, long j6) {
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = j5;
        this.g = j6;
    }

    public String toString() {
        try {
            return String.format(Locale.US, "[tp:%d , st:%d ,counts:%d, wifi:%d , notWifi:%d , up:%d , dn:%d]", new Object[]{Integer.valueOf(this.a), Long.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.d), Long.valueOf(this.e), Long.valueOf(this.f), Long.valueOf(this.g)});
        } catch (Throwable th) {
            a.a(th);
            return null;
        }
    }

    public final synchronized long a() {
        return this.j;
    }

    public final synchronized void a(long j) {
        this.j = j;
    }
}
