package format.epub.common.core.xhtml;

import format.epub.common.a.b;
import format.epub.common.core.a.c;

/* compiled from: XHTMLTagParagraphWithControlAction */
class n extends t {
    final byte a;

    n(byte b) {
        this.a = b;
    }

    protected void a(c cVar, c cVar2) {
        b b = cVar.b();
        switch (this.a) {
            case (byte) 1:
                if (b.a.b.b() > 1) {
                    b.c();
                    break;
                }
                break;
        }
        cVar.a(this.a);
        if (!cVar.g) {
            cVar.a(false);
            cVar.g = true;
        }
    }

    protected void a(c cVar) {
        cVar.c();
    }
}
