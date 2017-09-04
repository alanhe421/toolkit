package com.qq.reader.readengine.kernel.a;

import android.graphics.RectF;
import com.qq.reader.readengine.kernel.f;
import format.epub.common.utils.ZLStyleNodeList;
import format.epub.view.e;
import format.epub.view.r;
import format.epub.view.style.b;
import format.epub.view.z;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: QEPubPage */
public class d extends f {
    final z b = new z();
    final z c = new z();
    final List<r> d = new ArrayList();
    public final ZLStyleNodeList e = new ZLStyleNodeList();
    public List<b> f = new ArrayList();
    public Set<b> g = new HashSet();
    int h = 0;
    float i;
    float j;
    float k;
    float l;
    float m;

    void a(z zVar) {
        this.b.a(zVar);
        this.c.p();
        this.d.clear();
        this.f.clear();
        this.g.clear();
        this.e.clear();
        this.h = 2;
    }

    void b(z zVar) {
        int a = zVar.a();
        int b = zVar.b();
        int c = zVar.c();
        this.c.a(zVar);
        if (this.c.d()) {
            this.c.a(this.b);
        }
        this.c.a(a);
        if (a > 0 && b == 0 && c == 0) {
            this.c.m();
            this.c.o();
        } else {
            this.c.a(b, c);
        }
        this.b.p();
        this.d.clear();
        this.f.clear();
        this.g.clear();
        this.e.clear();
        this.h = 3;
    }

    public boolean b() {
        return this.h == 1;
    }

    public boolean c() {
        if (this.c == null || this.c.d() || (this.c.f() && this.c.j().d())) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (this.b == null || this.b.d() || (this.b.e() && this.b.j().c())) {
            return true;
        }
        return false;
    }

    public z e() {
        return this.b;
    }

    public z f() {
        return this.c;
    }

    public List<r> g() {
        return this.d;
    }

    public float h() {
        return this.i;
    }

    public void a(float f) {
        this.i = f;
    }

    public float i() {
        return this.j;
    }

    public void b(float f) {
        this.j = f;
    }

    public int j() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public float k() {
        return this.k;
    }

    public ZLStyleNodeList l() {
        return this.e;
    }

    public void m() {
        for (int size = this.e.size() - 1; size >= 0; size--) {
            e eVar = (e) this.e.get(size);
            while (true) {
                RectF u = ((b) eVar.b).u();
                e eVar2 = eVar.g;
                if (eVar2 != null) {
                    ((b) eVar2.b).a(u);
                }
                eVar2 = eVar.f;
                if (eVar2 == null) {
                    break;
                }
                eVar = eVar2;
            }
        }
    }
}
