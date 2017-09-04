package com.xiaomi.push.b;

import com.google.protobuf.micro.b;
import com.google.protobuf.micro.c;
import com.google.protobuf.micro.e;

public final class b$c extends e {
    private boolean a;
    private String b = "";
    private boolean c;
    private String d = "";
    private boolean e;
    private String f = "";
    private boolean g;
    private String h = "";
    private boolean i;
    private String j = "";
    private boolean k;
    private String l = "";
    private int m = -1;

    public int a() {
        if (this.m < 0) {
            b();
        }
        return this.m;
    }

    public /* synthetic */ e a(b bVar) {
        return b(bVar);
    }

    public b$c a(String str) {
        this.a = true;
        this.b = str;
        return this;
    }

    public void a(c cVar) {
        if (e()) {
            cVar.a(1, d());
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
    }

    public int b() {
        int i = 0;
        if (e()) {
            i = 0 + c.b(1, d());
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
            i += c.b(5, l());
        }
        if (o()) {
            i += c.b(6, n());
        }
        this.m = i;
        return i;
    }

    public b$c b(b bVar) {
        while (true) {
            int a = bVar.a();
            switch (a) {
                case 0:
                    break;
                case 10:
                    a(bVar.g());
                    continue;
                case 18:
                    b(bVar.g());
                    continue;
                case 26:
                    c(bVar.g());
                    continue;
                case 34:
                    d(bVar.g());
                    continue;
                case 42:
                    e(bVar.g());
                    continue;
                case 50:
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

    public b$c b(String str) {
        this.c = true;
        this.d = str;
        return this;
    }

    public b$c c(String str) {
        this.e = true;
        this.f = str;
        return this;
    }

    public b$c d(String str) {
        this.g = true;
        this.h = str;
        return this;
    }

    public String d() {
        return this.b;
    }

    public b$c e(String str) {
        this.i = true;
        this.j = str;
        return this;
    }

    public boolean e() {
        return this.a;
    }

    public b$c f(String str) {
        this.k = true;
        this.l = str;
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
}
