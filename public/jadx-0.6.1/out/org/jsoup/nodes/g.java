package org.jsoup.nodes;

import com.tencent.mm.performance.WxPerformanceHandle;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.jsoup.helper.b;
import org.jsoup.helper.c;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.jsoup.parser.d;
import org.jsoup.parser.e;
import org.jsoup.parser.f;
import org.jsoup.select.Elements;
import org.jsoup.select.a;

/* compiled from: Element */
public class g extends Node {
    private static final Pattern h = Pattern.compile("\\s+");
    private f f;
    private WeakReference<List<g>> g;

    public /* synthetic */ Node E() {
        return m();
    }

    public /* synthetic */ Node b(String str, String str2) {
        return a(str, str2);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    public /* synthetic */ Node d(Node node) {
        return b(node);
    }

    public /* synthetic */ Node h() {
        return g();
    }

    public /* synthetic */ Node n(String str) {
        return g(str);
    }

    public /* synthetic */ Node o(String str) {
        return f(str);
    }

    public /* synthetic */ Node p(String str) {
        return e(str);
    }

    public g(f fVar, String str, b bVar) {
        super(str, bVar);
        c.a((Object) fVar);
        this.f = fVar;
    }

    public g(f fVar, String str) {
        this(fVar, str, new b());
    }

    public String a() {
        return this.f.a();
    }

    public String i() {
        return this.f.a();
    }

    public g b(String str) {
        c.a(str, "Tag name must not be empty.");
        this.f = f.a(str, d.b);
        return this;
    }

    public f j() {
        return this.f;
    }

    public boolean k() {
        return this.f.b();
    }

    public String l() {
        return this.c.b("id");
    }

    public g a(String str, String str2) {
        super.b(str, str2);
        return this;
    }

    public final g m() {
        return (g) this.a;
    }

    public Elements n() {
        Elements elements = new Elements();
        a(this, elements);
        return elements;
    }

    private static void a(g gVar, Elements elements) {
        g m = gVar.m();
        if (m != null && !m.i().equals("#root")) {
            elements.add(m);
            a(m, elements);
        }
    }

    public g a(int i) {
        return (g) b().get(i);
    }

    public Elements o() {
        return new Elements(b());
    }

    private List<g> b() {
        if (this.g != null) {
            List<g> list = (List) this.g.get();
            if (list != null) {
                return list;
            }
        }
        int size = this.b.size();
        List<g> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Node node = (Node) this.b.get(i);
            if (node instanceof g) {
                arrayList.add((g) node);
            }
        }
        this.g = new WeakReference(arrayList);
        return arrayList;
    }

    void p() {
        super.p();
        this.g = null;
    }

    public boolean a(org.jsoup.select.c cVar) {
        return cVar.a((g) L(), this);
    }

    public g a(Node node) {
        c.a((Object) node);
        g(node);
        P();
        this.b.add(node);
        node.c(this.b.size() - 1);
        return this;
    }

    public g c(String str) {
        c.a((Object) str);
        List a = e.a(str, this, G());
        a((Node[]) a.toArray(new Node[a.size()]));
        return this;
    }

    public g d(String str) {
        c.a((Object) str);
        List a = e.a(str, this, G());
        a(0, (Node[]) a.toArray(new Node[a.size()]));
        return this;
    }

    public g e(String str) {
        return (g) super.p(str);
    }

    public g b(Node node) {
        return (g) super.d(node);
    }

    public g f(String str) {
        return (g) super.o(str);
    }

    public g q() {
        this.b.clear();
        return this;
    }

    public g g(String str) {
        return (g) super.n(str);
    }

    public Elements r() {
        if (this.a == null) {
            return new Elements(0);
        }
        List<g> b = m().b();
        Elements elements = new Elements(b.size() - 1);
        for (g gVar : b) {
            if (gVar != this) {
                elements.add(gVar);
            }
        }
        return elements;
    }

    public g s() {
        if (this.a == null) {
            return null;
        }
        List b = m().b();
        Object valueOf = Integer.valueOf(a(this, b));
        c.a(valueOf);
        if (b.size() > valueOf.intValue() + 1) {
            return (g) b.get(valueOf.intValue() + 1);
        }
        return null;
    }

    public g t() {
        if (this.a == null) {
            return null;
        }
        List b = m().b();
        Object valueOf = Integer.valueOf(a(this, b));
        c.a(valueOf);
        if (valueOf.intValue() > 0) {
            return (g) b.get(valueOf.intValue() - 1);
        }
        return null;
    }

    public int u() {
        if (m() == null) {
            return 0;
        }
        return a(this, m().b());
    }

