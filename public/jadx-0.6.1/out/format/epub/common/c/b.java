package format.epub.common.c;

import format.epub.common.c.b.g;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PluginCollection */
public class b {
    private static b a;
    private final ArrayList<a> b = new ArrayList();

    public static b a() {
        if (a == null) {
            a = new b();
            a.b.add(new g());
        }
        return a;
    }

    private b() {
    }

    public a a(format.epub.common.b.b bVar) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.a(bVar)) {
                return aVar;
            }
        }
        return null;
    }
}
