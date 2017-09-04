package format.epub.common.core.xhtml;

import com.tencent.mm.performance.WxPerformanceHandle;
import format.epub.common.b.b;
import format.epub.common.core.a.c;
import format.epub.common.image.a;
import format.epub.common.text.model.n;
import format.epub.common.utils.d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: XHTMLTagListAction */
public class l extends t {
    private int a;

    public l(int i) {
        this.a = i;
    }

    protected void a(c cVar, c cVar2) {
        cVar.o.push(Integer.valueOf(this.a));
        cVar.j();
        List<String> arrayList = new ArrayList();
        String a = cVar2.a(WxPerformanceHandle.MESSAGE_CLASS);
        if (a != null) {
            String[] split = a.split(" ");
            for (Object add : split) {
                arrayList.add(add);
            }
        }
        for (String a2 : arrayList) {
            n a3 = cVar.a("", a2);
            if (a3 != null) {
                int u = a3.u();
                if (u != 0) {
                    cVar.c(u);
                } else if (a3.w() != null) {
                    a2 = a3.w();
                    b b = b.b(cVar.b + d.b(a2.substring(4, a2.length() - 1)));
                    if (b != null) {
                        format.epub.common.a.b b2 = cVar.b();
                        String d = b.d();
                        cVar.d(d);
                        b2.a(d, new a("image/auto", b));
                    }
                }
            }
        }
        cVar.a(false);
    }

    protected void a(c cVar) {
        cVar.c();
        cVar.j();
        if (!cVar.o.empty()) {
            cVar.o.pop();
        }
    }
}
