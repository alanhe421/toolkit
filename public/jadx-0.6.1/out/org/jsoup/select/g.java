package org.jsoup.select;

import java.util.Iterator;

/* compiled from: StructuralEvaluator */
abstract class g extends c {
    c a;

    /* compiled from: StructuralEvaluator */
    static class a extends g {
        public a(c cVar) {
            this.a = cVar;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            Iterator it = gVar2.v().iterator();
            while (it.hasNext()) {
                org.jsoup.nodes.g gVar3 = (org.jsoup.nodes.g) it.next();
                if (gVar3 != gVar2 && this.a.a(gVar, gVar3)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":has(%s)", new Object[]{this.a});
        }
    }

    /* compiled from: StructuralEvaluator */
    static class b extends g {
        public b(c cVar) {
            this.a = cVar;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            if (gVar == gVar2) {
                return false;
            }
            org.jsoup.nodes.g m = gVar2.m();
            if (m == null || !this.a.a(gVar, m)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return String.format(":ImmediateParent%s", new Object[]{this.a});
        }
    }

    /* compiled from: StructuralEvaluator */
    static class c extends g {
        public c(c cVar) {
            this.a = cVar;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            if (gVar == gVar2) {
                return false;
            }
            org.jsoup.nodes.g t = gVar2.t();
            if (t == null || !this.a.a(gVar, t)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return String.format(":prev%s", new Object[]{this.a});
        }
    }

    /* compiled from: StructuralEvaluator */
    static class d extends g {
        public d(c cVar) {
            this.a = cVar;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return !this.a.a(gVar, gVar2);
        }

        public String toString() {
            return String.format(":not%s", new Object[]{this.a});
        }
    }

    /* compiled from: StructuralEvaluator */
    static class e extends g {
        public e(c cVar) {
            this.a = cVar;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            if (gVar == gVar2) {
                return false;
            }
            for (org.jsoup.nodes.g m = gVar2.m(); !this.a.a(gVar, m); m = m.m()) {
                if (m == gVar) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return String.format(":parent%s", new Object[]{this.a});
        }
    }

    /* compiled from: StructuralEvaluator */
    static class f extends g {
        public f(c cVar) {
            this.a = cVar;
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            if (gVar == gVar2) {
                return false;
            }
            for (org.jsoup.nodes.g t = gVar2.t(); t != null; t = t.t()) {
                if (this.a.a(gVar, t)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":prev*%s", new Object[]{this.a});
        }
    }

    /* compiled from: StructuralEvaluator */
    static class g extends c {
        g() {
        }

        public boolean a(org.jsoup.nodes.g gVar, org.jsoup.nodes.g gVar2) {
            return gVar == gVar2;
        }
    }

    g() {
    }
}
