package com.xiaomi.push.b;

import com.google.protobuf.micro.c;
import com.google.protobuf.micro.e;
import com.xiaomi.push.b.b.b;

public final class b$f extends e {
    private boolean a;
    private String b = "";
    private boolean c;
    private String d = "";
    private boolean e;
    private b f = null;
    private int g = -1;

    public static b$f b(byte[] bArr) {
        return (b$f) new b$f().a(bArr);
    }

    public int a() {
        if (this.g < 0) {
            b();
        }
        return this.g;
    }

    public /* synthetic */ e a(com.google.protobuf.micro.b bVar) {
        return b(bVar);
    }

    public b$f a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException();
        }
        this.e = true;
        this.f = bVar;
        return this;
    }

    public b$f a(String str) {
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
        if (h()) {
            cVar.a(3, i());
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
        if (h()) {
            i += c.b(3, i());
        }
        this.g = i;
        return i;
    }

    public b$f b(com.google.protobuf.micro.b bVar) {
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
                    b bVar2 = new b();
                    bVar.a(bVar2);
                    a(bVar2);
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

    public b$f b(String str) {
        this.c = true;
        this.d = str;
        return this;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.a;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.c;
    }

    public boolean h() {
        return this.e;
    }

    public b i() {
        return this.f;
    }
}
