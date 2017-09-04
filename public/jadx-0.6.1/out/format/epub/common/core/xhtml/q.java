package format.epub.common.core.xhtml;

import format.epub.common.b.b;
import format.epub.common.core.a.c;
import format.epub.common.utils.d;

/* compiled from: XHTMLTagSourceAction */
public class q extends d {
    protected void a(c cVar, c cVar2) {
        String a = cVar2.a("type");
        String a2 = cVar2.a("src");
        if (a != null && a2 != null) {
            cVar.r.a(a, b.b(cVar.b + d.b(a2)).c());
        }
    }

    protected void a(c cVar) {
    }

    protected boolean a(int i) {
        return i == 3;
    }
}
