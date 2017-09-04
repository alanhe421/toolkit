package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.SerializationException;
import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.helper.b;
import org.jsoup.helper.c;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.select.d;
import org.jsoup.select.e;

public abstract class Node implements Cloneable {
    private static final List<Node> f = Collections.emptyList();
    Node a;
    List<Node> b;
    b c;
    String d;
    int e;

    private final class NodeList extends ChangeNotifyingArrayList<Node> {
        NodeList(int i) {
            super(i);
        }

        public void onContentsChanged() {
            Node.this.p();
        }
    }

    private static class a implements e {
        private Appendable a;
        private OutputSettings b;

        a(Appendable appendable, OutputSettings outputSettings) {
            this.a = appendable;
            this.b = outputSettings;
        }

        public void a(Node node, int i) {
            try {
                node.a(this.a, i, this.b);
            } catch (Throwable e) {
                throw new SerializationException(e);
            }
        }

        public void b(Node node, int i) {
            if (!node.a().equals("#text")) {
                try {
                    node.b(this.a, i, this.b);
                } catch (Throwable e) {
                    throw new SerializationException(e);
                }
            }
        }
    }

    public abstract String a();

    abstract void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException;

    abstract void b(Appendable appendable, int i, OutputSettings outputSettings) throws IOException;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return h();
    }

    protected Node(String str, b bVar) {
        c.a((Object) str);
        c.a((Object) bVar);
        this.b = f;
        this.d = str.trim();
        this.c = bVar;
    }

    protected Node(String str) {
        this(str, new b());
    }

    protected Node() {
        this.b = f;
        this.c = null;
    }

    public String q(String str) {
        c.a((Object) str);
        String b = this.c.b(str);
        if (b.length() > 0) {
            return b;
        }
        if (org.jsoup.a.a.a(str).startsWith("abs:")) {
            return u(str.substring("abs:".length()));
        }
        return "";
    }

    public b F() {
        return this.c;
    }

    public Node b(String str, String str2) {
        this.c.a(str, str2);
        return this;
    }

    public boolean r(String str) {
        c.a((Object) str);
        if (str.startsWith("abs:")) {
            String substring = str.substring("abs:".length());
            if (this.c.e(substring) && !u(substring).equals("")) {
                return true;
            }
        }
        return this.c.e(str);
    }

    public Node s(String str) {
        c.a((Object) str);
        this.c.c(str);
        return this;
    }

    public String G() {
        return this.d;
    }

    public void t(final String str) {
        c.a((Object) str);
        a(new e(this) {
            final /* synthetic */ Node b;

            public void a(Node node, int i) {
                node.d = str;
            }

            public void b(Node node, int i) {
            }
        });
    }

    public String u(String str) {
        c.a(str);
        if (r(str)) {
            return b.a(this.d, q(str));
        }
        return "";
    }

    public Node b(int i) {
        return (Node) this.b.get(i);
    }

    public List<Node> H() {
        return Collections.unmodifiableList(this.b);
    }

    public final int I() {
        return this.b.size();
    }

    protected Node[] J() {
        return (Node[]) this.b.toArray(new Node[I()]);
    }

    public Node E() {
        return this.a;
    }

    public final Node K() {
        return this.a;
    }

    public Node L() {
        while (this.a != null) {
            this = this.a;
        }
        return this;
    }

    public Document M() {
        Node L = L();
        return L instanceof Document ? (Document) L : null;
    }

    public void N() {
        c.a(this.a);
        this.a.f(this);
    }

    public Node p(String str) {
        a(this.e, str);
        return this;
    }

    public Node d(Node node) {
        c.a((Object) node);
        c.a(this.a);
        this.a.a(this.e, node);
        return this;
    }

    public Node o(String str) {
        a(this.e + 1, str);
        return this;
    }

    private void a(int i, String str) {
        c.a((Object) str);
        c.a(this.a);
        List a = org.jsoup.parser.e.a(str, E() instanceof g ? (g) E() : null, G());
        this.a.a(i, (Node[]) a.toArray(new Node[a.size()]));
    }

    public Node n(String str) {
        int i = 0;
        c.a(str);
        List a = org.jsoup.parser.e.a(str, E() instanceof g ? (g) E() : null, G());
        Node node = (Node) a.get(0);
        if (node == null || !(node instanceof g)) {
            return null;
        }
        node = (g) node;
        g a2 = a((g) node);
        this.a.a(this, node);
        a2.a(this);
        if (a.size() <= 0) {
            return this;
        }
        while (i < a.size()) {
            Node node2 = (Node) a.get(i);
            node2.a.f(node2);
            node.a(node2);
            i++;
        }
        return this;
    }

    public Node O() {
        c.a(this.a);
        Node node = this.b.size() > 0 ? (Node) this.b.get(0) : null;
        this.a.a(this.e, J());
        N();
        return node;
    }

    private g a(g gVar) {
        List o = gVar.o();
        if (o.size() > 0) {
            return a((g) o.get(0));
        }
        return gVar;
    }

    void p() {
    }

    protected void e(Node node) {
        c.a((Object) node);
        if (this.a != null) {
            this.a.f(this);
        }
        this.a = node;
    }

    protected void a(Node node, Node node2) {
        c.a(node.a == this);
        c.a((Object) node2);
        if (node2.a != null) {
            node2.a.f(node2);
        }
        int i = node.e;
        this.b.set(i, node2);
        node2.a = this;
        node2.c(i);
        node.a = null;
    }

    protected void f(Node node) {
        c.a(node.a == this);
        int i = node.e;
        this.b.remove(i);
        a(i);
        node.a = null;
    }

    protected void a(Node... nodeArr) {
        for (Node node : nodeArr) {
            g(node);
            P();
            this.b.add(node);
            node.c(this.b.size() - 1);
        }
    }

    protected void a(int i, Node... nodeArr) {
        c.a((Object[]) nodeArr);
        P();
        for (int length = nodeArr.length - 1; length >= 0; length--) {
            Node node = nodeArr[length];
            g(node);
            this.b.add(i, node);
            a(i);
        }
    }

    protected void P() {
        if (this.b == f) {
            this.b = new NodeList(4);
        }
    }

    protected void g(Node node) {
        if (node.a != null) {
            node.a.f(node);
        }
        node.e(this);
    }

    private void a(int i) {
        while (i < this.b.size()) {
            ((Node) this.b.get(i)).c(i);
            i++;
        }
    }

    public List<Node> Q() {
        if (this.a == null) {
            return Collections.emptyList();
        }
        List<Node> list = this.a.b;
        List<Node> arrayList = new ArrayList(list.size() - 1);
        for (Node node : list) {
            if (node != this) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public Node R() {
        if (this.a == null) {
            return null;
        }
        List list = this.a.b;
        int i = this.e + 1;
        if (list.size() > i) {
            return (Node) list.get(i);
        }
        return null;
    }

    public int S() {
        return this.e;
    }

    protected void c(int i) {
        this.e = i;
    }

    public Node a(e eVar) {
        c.a((Object) eVar);
        new d(eVar).a(this);
        return this;
    }

    public String c() {
        Appendable stringBuilder = new StringBuilder(128);
        a(stringBuilder);
        return stringBuilder.toString();
    }

    protected void a(Appendable appendable) {
        new d(new a(appendable, T())).a(this);
    }

    OutputSettings T() {
        Document M = M();
        return M != null ? M.e() : new Document("").e();
    }

    public String toString() {
        return c();
    }

    protected void c(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        appendable.append("\n").append(b.a(outputSettings.f() * i));
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public Node h() {
        Node h = h(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(h);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            for (int i = 0; i < node.b.size(); i++) {
                Node h2 = ((Node) node.b.get(i)).h(node);
                node.b.set(i, h2);
                linkedList.add(h2);
            }
        }
        return h;
    }

    protected Node h(Node node) {
        try {
            Node node2 = (Node) super.clone();
            node2.a = node;
            node2.e = node == null ? 0 : this.e;
            node2.c = this.c != null ? this.c.d() : null;
            node2.d = this.d;
            node2.b = new NodeList(this.b.size());
            for (Node add : this.b) {
                node2.b.add(add);
            }
            return node2;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
