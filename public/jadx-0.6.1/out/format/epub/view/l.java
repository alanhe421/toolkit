package format.epub.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: ZLTextHorizontalConvexHull */
class l {
    private final LinkedList<a> a = new LinkedList();

    /* compiled from: ZLTextHorizontalConvexHull */
    private static final class a {
        float a;
        float b;
        float c;
        float d;

        a(float f, float f2, float f3, float f4) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
        }

        a(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
        }
    }

    l(List<h> list) {
        for (h a : list) {
            a(a);
        }
        a();
    }

    private void a(h hVar) {
        if (this.a.isEmpty()) {
            this.a.add(new a(hVar.a, hVar.b, hVar.c, hVar.d));
            return;
        }
        a aVar;
        float f = hVar.c;
        float f2 = hVar.d;
        ListIterator listIterator = this.a.listIterator();
        while (listIterator.hasNext()) {
            aVar = (a) listIterator.next();
            if (aVar.d > f) {
                if (aVar.c >= f2) {
                    break;
                }
                a aVar2;
                if (aVar.c < f) {
                    aVar2 = new a(aVar);
                    aVar2.d = f;
                    aVar.c = f;
                    listIterator.previous();
                    listIterator.add(aVar2);
                    listIterator.next();
                }
                if (aVar.d > f2) {
                    aVar2 = new a(aVar);
                    aVar2.c = f2;
                    aVar.d = f2;
                    listIterator.add(aVar2);
                }
                aVar.a = Math.min(aVar.a, hVar.a);
                aVar.b = Math.max(aVar.b, hVar.b);
            }
        }
        aVar = (a) this.a.getFirst();
        if (f < aVar.c) {
            this.a.add(0, new a(hVar.a, hVar.b, f, Math.min(f2, aVar.c)));
        }
        aVar = (a) this.a.getLast();
        if (f2 > aVar.d) {
            this.a.add(new a(hVar.a, hVar.b, Math.max(f, aVar.d), f2));
        }
    }

    private void a() {
        ListIterator listIterator = this.a.listIterator();
        a aVar = null;
        while (listIterator.hasNext()) {
            a aVar2 = (a) listIterator.next();
            if (aVar != null) {
                if (aVar.a == aVar2.a && aVar.b == aVar2.b) {
                    aVar.d = aVar2.d;
                    listIterator.remove();
                } else if (aVar.d != aVar2.c && aVar2.a <= aVar.b && aVar.a <= aVar2.b) {
                    listIterator.previous();
                    listIterator.add(new a(Math.max(aVar.a, aVar2.a), Math.min(aVar.b, aVar2.b), aVar.d, aVar2.c));
                    listIterator.next();
                }
            }
            aVar = aVar2;
        }
    }

    float a(float f, float f2) {
        Iterator it = this.a.iterator();
        float f3 = 2.14748365E9f;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            float f4 = aVar.a > f ? aVar.a - f : aVar.b < f ? f - aVar.b : 0.0f;
            float f5 = aVar.c > f2 ? aVar.c - f2 : aVar.d < f2 ? f2 - aVar.d : 0.0f;
            f5 = Math.min(f3, Math.max(f4, f5));
            if (f5 == 0.0f) {
                return f5;
            }
            f3 = f5;
        }
        return f3;
    }
}
