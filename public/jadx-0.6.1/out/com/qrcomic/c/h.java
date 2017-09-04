package com.qrcomic.c;

import com.qrcomic.c.a.a;
import com.qrcomic.c.a.b;
import com.qrcomic.util.j;

/* compiled from: ThirdPartProvider */
public class h {
    private b a;
    private c b;
    private e c;
    private f d;
    private g e;
    private d f;
    private a g;

    public h(b bVar, c cVar, e eVar, f fVar, g gVar, d dVar, a aVar) {
        this.a = (b) j.a((Object) bVar);
        this.b = (c) j.a((Object) cVar);
        this.c = (e) j.a((Object) eVar);
        this.d = (f) j.a((Object) fVar);
        if (gVar != null) {
            this.e = gVar;
        } else {
            this.e = new a();
        }
        if (dVar != null) {
            this.f = dVar;
        } else {
            this.f = new b();
        }
        if (aVar != null) {
            this.g = aVar;
        } else {
            this.g = new a();
        }
    }

    public b a() {
        return this.a;
    }

    public c b() {
        return this.b;
    }

    public e c() {
        return this.c;
    }

    public g d() {
        return this.e;
    }

    public d e() {
        return this.f;
    }

    public a f() {
        return this.g;
    }
}