    private static <E extends g> int a(g gVar, List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == gVar) {
                return i;
            }
        }
        return 0;
    }

    public Elements v() {
        return a.a(new org.jsoup.select.c.a(), this);
    }

    public String w() {
        final StringBuilder stringBuilder = new StringBuilder();
        new org.jsoup.select.d(new org.jsoup.select.e(this) {
            final /* synthetic */ g b;

            public void a(Node node, int i) {
                if (node instanceof i) {
                    g.b(stringBuilder, (i) node);
                } else if (node instanceof g) {
                    g gVar = (g) node;
                    if (stringBuilder.length() <= 0) {
                        return;
                    }
                    if ((gVar.k() || gVar.f.a().equals("br")) && !i.a(stringBuilder)) {
                        stringBuilder.append(" ");
                    }
                }
            }

            public void b(Node node, int i) {
            }
        }).a(this);
        return stringBuilder.toString().trim();
    }

    public String x() {
        StringBuilder stringBuilder = new StringBuilder();
        a(stringBuilder);
        return stringBuilder.toString().trim();
    }

    private void a(StringBuilder stringBuilder) {
        for (Node node : this.b) {
            if (node instanceof i) {
                b(stringBuilder, (i) node);
            } else if (node instanceof g) {
                a((g) node, stringBuilder);
            }
        }
    }

    private static void b(StringBuilder stringBuilder, i iVar) {
        String b = iVar.b();
        if (c(iVar.a)) {
            stringBuilder.append(b);
        } else {
            b.a(stringBuilder, b, i.a(stringBuilder));
        }
    }

    private static void a(g gVar, StringBuilder stringBuilder) {
        if (gVar.f.a().equals("br") && !i.a(stringBuilder)) {
            stringBuilder.append(" ");
        }
    }

    static boolean c(Node node) {
        if (node == null || !(node instanceof g)) {
            return false;
        }
        g gVar = (g) node;
        if (gVar.f.g() || (gVar.m() != null && gVar.m().f.g())) {
            return true;
        }
        return false;
    }

    public g a(String str) {
        c.a((Object) str);
        q();
        a(new i(str, this.d));
        return this;
    }

    public boolean y() {
        for (Node node : this.b) {
            if (node instanceof i) {
                if (!((i) node).d()) {
                    return true;
                }
            } else if ((node instanceof g) && ((g) node).y()) {
                return true;
            }
        }
        return false;
    }

    public String z() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : this.b) {
            if (node instanceof e) {
                stringBuilder.append(((e) node).b());
            } else if (node instanceof d) {
                stringBuilder.append(((d) node).b());
            } else if (node instanceof g) {
                stringBuilder.append(((g) node).z());
            }
        }
        return stringBuilder.toString();
    }

    public String A() {
        return q(WxPerformanceHandle.MESSAGE_CLASS).trim();
    }

    public Set<String> B() {
        Set<String> linkedHashSet = new LinkedHashSet(Arrays.asList(h.split(A())));
        linkedHashSet.remove("");
        return linkedHashSet;
    }

    public g a(Set<String> set) {
        c.a((Object) set);
        this.c.a(WxPerformanceHandle.MESSAGE_CLASS, b.a((Collection) set, " "));
        return this;
    }

    public boolean h(String str) {
        String b = this.c.b(WxPerformanceHandle.MESSAGE_CLASS);
        int length = b.length();
        int length2 = str.length();
        if (length == 0 || length < length2) {
            return false;
        }
        if (length == length2) {
            return str.equalsIgnoreCase(b);
        }
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            if (Character.isWhitespace(b.charAt(i2))) {
                if (!z) {
                    continue;
                } else if (i2 - i == length2 && b.regionMatches(true, i, str, 0, length2)) {
                    return true;
                } else {
                    z = false;
                }
            } else if (!z) {
                i = i2;
                z = true;
            }
        }
        if (z && length - i == length2) {
            return b.regionMatches(true, i, str, 0, length2);
        }
        return false;
    }

    public g i(String str) {
        c.a((Object) str);
        Set B = B();
        B.add(str);
        a(B);
        return this;
    }

    public g j(String str) {
        c.a((Object) str);
        Set B = B();
        B.remove(str);
        a(B);
        return this;
    }

    public g k(String str) {
        c.a((Object) str);
        Set B = B();
        if (B.contains(str)) {
            B.remove(str);
        } else {
            B.add(str);
        }
        a(B);
        return this;
    }

    public String C() {
        if (i().equals("textarea")) {
            return w();
        }
        return q("value");
    }

    public g l(String str) {
        if (i().equals("textarea")) {
            a(str);
        } else {
            a("value", str);
        }
        return this;
    }

    void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        if (outputSettings.d() && (this.f.c() || ((m() != null && m().j().c()) || outputSettings.e()))) {
            if (!(appendable instanceof StringBuilder)) {
                c(appendable, i, outputSettings);
            } else if (((StringBuilder) appendable).length() > 0) {
                c(appendable, i, outputSettings);
            }
        }
        appendable.append("<").append(i());
        this.c.a(appendable, outputSettings);
        if (!this.b.isEmpty() || !this.f.e()) {
            appendable.append(">");
        } else if (outputSettings.c() == Syntax.html && this.f.d()) {
            appendable.append('>');
        } else {
            appendable.append(" />");
        }
    }

    void b(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        if (!this.b.isEmpty() || !this.f.e()) {
            if (outputSettings.d() && !this.b.isEmpty() && (this.f.c() || (outputSettings.e() && (this.b.size() > 1 || (this.b.size() == 1 && !(this.b.get(0) instanceof i)))))) {
                c(appendable, i, outputSettings);
            }
            appendable.append("</").append(i()).append(">");
        }
    }

    public String D() {
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder);
        return T().d() ? stringBuilder.toString().trim() : stringBuilder.toString();
    }

    private void b(StringBuilder stringBuilder) {
        for (Node a : this.b) {
            a.a((Appendable) stringBuilder);
        }
    }

    public g m(String str) {
        q();
        c(str);
        return this;
    }

    public String toString() {
        return c();
    }

    public g g() {
        return (g) super.h();
    }
}
