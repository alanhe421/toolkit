package format.epub.common.c.b;

import format.epub.common.b.b;
import format.epub.common.book.BookEPub;
import format.epub.common.c.a;

/* compiled from: OEBPlugin */
public class g extends a {
    public boolean a(b bVar) {
        String l = bVar.l();
        return "oebzip".equals(l) || "opf".equals(l) || com.qq.reader.readengine.model.a.i(l);
    }

    private b c(b bVar) {
        if ("opf".equals(bVar.l())) {
            return bVar;
        }
        b b = b.b(bVar, "META-INF/container.xml");
        if (b.a()) {
            a aVar = new a();
            aVar.a(b);
            String a = aVar.a();
            if (a != null) {
                return b.b(bVar, a);
            }
        }
        for (b b2 : bVar.n()) {
            if (b2.l().equals("opf")) {
                return b2;
            }
        }
        return null;
    }

    public boolean a(BookEPub bookEPub) {
        b c = c(bookEPub.File);
        return c != null ? new f(bookEPub).b(c) : false;
    }

    public boolean a(format.epub.common.a.a aVar) {
        aVar.a.File.a(true);
        if (aVar.c()) {
            return true;
        }
        b c = c(aVar.a.File);
        return c != null ? new c(aVar).b(c) : false;
    }

    public format.epub.common.image.b b(b bVar) {
        b c = c(bVar);
        return c != null ? new e().a(c) : null;
    }
}
