package com.qrcomic.screenshot.c;

import com.qrcomic.screenshot.b.a;

/* compiled from: QRLayerCommand */
public abstract class f implements c {
    a a;
    e b;
    e c;

    public f(a aVar) {
        this.a = aVar;
    }

    public f(a aVar, e eVar) {
        this.a = aVar;
        this.b = eVar;
    }

    public e a() {
        return this.b;
    }

    public void a(e eVar) {
        this.c = eVar;
    }

    public a b() {
        return this.a;
    }
}
