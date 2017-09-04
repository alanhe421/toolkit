package format.epub.common.core.xhtml;

import format.epub.common.text.model.n;
import format.epub.options.ZLBoolean3;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TagData */
public class a {
    List<Integer> a = new ArrayList();
    List<n> b = new ArrayList();
    ZLBoolean3 c;
    int d;
    XHTMLTagInfoList e = new XHTMLTagInfoList();

    public XHTMLTagInfoList a() {
        return this.e;
    }

    public List<n> b() {
        return this.b;
    }

    public List<Integer> c() {
        return this.a;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }
}
