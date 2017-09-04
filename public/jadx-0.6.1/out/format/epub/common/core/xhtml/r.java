package format.epub.common.core.xhtml;

import format.epub.common.c.a.h;
import format.epub.common.core.a.c;

/* compiled from: XHTMLTagStyleAction */
public class r extends b {
    protected void a(c cVar, c cVar2) {
        String str = "text/css";
        String a = cVar2.a("type");
        if (a != null && str.equals(a) && cVar.t == 0) {
            cVar.t = 1;
            cVar.k = new h(cVar.b, cVar.h);
        }
    }

    protected void a(c cVar) {
        if (cVar.t == 1) {
            cVar.t = 0;
            cVar.k.a();
        }
    }
}
