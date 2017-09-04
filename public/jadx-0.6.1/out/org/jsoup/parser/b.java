package org.jsoup.parser;

import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.c;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.d;
import org.jsoup.nodes.e;
import org.jsoup.nodes.g;
import org.jsoup.nodes.h;
import org.jsoup.nodes.i;
import org.jsoup.select.Elements;

/* compiled from: HtmlTreeBuilder */
public class b extends i {
    public static final String[] a = new String[]{"applet", "caption", "html", "table", "td", "th", "marquee", "object"};
    static final /* synthetic */ boolean b = (!b.class.desiredAssertionStatus());
    private static final String[] k = new String[]{"ol", "ul"};
    private static final String[] l = new String[]{"button"};
    private static final String[] m = new String[]{"html", "table"};
    private static final String[] n = new String[]{"optgroup", "option"};
    private static final String[] o = new String[]{"dd", "dt", "li", "option", "optgroup", "p", "rp", "rt"};
    private static final String[] p = new String[]{"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", "br", "button", "caption", "center", "col", "colgroup", "command", "dd", "details", "dir", "div", "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr", "html", "iframe", SocialConstants.PARAM_IMG_URL, "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", "p", "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
    private boolean A = false;
    private boolean B = false;
    private String[] C = new String[]{null};
    private HtmlTreeBuilderState q;
    private HtmlTreeBuilderState r;
    private boolean s = false;
    private g t;
    private h u;
    private g v;
    private ArrayList<g> w = new ArrayList();
    private List<String> x = new ArrayList();
    private e y = new e();
    private boolean z = true;

    b() {
    }

    d a() {
        return d.a;
    }

    Document a(String str, String str2, ParseErrorList parseErrorList, d dVar) {
        this.q = HtmlTreeBuilderState.Initial;
        this.s = false;
        return super.a(str, str2, parseErrorList, dVar);
    }

    List<Node> a(String str, g gVar, String str2, ParseErrorList parseErrorList, d dVar) {
        this.q = HtmlTreeBuilderState.Initial;
        b(str, str2, parseErrorList, dVar);
        this.v = gVar;
        this.B = true;
        g gVar2 = null;
        if (gVar != null) {
            if (gVar.M() != null) {
                this.e.a(gVar.M().f());
            }
            String i = gVar.i();
            if (org.jsoup.helper.b.a(i, "title", "textarea")) {
                this.d.a(TokeniserState.Rcdata);
            } else {
                if (org.jsoup.helper.b.a(i, "iframe", "noembed", "noframes", "style", "xmp")) {
                    this.d.a(TokeniserState.Rawtext);
                } else if (i.equals("script")) {
                    this.d.a(TokeniserState.ScriptData);
                } else if (i.equals("noscript")) {
                    this.d.a(TokeniserState.Data);
                } else if (i.equals("plaintext")) {
                    this.d.a(TokeniserState.Data);
                } else {
                    this.d.a(TokeniserState.Data);
                }
            }
            Node gVar3 = new g(f.a("html", dVar), str2);
            this.e.a(gVar3);
            this.f.add(gVar3);
            n();
            Elements n = gVar.n();
            n.add(0, gVar);
            Iterator it = n.iterator();
            while (it.hasNext()) {
                gVar2 = (g) it.next();
                if (gVar2 instanceof h) {
                    this.u = (h) gVar2;
                    gVar2 = gVar3;
                    break;
                }
            }
            Node node = gVar3;
        }
        z();
        if (gVar == null || gVar2 == null) {
            return this.e.H();
        }
        return gVar2.H();
    }

    protected boolean a(Token token) {
        this.h = token;
        return this.q.process(token, this);
    }

    boolean a(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        this.h = token;
        return htmlTreeBuilderState.process(token, this);
    }

    void a(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.q = htmlTreeBuilderState;
    }

    HtmlTreeBuilderState b() {
        return this.q;
    }

    void c() {
        this.r = this.q;
    }

    HtmlTreeBuilderState d() {
        return this.r;
    }

    void a(boolean z) {
        this.z = z;
    }

    boolean e() {
        return this.z;
    }

    Document f() {
        return this.e;
    }

    String g() {
        return this.g;
    }

    void a(g gVar) {
        if (!this.s) {
            String u = gVar.u("href");
            if (u.length() != 0) {
                this.g = u;
                this.s = true;
                this.e.t(u);
            }
        }
    }

    boolean h() {
        return this.B;
    }

    void b(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.i.canAddError()) {
            this.i.add(new c(this.c.a(), "Unexpected token [%s] when in state [%s]", this.h.a(), htmlTreeBuilderState));
        }
    }

