package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.jsoup.nodes.g;

/* compiled from: CombiningEvaluator */
abstract class b extends c {
    final ArrayList<c> a;
    int b;

    /* compiled from: CombiningEvaluator */
    static final class a extends b {
        a(Collection<c> collection) {
            super(collection);
        }

        a(c... cVarArr) {
            this(Arrays.asList(cVarArr));
        }

        public boolean a(g gVar, g gVar2) {
            for (int i = 0; i < this.b; i++) {
                if (!((c) this.a.get(i)).a(gVar, gVar2)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return org.jsoup.helper.b.a(this.a, " ");
        }
    }

    /* compiled from: CombiningEvaluator */
    static final class b extends b {
        b(Collection<c> collection) {
            if (this.b > 1) {
                this.a.add(new a((Collection) collection));
            } else {
                this.a.addAll(collection);
            }
            b();
        }

        b(c... cVarArr) {
            this(Arrays.asList(cVarArr));
        }

        b() {
        }

        public void b(c cVar) {
            this.a.add(cVar);
            b();
        }

        public boolean a(g gVar, g gVar2) {
            for (int i = 0; i < this.b; i++) {
                if (((c) this.a.get(i)).a(gVar, gVar2)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":or%s", new Object[]{this.a});
        }
    }

    b() {
        this.b = 0;
        this.a = new ArrayList();
    }

    b(Collection<c> collection) {
        this();
        this.a.addAll(collection);
        b();
    }

    c a() {
        return this.b > 0 ? (c) this.a.get(this.b - 1) : null;
    }

    void a(c cVar) {
        this.a.set(this.b - 1, cVar);
    }

    void b() {
        this.b = this.a.size();
    }
}
