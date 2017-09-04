package format.epub.view;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ZLTextLineInfo */
public final class r {
    boolean A = false;
    byte B;
    byte C;
    float D;
    byte E;
    boolean F;
    final z G = new z();
    final z H = new z();
    List<h> I = new ArrayList();
    boolean J = false;
    final s a;
    final int b;
    final int c;
    final int d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    int j;
    int k;
    int l;
    float m;
    float n;
    float o;
    float p;
    int q;
    float r;
    int s;
    int t;
    v u;
    float v = 0.0f;
    boolean w = true;
    boolean x = false;
    boolean y = false;
    boolean z = false;

    r(s sVar, int i, int i2, v vVar) {
        this.a = sVar;
        this.b = sVar.f();
        this.c = i;
        this.d = i2;
        this.e = i;
        this.f = i2;
        this.g = i;
        this.h = i2;
        this.u = vVar;
        this.G.a(sVar);
        this.G.a(this.c, this.d);
        this.H.a(sVar);
        this.H.a(this.g, this.h);
    }

    boolean a() {
        return this.g == this.b;
    }

    public boolean equals(Object obj) {
        r rVar = (r) obj;
        return this.a == rVar.a && this.c == rVar.c && this.d == rVar.d;
    }

    public int hashCode() {
        return (this.a.hashCode() + this.c) + (this.d * 239);
    }

    public float b() {
        if (h()) {
            return (this.n * 2.0f) + this.r;
        }
        return this.n + this.r;
    }

    public float c() {
        return this.n + this.r;
    }

    public boolean d() {
        return this.i;
    }

    public z e() {
        return this.G;
    }

    public z f() {
        return this.H;
    }

    public List<h> g() {
        return this.I;
    }

    public boolean h() {
        return this.H.h() || this.J;
    }

    public boolean i() {
        return this.a.j();
    }
}