    g a(f fVar) {
        if (fVar.s()) {
            g b = b(fVar);
            this.f.add(b);
            this.d.a(TokeniserState.Data);
            this.d.a(this.y.n().a(b.i()));
            return b;
        }
        b = new g(f.a(fVar.q(), this.j), this.g, this.j.a(fVar.e));
        b(b);
        return b;
    }

    g a(String str) {
        g gVar = new g(f.a(str, this.j), this.g);
        b(gVar);
        return gVar;
    }

    void b(g gVar) {
        b((Node) gVar);
        this.f.add(gVar);
    }

    g b(f fVar) {
        f a = f.a(fVar.q(), this.j);
        Node gVar = new g(a, this.g, fVar.e);
        b(gVar);
        if (fVar.s()) {
            if (!a.f()) {
                a.i();
                this.d.b();
            } else if (a.e()) {
                this.d.b();
            }
        }
        return gVar;
    }

    h a(f fVar, boolean z) {
        Node hVar = new h(f.a(fVar.q(), this.j), this.g, fVar.e);
        a((h) hVar);
        b(hVar);
        if (z) {
            this.f.add(hVar);
        }
        return hVar;
    }

    void a(b bVar) {
        b(new d(bVar.n(), this.g));
    }

    void a(a aVar) {
        Node eVar;
        String i = A().i();
        if (i.equals("script") || i.equals("style")) {
            eVar = new e(aVar.n(), this.g);
        } else {
            eVar = new i(aVar.n(), this.g);
        }
        A().a(eVar);
    }

    private void b(Node node) {
        if (this.f.size() == 0) {
            this.e.a(node);
        } else if (p()) {
            a(node);
        } else {
            A().a(node);
        }
        if ((node instanceof g) && ((g) node).j().h() && this.u != null) {
            this.u.b((g) node);
        }
    }

    g i() {
        return (g) this.f.remove(this.f.size() - 1);
    }

    void c(g gVar) {
        this.f.add(gVar);
    }

    ArrayList<g> j() {
        return this.f;
    }

    boolean d(g gVar) {
        return a(this.f, gVar);
    }

