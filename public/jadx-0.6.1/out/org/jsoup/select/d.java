package org.jsoup.select;

import org.jsoup.nodes.Node;

/* compiled from: NodeTraversor */
public class d {
    private e a;

    public d(e eVar) {
        this.a = eVar;
    }

    public void a(Node node) {
        int i = 0;
        Node node2 = node;
        while (node2 != null) {
            this.a.a(node2, i);
            if (node2.I() > 0) {
                node2 = node2.b(0);
                i++;
            } else {
                while (node2.R() == null && i > 0) {
                    this.a.b(node2, i);
                    node2 = node2.K();
                    i--;
                }
                this.a.b(node2, i);
                if (node2 != node) {
                    node2 = node2.R();
                } else {
                    return;
                }
            }
        }
    }
}
