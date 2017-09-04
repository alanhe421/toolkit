package format.epub.common.core.xhtml;

import format.epub.common.core.a.c;

/* compiled from: XHTMLTagBodyAction */
class e extends b {
    e() {
    }

    protected void a(c cVar, c cVar2) {
        cVar.n++;
        if (cVar.n > 0) {
            cVar.t = 2;
        }
    }

    protected void a(c cVar) {
        cVar.c();
        cVar.n--;
        if (cVar.n <= 0) {
            cVar.t = 0;
        }
    }
}
