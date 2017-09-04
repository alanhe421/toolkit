package com.qq.reader.common.monitor;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PerformanceManager */
public class h {
    private static h a = null;
    private List<a> b = new ArrayList();
    private a c;

    /* compiled from: PerformanceManager */
    class a {
        final /* synthetic */ h a;
        private boolean b = false;
        private long c = 0;
        private long d = 0;
        private long e = 0;
        private long f = 0;
        private long g = 0;
        private long h = 0;
        private int i;

        a(h hVar) {
            this.a = hVar;
        }

        static /* synthetic */ long b(a aVar, long j) {
            long j2 = aVar.h + j;
            aVar.h = j2;
            return j2;
        }
    }

    private h() {
    }

    public static h a() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    public void a(int i) {
        this.c = new a(this);
        this.c.i = i;
        this.b.add(this.c);
    }

    public void b() {
        this.b.clear();
    }

    public int b(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            a aVar = (a) this.b.get(i3);
            if (aVar != null && aVar.i == i) {
                i2++;
            }
        }
        return i2;
    }

    public int c(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            a aVar = (a) this.b.get(i3);
            if (aVar != null && aVar.i == i && aVar.b) {
                i2++;
            }
        }
        return i2;
    }

    public long d(int i) {
        long j = 0;
        int i2 = 0;
        for (a aVar : this.b) {
            int i3;
            long j2;
            long j3;
            if (aVar.i != i || aVar.b) {
                j3 = j;
                i3 = i2;
                j2 = j3;
            } else {
                j3 = j + a(aVar);
                i3 = i2 + 1;
                j2 = j3;
            }
            i2 = i3;
            j = j2;
        }
        if (i2 == 0) {
            return 0;
        }
        return j / ((long) i2);
    }

    public long e(int i) {
        long j = 0;
        int i2 = 0;
        for (a aVar : this.b) {
            int i3;
            long j2;
            long j3;
            if (aVar.i != i || aVar.b) {
                j3 = j;
                i3 = i2;
                j2 = j3;
            } else {
                j3 = j + b(aVar);
                i3 = i2 + 1;
                j2 = j3;
            }
            i2 = i3;
            j = j2;
        }
        if (i2 == 0) {
            return 0;
        }
        return j / ((long) i2);
    }

    public long c() {
        long j = 0;
        for (a aVar : this.b) {
            long j2;
            if (aVar == null || aVar.b) {
                j2 = j;
            } else {
                j2 = j + aVar.h;
            }
            j = j2;
        }
        return j;
    }

    public void d() {
        this.c.g = System.currentTimeMillis();
    }

    public void e() {
        if (this.c.g != 0) {
            a.b(this.c, System.currentTimeMillis() - this.c.g);
            this.c.g = 0;
        }
    }

    public void f() {
        this.c.b = true;
    }

    public void g() {
        this.c.c = System.currentTimeMillis();
    }

    public void h() {
        this.c.d = System.currentTimeMillis();
    }

    public void i() {
        this.c.e = System.currentTimeMillis();
    }

    public void j() {
        this.c.f = System.currentTimeMillis();
    }

    private long a(a aVar) {
        return aVar.d - aVar.c;
    }

    private long b(a aVar) {
        return aVar.f - aVar.e;
    }
}
