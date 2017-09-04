package format.epub.common.core.xhtml;

import format.epub.common.a.b;
import format.epub.common.core.a.c;

/* compiled from: XHTMLTagControlAction */
class f extends t {
    final byte a;

    f(byte b) {
        this.a = b;
    }

    protected void a(c cVar, c cVar2) {
        b b = cVar.b();
        cVar.a(this.a);
        b.a(this.a, true);
    }

    protected void a(c cVar) {
        cVar.b().a(this.a, false);
    }
}
