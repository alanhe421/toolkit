package format.epub.common.core.xhtml;

import format.epub.common.b.b;
import format.epub.common.c.a.e;
import format.epub.common.core.a.c;
import format.epub.common.utils.d;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: XHTMLTagLinkAction */
public class k extends b {
    protected void a(c cVar, c cVar2) {
        String str = "stylesheet";
        String a = cVar2.a("rel");
        if (a != null && str.equals(a)) {
            str = "text/css";
            a = cVar2.a("type");
            if (a != null && str.equals(a)) {
                str = cVar2.a("href");
                if (str != null) {
                    str = cVar.b + d.b(str);
                    com.qq.reader.common.monitor.debug.c.a("CSS", "style file: " + str);
                    b b = b.b(str);
                    String c = b.c();
                    e eVar = (e) cVar.m.get(c);
                    if (eVar == null) {
                        eVar = new e(b, d.a(b));
                        cVar.m.put(c, eVar);
                        com.qq.reader.common.monitor.debug.c.a("CSS", "creating stream");
                        try {
                            InputStream i = b.i();
                            if (i != null) {
                                com.qq.reader.common.monitor.debug.c.a("CSS", "parsing file");
                                eVar.a(i);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    eVar.a(cVar.h);
                }
            }
        }
    }

    protected void a(c cVar) {
    }
}
