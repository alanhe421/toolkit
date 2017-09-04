package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.jsoup.helper.c;
import org.jsoup.nodes.g;
import org.jsoup.nodes.h;

public class Elements extends ArrayList<g> {
    public Elements(int i) {
        super(i);
    }

    public Elements(Collection<g> collection) {
        super(collection);
    }

    public Elements(List<g> list) {
        super(list);
    }

    public Elements(g... gVarArr) {
        super(Arrays.asList(gVarArr));
    }

    public Elements clone() {
        Elements elements = new Elements(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            elements.add(((g) it.next()).g());
        }
        return elements;
    }

    public String attr(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar.r(str)) {
                return gVar.q(str);
            }
        }
        return "";
    }

    public boolean hasAttr(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((g) it.next()).r(str)) {
                return true;
            }
        }
        return false;
    }

    public List<String> eachAttr(String str) {
        List<String> arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar.r(str)) {
                arrayList.add(gVar.q(str));
            }
        }
        return arrayList;
    }

    public Elements attr(String str, String str2) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).a(str, str2);
        }
        return this;
    }

    public Elements removeAttr(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).s(str);
        }
        return this;
    }

    public Elements addClass(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).i(str);
        }
        return this;
    }

    public Elements removeClass(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).j(str);
        }
        return this;
    }

    public Elements toggleClass(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).k(str);
        }
        return this;
    }

    public boolean hasClass(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((g) it.next()).h(str)) {
                return true;
            }
        }
        return false;
    }

    public String val() {
        if (size() > 0) {
            return first().C();
        }
        return "";
    }

    public Elements val(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).l(str);
        }
        return this;
    }

    public String text() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (stringBuilder.length() != 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(gVar.w());
        }
        return stringBuilder.toString();
    }

    public boolean hasText() {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((g) it.next()).y()) {
                return true;
            }
        }
        return false;
    }

    public List<String> eachText() {
        List arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar.y()) {
                arrayList.add(gVar.w());
            }
        }
        return arrayList;
    }

    public String html() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(gVar.D());
        }
        return stringBuilder.toString();
    }

    public String outerHtml() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(gVar.c());
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return outerHtml();
    }

    public Elements tagName(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).b(str);
        }
        return this;
    }

    public Elements html(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).m(str);
        }
        return this;
    }

    public Elements prepend(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).d(str);
        }
        return this;
    }

    public Elements append(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).c(str);
        }
        return this;
    }

    public Elements before(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).e(str);
        }
        return this;
    }

    public Elements after(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).f(str);
        }
        return this;
    }

    public Elements wrap(String str) {
        c.a(str);
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).g(str);
        }
        return this;
    }

    public Elements unwrap() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).O();
        }
        return this;
    }

    public Elements empty() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).q();
        }
        return this;
    }

    public Elements remove() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((g) it.next()).N();
        }
        return this;
    }

    public Elements select(String str) {
        return Selector.a(str, (Iterable) this);
    }

    public Elements not(String str) {
        return Selector.a((Collection) this, Selector.a(str, (Iterable) this));
    }

    public Elements eq(int i) {
        if (size() <= i) {
            return new Elements();
        }
        return new Elements((g) get(i));
    }

    public boolean is(String str) {
        c a = f.a(str);
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((g) it.next()).a(a)) {
                return true;
            }
        }
        return false;
    }

    public Elements next() {
        return siblings(null, true, false);
    }

    public Elements next(String str) {
        return siblings(str, true, false);
    }

    public Elements nextAll() {
        return siblings(null, true, true);
    }

    public Elements nextAll(String str) {
        return siblings(str, true, true);
    }

    public Elements prev() {
        return siblings(null, false, false);
    }

    public Elements prev(String str) {
        return siblings(str, false, false);
    }

    public Elements prevAll() {
        return siblings(null, false, true);
    }

    public Elements prevAll(String str) {
        return siblings(str, false, true);
    }

    private Elements siblings(String str, boolean z, boolean z2) {
        Elements elements = new Elements();
        c a = str != null ? f.a(str) : null;
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            do {
                gVar = z ? gVar.s() : gVar.t();
                if (gVar == null) {
                    break;
                } else if (a == null) {
                    elements.add(gVar);
                    continue;
                } else if (gVar.a(a)) {
                    elements.add(gVar);
                    continue;
                } else {
                    continue;
                }
            } while (z2);
        }
        return elements;
    }

    public Elements parents() {
        Collection linkedHashSet = new LinkedHashSet();
        Iterator it = iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(((g) it.next()).n());
        }
        return new Elements(linkedHashSet);
    }

    public g first() {
        return isEmpty() ? null : (g) get(0);
    }

    public g last() {
        return isEmpty() ? null : (g) get(size() - 1);
    }

    public Elements traverse(e eVar) {
        c.a((Object) eVar);
        d dVar = new d(eVar);
        Iterator it = iterator();
        while (it.hasNext()) {
            dVar.a((g) it.next());
        }
        return this;
    }

    public List<h> forms() {
        List arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar instanceof h) {
                arrayList.add((h) gVar);
            }
        }
        return arrayList;
    }
}
