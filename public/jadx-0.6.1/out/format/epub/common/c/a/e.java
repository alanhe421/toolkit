package format.epub.common.c.a;

import format.epub.common.b.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: StyleSheetParserWithCache */
public class e extends c {
    private List<a> f = new ArrayList();
    private Set<String> g = new HashSet();

    /* compiled from: StyleSheetParserWithCache */
    private class a {
        b a;
        Map<String, String> b;
        final /* synthetic */ e c;

        a(e eVar, b bVar, Map<String, String> map) {
            this.c = eVar;
            this.a = bVar;
            this.b = new LinkedHashMap(map);
        }
    }

    public e(b bVar, String str) {
        super(str);
        this.g.add(bVar.c());
    }

    protected void a(b bVar, Map<String, String> map) {
        this.f.add(new a(this, bVar, map));
    }

    protected void b(String str) {
    }

    public void a(g gVar) {
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.f.get(i);
            gVar.a(aVar.a, aVar.b);
        }
    }
}
