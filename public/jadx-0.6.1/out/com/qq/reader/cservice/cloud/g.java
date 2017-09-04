package com.qq.reader.cservice.cloud;

import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.module.bookstore.qnative.b.b;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.File;

/* compiled from: CloudTag */
public class g implements Comparable<g> {
    private String A = "";
    private int B;
    long a = 0;
    protected String b = "";
    private long c = 0;
    private long d = 0;
    private int e = 0;
    private String f;
    private String g = "";
    private String h;
    private String i = "";
    private String j = "";
    private String k = null;
    private String l;
    private int m;
    private int n = 0;
    private int o = 0;
    private String p = "";
    private int q = 0;
    private int r = 0;
    private volatile boolean s = false;
    private int t = 0;
    private int u = 0;
    private String v = "";
    private String w = "";
    private b x;
    private DownloadBookTask y = null;
    private long z = 0;

    public /* synthetic */ int compareTo(Object obj) {
        return a((g) obj);
    }

    public g(long j, long j2, int i) {
        this.c = j;
        this.a = j2;
        this.x = new b();
        if (i == 0) {
            i = 1;
        }
        this.B = i;
    }

    public void a(String str) {
        this.x.a(str);
    }

    public b a() {
        return this.x;
    }

    public String b() {
        return this.x.j();
    }

    public long c() {
        return this.z;
    }

    public void a(long j) {
        this.z = j;
    }

    public String d() {
        return this.A;
    }

    public void b(String str) {
        this.A = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void a(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(".");
        stringBuffer.append(str2);
        this.b = ao.g() + File.separator + stringBuffer.toString();
    }

    public String e() {
        return this.b;
    }

    public long f() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public long g() {
        return this.a;
    }

    public void c(long j) {
        this.a = j;
    }

    public boolean a(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        if (this.a < ((g) obj).g()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((int) (this.c ^ (this.c >>> 32))) + 31) * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + (this.e ^ (this.e >>> 32));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (f() == gVar.f() && h() == gVar.h() && i() == gVar.i()) {
            return true;
        }
        return false;
    }

    public long h() {
        return this.d;
    }

    public void d(long j) {
        if (w() != 3 && j == 0) {
            j = 1;
        }
        this.d = j;
    }

    public int i() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public void d(String str) {
        this.f = str;
        if (this.g == null || this.g.length() == 0) {
            String str2 = "";
            if (this.f != null) {
                int lastIndexOf = this.f.lastIndexOf(".");
                if (lastIndexOf != -1) {
                    str2 = this.f.substring(0, lastIndexOf);
                } else {
                    str2 = this.f;
                }
                this.g = ao.a(new StringBuffer(str2));
            }
        }
    }

    public String j() {
        return this.f;
    }

    public void e(String str) {
        this.h = str;
    }

    public String k() {
        if (this.h == null || this.h.length() == 0) {
            this.h = "匿名";
        }
        return this.h;
    }

    public void f(String str) {
        this.i = str;
    }

    public String l() {
        return this.i;
    }

    public void g(String str) {
        this.j = str;
    }

    public String m() {
        if (this.j == null || this.j.length() == 0) {
            if (w() == 3) {
                this.j = ao.h(this.c);
            } else if (w() == 2) {
                this.j = ao.a(this.c, false, (int) Opcodes.OR_INT);
            } else {
                this.j = ao.q(this.j);
            }
        }
        return this.j;
    }

    public void h(String str) {
        this.l = str;
    }

    public String n() {
        return this.l;
    }

    public void b(int i) {
        this.m = i;
    }

    public int o() {
        return this.m;
    }

    public int p() {
        return this.o;
    }

    public void c(int i) {
        this.o = i;
    }

    public String q() {
        return this.p;
    }

    public void i(String str) {
        this.p = str;
    }

    public int r() {
        return this.q;
    }

    public void d(int i) {
        this.q = i;
    }

    public int s() {
        return this.r;
    }

    public void e(int i) {
        this.r = i;
    }

    public String t() {
        return this.w;
    }

    public void j(String str) {
        this.w = str;
    }

    public int u() {
        return this.n;
    }

    public void f(int i) {
        this.n = i;
    }

    public String v() {
        return this.g;
    }

    public int a(g gVar) {
        if (this.c != gVar.f()) {
            return 0;
        }
        int h = (int) (this.d - gVar.h());
        if (h != 0) {
            return h;
        }
        long i = (long) (this.e - gVar.i());
        if (i > 0) {
            return 1;
        }
        if (i != 0) {
            return -1;
        }
        return 0;
    }

    public int w() {
        return this.B;
    }

    public void g(int i) {
        this.B = i;
    }
}
