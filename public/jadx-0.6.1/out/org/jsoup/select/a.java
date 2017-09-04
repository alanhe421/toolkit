package org.jsoup.select;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.g;

/* compiled from: Collector */
public class a {

    /* compiled from: Collector */
    private static class a implements e {
        private final g a;
        private final Elements b;
        private final c c;

        a(g gVar, Elements elements, c cVar) {
            this.a = gVar;
            this.b = elements;
            this.c = cVar;
        }

        public void a(Node node, int i) {
            if (node instanceof g) {
                g gVar = (g) node;
                if (this.c.a(this.a, gVar)) {
                    this.b.add(gVar);
                }
            }
        }

        public void b(Node node, int i) {
        }
    }

    public static Elements a(c cVar, g gVar) {
        Elements elements = new Elements();
        new d(new a(gVar, elements, cVar)).a(gVar);
        return elements;
    }
}
