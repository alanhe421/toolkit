package org.jsoup.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.c;
import org.jsoup.nodes.g;

public class Selector {
    private final c a;
    private final g b;

    public static class SelectorParseException extends IllegalStateException {
        public SelectorParseException(String str, Object... objArr) {
            super(String.format(str, objArr));
        }
    }

    private Selector(c cVar, g gVar) {
        c.a((Object) cVar);
        c.a((Object) gVar);
        this.a = cVar;
        this.b = gVar;
    }

    public static Elements a(c cVar, g gVar) {
        return new Selector(cVar, gVar).a();
    }

    public static Elements a(String str, Iterable<g> iterable) {
        c.a(str);
        c.a((Object) iterable);
        c a = f.a(str);
        List arrayList = new ArrayList();
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (g a2 : iterable) {
            g a22;
            Iterator it = a(a, a22).iterator();
            while (it.hasNext()) {
                a22 = (g) it.next();
                if (!identityHashMap.containsKey(a22)) {
                    arrayList.add(a22);
                    identityHashMap.put(a22, Boolean.TRUE);
                }
            }
        }
        return new Elements(arrayList);
    }

    private Elements a() {
        return a.a(this.a, this.b);
    }

    static Elements a(Collection<g> collection, Collection<g> collection2) {
        Elements elements = new Elements();
        for (g gVar : collection) {
            Object obj;
            for (g equals : collection2) {
                if (gVar.equals(equals)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                elements.add(gVar);
            }
        }
        return elements;
    }
}