    private boolean a(ArrayList<g> arrayList, g gVar) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((g) arrayList.get(size)) == gVar) {
                return true;
            }
        }
        return false;
    }

    g b(String str) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            g gVar = (g) this.f.get(size);
            if (gVar.a().equals(str)) {
                return gVar;
            }
        }
        return null;
    }

    boolean e(g gVar) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            if (((g) this.f.get(size)) == gVar) {
                this.f.remove(size);
                return true;
            }
        }
        return false;
    }

    void c(String str) {
        int size = this.f.size() - 1;
        while (size >= 0) {
            g gVar = (g) this.f.get(size);
            this.f.remove(size);
            if (!gVar.a().equals(str)) {
                size--;
            } else {
                return;
            }
        }
    }

    void a(String... strArr) {
        int size = this.f.size() - 1;
        while (size >= 0) {
            g gVar = (g) this.f.get(size);
            this.f.remove(size);
            if (!org.jsoup.helper.b.a(gVar.a(), strArr)) {
                size--;
            } else {
                return;
            }
        }
    }

    void d(String str) {
        int size = this.f.size() - 1;
        while (size >= 0 && !((g) this.f.get(size)).a().equals(str)) {
            this.f.remove(size);
            size--;
        }
    }

    void k() {
        c("table");
    }

    void l() {
        c("tbody", "tfoot", "thead");
    }

    void m() {
        c("tr");
    }

    private void c(String... strArr) {
        int size = this.f.size() - 1;
        while (size >= 0) {
            g gVar = (g) this.f.get(size);
            if (!org.jsoup.helper.b.a(gVar.a(), strArr) && !gVar.a().equals("html")) {
                this.f.remove(size);
                size--;
            } else {
                return;
            }
        }
    }

    g f(g gVar) {
        if (b || d(gVar)) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                if (((g) this.f.get(size)) == gVar) {
                    return (g) this.f.get(size - 1);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    void a(g gVar, g gVar2) {
        int lastIndexOf = this.f.lastIndexOf(gVar);
        c.a(lastIndexOf != -1);
        this.f.add(lastIndexOf + 1, gVar2);
    }

    void b(g gVar, g gVar2) {
        a(this.f, gVar, gVar2);
    }

    private void a(ArrayList<g> arrayList, g gVar, g gVar2) {
        int lastIndexOf = arrayList.lastIndexOf(gVar);
        c.a(lastIndexOf != -1);
        arrayList.set(lastIndexOf, gVar2);
    }

    void n() {
        Object obj = null;
        int size = this.f.size() - 1;
        while (size >= 0) {
            g gVar = (g) this.f.get(size);
            if (size == 0) {
                obj = 1;
                gVar = this.v;
            }
            String a = gVar.a();
            if ("select".equals(a)) {
                a(HtmlTreeBuilderState.InSelect);
                return;
            } else if ("td".equals(a) || ("th".equals(a) && obj == null)) {
                a(HtmlTreeBuilderState.InCell);
                return;
            } else if ("tr".equals(a)) {
                a(HtmlTreeBuilderState.InRow);
                return;
            } else if ("tbody".equals(a) || "thead".equals(a) || "tfoot".equals(a)) {
                a(HtmlTreeBuilderState.InTableBody);
                return;
            } else if ("caption".equals(a)) {
                a(HtmlTreeBuilderState.InCaption);
                return;
            } else if ("colgroup".equals(a)) {
                a(HtmlTreeBuilderState.InColumnGroup);
                return;
            } else if ("table".equals(a)) {
                a(HtmlTreeBuilderState.InTable);
                return;
            } else if ("head".equals(a)) {
                a(HtmlTreeBuilderState.InBody);
                return;
            } else if ("body".equals(a)) {
                a(HtmlTreeBuilderState.InBody);
                return;
            } else if ("frameset".equals(a)) {
                a(HtmlTreeBuilderState.InFrameset);
                return;
            } else if ("html".equals(a)) {
                a(HtmlTreeBuilderState.BeforeHead);
                return;
            } else if (obj != null) {
                a(HtmlTreeBuilderState.InBody);
                return;
            } else {
                size--;
            }
        }
    }

    private boolean a(String str, String[] strArr, String[] strArr2) {
        this.C[0] = str;
        return a(this.C, strArr, strArr2);
    }

    private boolean a(String[] strArr, String[] strArr2, String[] strArr3) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            String a = ((g) this.f.get(size)).a();
            if (org.jsoup.helper.b.a(a, strArr)) {
                return true;
            }
            if (org.jsoup.helper.b.a(a, strArr2)) {
                return false;
            }
            if (strArr3 != null && org.jsoup.helper.b.a(a, strArr3)) {
                return false;
            }
        }
        c.b("Should not be reachable");
        return false;
    }

    boolean b(String[] strArr) {
        return a(strArr, a, null);
    }

    boolean e(String str) {
        return a(str, null);
    }

    boolean a(String str, String[] strArr) {
        return a(str, a, strArr);
    }

    boolean f(String str) {
        return a(str, k);
    }

    boolean g(String str) {
        return a(str, l);
    }

    boolean h(String str) {
        return a(str, m, null);
    }

    boolean i(String str) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            String a = ((g) this.f.get(size)).a();
            if (a.equals(str)) {
                return true;
            }
            if (!org.jsoup.helper.b.a(a, n)) {
                return false;
            }
        }
        c.b("Should not be reachable");
        return false;
    }

    void g(g gVar) {
        this.t = gVar;
    }

    g o() {
        return this.t;
    }

    boolean p() {
        return this.A;
    }

    void b(boolean z) {
        this.A = z;
    }

    h q() {
        return this.u;
    }

    void a(h hVar) {
        this.u = hVar;
    }

    void r() {
        this.x = new ArrayList();
    }

    List<String> s() {
        return this.x;
    }

    void j(String str) {
        while (str != null && !A().a().equals(str) && org.jsoup.helper.b.a(A().a(), o)) {
            i();
        }
    }

    void t() {
        j(null);
    }

    boolean h(g gVar) {
        return org.jsoup.helper.b.a(gVar.a(), p);
    }

    g u() {
        return this.w.size() > 0 ? (g) this.w.get(this.w.size() - 1) : null;
    }

    g v() {
        int size = this.w.size();
        if (size > 0) {
            return (g) this.w.remove(size - 1);
        }
        return null;
    }

    void i(g gVar) {
        int i = 0;
        int size = this.w.size() - 1;
        while (size >= 0) {
            g gVar2 = (g) this.w.get(size);
            if (gVar2 == null) {
                break;
            }
            int i2;
            if (d(gVar, gVar2)) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            if (i2 == 3) {
                this.w.remove(size);
                break;
            } else {
                size--;
                i = i2;
            }
        }
        this.w.add(gVar);
    }

    private boolean d(g gVar, g gVar2) {
        return gVar.a().equals(gVar2.a()) && gVar.F().equals(gVar2.F());
    }

    void w() {
        g u = u();
        if (u != null && !d(u)) {
            Object obj;
            int i;
            g gVar;
            int i2;
            int size = this.w.size();
            int i3 = size - 1;
            while (i3 != 0) {
                i3--;
                g gVar2 = (g) this.w.get(i3);
                if (gVar2 == null) {
                    obj = null;
                    i = i3;
                    gVar = gVar2;
                    i2 = i;
                    break;
                } else if (d(gVar2)) {
                    obj = null;
                    i = i3;
                    gVar = gVar2;
                    i2 = i;
                    break;
                } else {
                    u = gVar2;
                }
            }
            i2 = i3;
            gVar = u;
            obj = 1;
            while (true) {
                if (obj == null) {
                    i3 = i2 + 1;
                    i = i3;
                    gVar = (g) this.w.get(i3);
                    i2 = i;
                }
                c.a(r1);
                u = a(r1.a());
                u.F().a(r1.F());
                this.w.set(i2, u);
                if (i2 != size - 1) {
                    obj = null;
                } else {
                    return;
                }
            }
        }
    }

    void x() {
        while (!this.w.isEmpty()) {
            if (v() == null) {
                return;
            }
        }
    }

    void j(g gVar) {
        for (int size = this.w.size() - 1; size >= 0; size--) {
            if (((g) this.w.get(size)) == gVar) {
                this.w.remove(size);
                return;
            }
        }
    }

    boolean k(g gVar) {
        return a(this.w, gVar);
    }

    g k(String str) {
        int size = this.w.size() - 1;
        while (size >= 0) {
            g gVar = (g) this.w.get(size);
            if (gVar == null) {
                break;
            } else if (gVar.a().equals(str)) {
                return gVar;
            } else {
                size--;
            }
        }
        return null;
    }

    void c(g gVar, g gVar2) {
        a(this.w, gVar, gVar2);
    }

    void y() {
        this.w.add(null);
    }

    void a(Node node) {
        g gVar;
        int i;
        g b = b("table");
        if (b == null) {
            gVar = (g) this.f.get(0);
            i = 0;
        } else if (b.m() != null) {
            gVar = b.m();
            i = 1;
        } else {
            gVar = f(b);
            i = 0;
        }
        if (i != 0) {
            c.a((Object) b);
            b.b(node);
            return;
        }
        gVar.a(node);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.h + ", state=" + this.q + ", currentElement=" + A() + '}';
    }
}
