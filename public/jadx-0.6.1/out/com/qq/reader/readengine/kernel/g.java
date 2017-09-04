package com.qq.reader.readengine.kernel;

/* compiled from: QTextPosition */
public class g implements Comparable<g> {
    private int a;
    private long b;
    private long c;
    private int d = 0;
    private boolean e;

    public /* synthetic */ int compareTo(Object obj) {
        return b((g) obj);
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return (int) ((this.c >> 32) & -1);
    }

    public int c() {
        return (int) ((this.c >> 8) & 16777215);
    }

    public int d() {
        return (int) (this.c & 255);
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(int i, long j) {
        this.a = i;
        this.b = j;
        this.d = 1;
    }

    public long e() {
        return this.c;
    }

    public int f() {
        return this.a;
    }

    public long g() {
        return this.b;
    }

    public int hashCode() {
        return (((((this.a ^ (this.a >>> 32)) + 31) * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + ((int) (this.c ^ (this.c >>> 32)));
    }

    public void a(g gVar) {
        this.a = gVar.f();
        this.b = gVar.g();
        this.c = gVar.e();
        this.d = gVar.a();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.d != gVar.d) {
            return false;
        }
        if (this.d == 1) {
            if (gVar.a == this.a && gVar.b == this.b) {
                return true;
            }
            return false;
        } else if (gVar.c != this.c) {
            return false;
        } else {
            return true;
        }
    }

    public int b(g gVar) {
        long g;
        if (this.d == 1) {
            int f = this.a - gVar.f();
            if (f != 0) {
                return f;
            }
            g = this.b - gVar.g();
            if (g > 0) {
                return 1;
            }
            if (g == 0) {
                return 0;
            }
            return -1;
        }
        g = this.c - gVar.e();
        if (g > 0) {
            return 1;
        }
        if (g == 0) {
            return 0;
        }
        return -1;
    }

    public boolean h() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }
}
