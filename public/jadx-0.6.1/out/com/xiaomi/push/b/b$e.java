package com.xiaomi.push.b;

import com.google.protobuf.micro.c;
import com.google.protobuf.micro.e;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.xiaomi.push.b.b.b;

public final class b$e extends e {
    private boolean a;
    private int b = 0;
    private boolean c;
    private String d = "";
    private boolean e;
    private String f = "";
    private boolean g;
    private String h = "";
    private boolean i;
    private int j = 0;
    private boolean k;
    private String l = "";
    private boolean m;
    private String n = "";
    private boolean o;
    private String p = "";
    private boolean q;
    private b r = null;
    private boolean s;
    private int t = 0;
    private int u = -1;

    public int a() {
        if (this.u < 0) {
            b();
        }
        return this.u;
    }

    public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
        return b(bVar);
    }

    public b$e a(int i) {
        this.a = true;
        this.b = i;
        return this;
    }

    public b$e a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException();
        }
        this.q = true;
        this.r = bVar;
        return this;
    }

    public b$e a(String str) {
        this.c = true;
        this.d = str;
        return this;
    }

    public void a(c cVar) {
        if (e()) {
            cVar.b(1, d());
        }
        if (g()) {
            cVar.a(2, f());
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
        if (q()) {
            cVar.a(7, p());
        }
        if (s()) {
            cVar.a(8, r());
        }
        if (t()) {
            cVar.a(9, u());
        }
        if (w()) {
            cVar.a(10, v());
        }
    }

    public int b() {
        int i = 0;
        if (e()) {
            i = 0 + c.d(1, d());
        }
        if (g()) {
            i += c.b(2, f());
        }
        if (i()) {
            i += c.b(3, h());
        }
        if (k()) {
            i += c.b(4, j());
        }
        if (m()) {
            i += c.c(5, l());
        }
        if (o()) {
            i += c.b(6, n());
        }
        if (q()) {
            i += c.b(7, p());
        }
        if (s()) {
            i += c.b(8, r());
        }
        if (t()) {
            i += c.b(9, u());
        }
        if (w()) {
            i += c.c(10, v());
        }
        this.u = i;
        return i;
    }

    public b$e b(int i) {
        this.i = true;
        this.j = i;
        return this;
    }

    public b$e b(com.google.protobuf.micro.b bVar) {
        while (true) {
            int a = bVar.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    a(bVar.i());
                    continue;
                case 18:
                    a(bVar.g());
                    continue;
                case 26:
                    b(bVar.g());
                    continue;
                case 34:
                    c(bVar.g());
                    continue;
                case 40:
                    b(bVar.e());
                    continue;
                case 50:
                    d(bVar.g());
                    continue;
                case 58:
                    e(bVar.g());
                    continue;
                case 66:
                    f(bVar.g());
                    continue;
                case Opcodes.AGET_SHORT /*74*/:
                    b bVar2 = new b();
                    bVar.a(bVar2);
                    a(bVar2);
                    continue;
                case Opcodes.APUT_CHAR /*80*/:
                    c(bVar.e());
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

    public b$e b(String str) {
        this.e = true;
        this.f = str;
        return this;
    }

    public b$e c(int i) {
        this.s = true;
        this.t = i;
        return this;
    }

    public b$e c(String str) {
        this.g = true;
        this.h = str;
        return this;
    }

    public int d() {
        return this.b;
    }

    public b$e d(String str) {
        this.k = true;
        this.l = str;
        return this;
    }

    public b$e e(String str) {
        this.m = true;
        this.n = str;
        return this;
    }

    public boolean e() {
        return this.a;
    }

    public b$e f(String str) {
        this.o = true;
        this.p = str;
        return this;
    }

    public String f() {
        return this.d;
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

    public int l() {
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

    public String p() {
        return this.n;
    }

    public boolean q() {
        return this.m;
    }

    public String r() {
        return this.p;
    }

    public boolean s() {
        return this.o;
    }

    public boolean t() {
        return this.q;
    }

    public b u() {
        return this.r;
    }

    public int v() {
        return this.t;
    }

    public boolean w() {
        return this.s;
    }
}
