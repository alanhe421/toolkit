package format.epub.common.core.xhtml;

import format.epub.common.core.a.c;

/* compiled from: XHTMLTagParagraphAction */
class m extends t {
    private int a = -1;

    public m(int i) {
        this.a = i;
    }

    protected void a(c cVar, c cVar2) {
        if (!cVar.g) {
            cVar.a(this.a);
            cVar.a(false);
            cVar.g = true;
        }
    }

    protected void a(c cVar) {
        cVar.c();
    }
}
