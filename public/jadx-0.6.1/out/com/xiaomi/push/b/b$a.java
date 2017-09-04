package com.xiaomi.push.b;

import com.google.protobuf.micro.b;
import com.google.protobuf.micro.c;
import com.google.protobuf.micro.e;
import com.tencent.tinker.android.dx.instruction.Opcodes;

public final class b$a extends e {
    private boolean a;
    private int b = 0;
    private boolean c;
    private long d = 0;
    private boolean e;
    private String f = "";
    private boolean g;
    private String h = "";
    private boolean i;
    private String j = "";
    private boolean k;
    private String l = "";
    private boolean m;
    private String n = "";
    private boolean o;
    private int p = 1;
    private boolean q;
    private int r = 0;
    private boolean s;
    private int t = 0;
    private boolean u;
    private String v = "";
    private int w = -1;

    public int a() {
        if (this.w < 0) {
            b();
        }
        return this.w;
    }

    public /* synthetic */ e a(b bVar) {
        return b(bVar);
    }

    public b$a a(int i) {
        this.a = true;
        this.b = i;
        return this;
    }

    public b$a a(long j) {
        this.c = true;
        this.d = j;
        return this;
    }

    public b$a a(String str) {
        this.e = true;
        this.f = str;
        return this;
    }

    public void a(c cVar) {
        if (e()) {
            cVar.a(1, d());
        }
        if (g()) {
            cVar.b(2, f());
        }
        if (i()) {
            cVar.a(3, h());
        }
        if (k()) {
            cVar.a(4, j());
        }
        if (m()) {
            cVar.a(5, l());
        }
        if (o()) {
            cVar.a(6, n());
        }
        if (r()) {
            cVar.a(7, q());
        }
        if (s()) {
            cVar.a(8, t());
        }
        if (v()) {
            cVar.a(9, u());
        }
        if (x()) {
            cVar.a(10, w());
        }
        if (z()) {
            cVar.a(11, y());
        }
    }

    public int b() {
        int i = 0;
        if (e()) {
            i = 0 + c.c(1, d());
        }
        if (g()) {
            i += c.d(2, f());
        }
        if (i()) {
            i += c.b(3, h());
        }
        if (k()) {
            i += c.b(4, j());
        }
        if (m()) {
            i += c.b(5, l());
        }
        if (o()) {
            i += c.b(6, n());
        }
        if (r()) {
            i += c.b(7, q());
        }
        if (s()) {
            i += c.c(8, t());
        }
        if (v()) {
            i += c.c(9, u());
        }
        if (x()) {
            i += c.c(10, w());
        }
        if (z()) {
            i += c.b(11, y());
        }
        this.w = i;
        return i;
    }

    public b$a b(int i) {
        this.o = true;
        this.p = i;
        return this;
    }

    public b$a b(b bVar) {
        while (true) {
            int a = bVar.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    a(bVar.e());
                    continue;
                case 16:
                    a(bVar.d());
                    continue;
                case 26:
                    a(bVar.g());
                    continue;
                case 34:
                    b(bVar.g());
                    continue;
                case 42:
                    c(bVar.g());
                    continue;
                case 50:
                    d(bVar.g());
                    continue;
                case 58:
                    e(bVar.g());
                    continue;
                case 64:
                    b(bVar.e());
                    continue;
                case Opcodes.AGET_BYTE /*72*/:
                    c(bVar.e());
                    continue;
                case Opcodes.APUT_CHAR /*80*/:
                    d(bVar.e());
                    continue;
                case Opcodes.IPUT_WIDE /*90*/:
                    f(bVar.g());
                    continue;
                default:
                    if (!a(bVar, a)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public b$a b(String str) {
        this.g = true;
        this.h = str;
        return this;
    }

    public b$a c(int i) {
        this.q = true;
        this.r = i;
        return this;
    }

    public b$a c(String str) {
        this.i = true;
        this.j = str;
        return this;
    }

    public int d() {
        return this.b;
    }

    public b$a d(int i) {
        this.s = true;
        this.t = i;
        return this;
    }

    public b$a d(String str) {
        this.k = true;
        this.l = str;
        return this;
    }

    public b$a e(String str) {
        this.m = true;
        this.n = str;
        return this;
    }

    public boolean e() {
        return this.a;
    }

    public long f() {
        return this.d;
    }

    public b$a f(String str) {
        this.u = true;
        this.v = str;
        return this;
    }

    public boolean g() {
        return this.c;
    }

    public String h() {
        return this.f;
    }

    public boolean i() {
        return this.e;
    }

    public String j() {
        return this.h;
    }

    public boolean k() {
        return this.g;
    }

    public String l() {
        return this.j;
    }

    public boolean m() {
        return this.i;
    }

    public String n() {
        return this.l;
    }

    public boolean o() {
        return this.k;
    }

    public b$a p() {
        this.k = false;
        this.l = "";
        return this;
    }

    public String q() {
        return this.n;
    }

    public boolean r() {
        return this.m;
    }

    public boolean s() {
        return this.o;
    }

    public int t() {
        return this.p;
    }

    public int u() {
        return this.r;
    }

    public boolean v() {
        return this.q;
    }

    public int w() {
        return this.t;
    }

    public boolean x() {
        return this.s;
    }

    public String y() {
        return this.v;
    }

    public boolean z() {
        return this.u;
    }
}
