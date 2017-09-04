package com.qq.reader.cservice.cloud.a;

import com.qq.reader.cservice.cloud.CloudActionEnum;
import com.qq.reader.cservice.cloud.a;

/* compiled from: CloudSyncAction */
public class g {
    private int a;
    private int b;
    protected CloudActionEnum c;
    protected long d;
    protected long e;
    protected int f;
    protected long g;
    protected int h;
    protected String i;
    protected a j;
    public int k;
    protected String l;
    protected int m;

    public g(long j, long j2, int i, long j3) {
        this(j, j2, i, j3, 1);
    }

    public g(long j, long j2, int i, long j3, int i2) {
        this.c = CloudActionEnum.Prepared;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 1;
        this.i = "";
        this.k = 0;
        this.l = "";
        this.m = 0;
        this.a = 0;
        this.b = 0;
        this.d = j;
        this.e = j2;
        this.f = i;
        this.g = j3;
        this.h = i2;
    }

    public g(long j, long j2, int i, long j3, String str, int i2) {
        this.c = CloudActionEnum.Prepared;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 1;
        this.i = "";
        this.k = 0;
        this.l = "";
        this.m = 0;
        this.a = 0;
        this.b = 0;
        this.d = j;
        this.e = j2;
        this.f = i;
        this.g = j3;
        this.i = str;
        this.h = i2;
    }

    public int c() {
        return this.m;
    }

    public int d() {
        return this.k;
    }

    public void b(int i) {
        this.k = i;
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public a e() {
        return this.j;
    }

    public long f() {
        return this.g;
    }

    public CloudActionEnum g() {
        return this.c;
    }

    public void a(CloudActionEnum cloudActionEnum) {
        this.c = cloudActionEnum;
    }

    public long b() {
        return this.d;
    }

    public long h() {
        return this.e;
    }

    public int i() {
        return this.f;
    }

    public int j() {
        return this.a;
    }

    public void c(int i) {
        this.a = i;
    }

    public int k() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public String l() {
        return this.i;
    }

    public int m() {
        return this.h;
    }

    public boolean a(Object obj) {
        if ((obj instanceof g) && this.d == ((g) obj).b()) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof g)) {
            return false;
        }
        boolean z = this.l.equals(((g) obj).n()) && this.d == ((g) obj).b() && this.e == ((g) obj).h() && this.f == ((g) obj).i();
        return z;
    }

    public int hashCode() {
        return (int) ((((long) (this.l.hashCode() + ((int) this.d))) + this.e) + ((long) this.f));
    }

    public String n() {
        return this.l;
    }

    public String toString() {
        return "method : " + this.l + "   @   " + b();
    }
}
