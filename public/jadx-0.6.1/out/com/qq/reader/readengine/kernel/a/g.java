package com.qq.reader.readengine.kernel.a;

import com.qq.reader.readengine.kernel.h;
import format.epub.view.s;
import format.epub.view.y;

/* compiled from: ZLTextTraverser */
public abstract class g {
    a b;

    protected abstract void a();

    protected abstract void a(y yVar);

    protected abstract void b();

    protected g() {
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(h hVar, h hVar2) {
        int a = hVar.a();
        int a2 = hVar2.a();
        s a3 = s.a(this.b.a(), a);
        int i = a;
        while (i <= a2) {
            s h;
            int b = i == a ? hVar.b() : 0;
            int b2 = i == a2 ? hVar2.b() : a3.f() - 1;
            while (b <= b2) {
                format.epub.view.g c = a3.c(b);
                if (c == format.epub.view.g.c) {
                    a();
                } else if (c instanceof y) {
                    a((y) c);
                }
                b++;
            }
            if (i < a2) {
                b();
                h = a3.h();
            } else {
                h = a3;
            }
            i++;
            a3 = h;
        }
    }
}
