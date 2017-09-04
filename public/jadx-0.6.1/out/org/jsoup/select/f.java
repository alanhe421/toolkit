package org.jsoup.select;

import com.dynamicload.Lib.DLConstants;
import com.tencent.qalsdk.sdk.v;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.a.a;
import org.jsoup.helper.c;
import org.jsoup.parser.g;
import org.jsoup.select.Selector.SelectorParseException;
import org.jsoup.select.c.aa;
import org.jsoup.select.c.ab;
import org.jsoup.select.c.ac;
import org.jsoup.select.c.ad;
import org.jsoup.select.c.ae;
import org.jsoup.select.c.af;
import org.jsoup.select.c.ag;
import org.jsoup.select.c.ah;
import org.jsoup.select.c.ai;
import org.jsoup.select.c.aj;
import org.jsoup.select.c.b;
import org.jsoup.select.c.d;
import org.jsoup.select.c.e;
import org.jsoup.select.c.h;
import org.jsoup.select.c.i;
import org.jsoup.select.c.j;
import org.jsoup.select.c.k;
import org.jsoup.select.c.l;
import org.jsoup.select.c.m;
import org.jsoup.select.c.n;
import org.jsoup.select.c.p;
import org.jsoup.select.c.q;
import org.jsoup.select.c.s;
import org.jsoup.select.c.t;
import org.jsoup.select.c.u;
import org.jsoup.select.c.w;
import org.jsoup.select.c.x;
import org.jsoup.select.c.y;
import org.jsoup.select.c.z;

/* compiled from: QueryParser */
public class f {
    private static final String[] a = new String[]{",", ">", "+", "~", " "};
    private static final String[] b = new String[]{"=", "!=", "^=", "$=", "*=", "~="};
    private static final Pattern f = Pattern.compile("((\\+|-)?(\\d+)?)n(\\s*(\\+|-)?\\s*\\d+)?", 2);
    private static final Pattern g = Pattern.compile("(\\+|-)?(\\d+)");
    private g c;
    private String d;
    private List<c> e = new ArrayList();

    private f(String str) {
        this.d = str;
        this.c = new g(str);
    }

    public static c a(String str) {
        try {
            return new f(str).a();
        } catch (IllegalArgumentException e) {
            throw new SelectorParseException(e.getMessage(), new Object[0]);
        }
    }

    c a() {
        this.c.e();
        if (this.c.a(a)) {
            this.e.add(new g());
            a(this.c.d());
        } else {
            c();
        }
        while (!this.c.a()) {
            boolean e = this.c.e();
            if (this.c.a(a)) {
                a(this.c.d());
            } else if (e) {
                a(' ');
            } else {
                c();
            }
        }
        if (this.e.size() == 1) {
            return (c) this.e.get(0);
        }
        return new a(this.e);
    }

    private void a(char c) {
        c cVar;
        int i;
        c cVar2;
        Object obj;
        c aVar;
        this.c.e();
        c a = a(b());
        if (this.e.size() == 1) {
            cVar = (c) this.e.get(0);
            if (!(cVar instanceof b) || c == ',') {
                i = 0;
                cVar2 = cVar;
            } else {
                i = 1;
                obj = cVar;
                cVar = ((b) cVar).a();
            }
        } else {
            cVar = new a(this.e);
            i = 0;
            cVar2 = cVar;
        }
        this.e.clear();
        if (c == '>') {
            aVar = new a(a, new b(cVar));
        } else if (c == ' ') {
            aVar = new a(a, new e(cVar));
        } else if (c == '+') {
            aVar = new a(a, new c(cVar));
        } else if (c == '~') {
            aVar = new a(a, new f(cVar));
        } else if (c == ',') {
            if (cVar instanceof b) {
                cVar = (b) cVar;
                cVar.b(a);
            } else {
                aVar = new b();
                aVar.b(cVar);
                aVar.b(a);
                cVar = aVar;
            }
            aVar = cVar;
        } else {
            throw new SelectorParseException("Unknown combinator: " + c, new Object[0]);
        }
        if (i != 0) {
            ((b) obj).a(aVar);
        } else {
            cVar2 = aVar;
        }
        this.e.add(obj);
    }

    private String b() {
        StringBuilder stringBuilder = new StringBuilder();
        while (!this.c.a()) {
            if (this.c.a("(")) {
                stringBuilder.append("(").append(this.c.a('(', ')')).append(")");
            } else if (this.c.a("[")) {
                stringBuilder.append("[").append(this.c.a('[', ']')).append("]");
            } else if (this.c.a(a)) {
                break;
            } else {
                stringBuilder.append(this.c.d());
            }
        }
        return stringBuilder.toString();
    }

    private void c() {
        if (this.c.b("#")) {
            d();
        } else if (this.c.b(".")) {
            e();
        } else if (this.c.c() || this.c.a("*|")) {
            f();
        } else if (this.c.a("[")) {
            g();
        } else if (this.c.b(v.n)) {
            h();
        } else if (this.c.b(":lt(")) {
            i();
        } else if (this.c.b(":gt(")) {
            j();
        } else if (this.c.b(":eq(")) {
            k();
        } else if (this.c.a(":has(")) {
            m();
        } else if (this.c.a(":contains(")) {
            a(false);
        } else if (this.c.a(":containsOwn(")) {
            a(true);
        } else if (this.c.a(":containsData(")) {
            n();
        } else if (this.c.a(":matches(")) {
            b(false);
        } else if (this.c.a(":matchesOwn(")) {
            b(true);
        } else if (this.c.a(":not(")) {
            o();
        } else if (this.c.b(":nth-child(")) {
            a(false, false);
        } else if (this.c.b(":nth-last-child(")) {
            a(true, false);
        } else if (this.c.b(":nth-of-type(")) {
            a(false, true);
        } else if (this.c.b(":nth-last-of-type(")) {
            a(true, true);
        } else if (this.c.b(":first-child")) {
            this.e.add(new c.v());
        } else if (this.c.b(":last-child")) {
            this.e.add(new x());
        } else if (this.c.b(":first-of-type")) {
            this.e.add(new w());
        } else if (this.c.b(":last-of-type")) {
            this.e.add(new y());
        } else if (this.c.b(":only-child")) {
            this.e.add(new ad());
        } else if (this.c.b(":only-of-type")) {
            this.e.add(new ae());
        } else if (this.c.b(":empty")) {
            this.e.add(new u());
        } else if (this.c.b(":root")) {
            this.e.add(new af());
        } else {
            throw new SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.d, this.c.h());
        }
    }

    private void d() {
        String g = this.c.g();
        c.a(g);
        this.e.add(new p(g));
    }

    private void e() {
        String g = this.c.g();
        c.a(g);
        this.e.add(new k(g.trim()));
    }

    private void f() {
        String f = this.c.f();
        c.a(f);
        if (f.startsWith("*|")) {
            this.e.add(new b(new ai(a.b(f)), new aj(a.b(f.replace("*|", ":")))));
            return;
        }
        if (f.contains(DLConstants.DEPENDENCY_PACKAGE_DIV)) {
            f = f.replace(DLConstants.DEPENDENCY_PACKAGE_DIV, ":");
        }
        this.e.add(new ai(f.trim()));
    }

    private void g() {
        g gVar = new g(this.c.a('[', ']'));
        String b = gVar.b(b);
        c.a(b);
        gVar.e();
        if (gVar.a()) {
            if (b.startsWith("^")) {
                this.e.add(new d(b.substring(1)));
            } else {
                this.e.add(new b(b));
            }
        } else if (gVar.b("=")) {
            this.e.add(new e(b, gVar.h()));
        } else if (gVar.b("!=")) {
            this.e.add(new i(b, gVar.h()));
        } else if (gVar.b("^=")) {
            this.e.add(new j(b, gVar.h()));
        } else if (gVar.b("$=")) {
            this.e.add(new c.g(b, gVar.h()));
        } else if (gVar.b("*=")) {
            this.e.add(new org.jsoup.select.c.f(b, gVar.h()));
        } else if (gVar.b("~=")) {
            this.e.add(new h(b, Pattern.compile(gVar.h())));
        } else {
            throw new SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.d, gVar.h());
        }
    }

    private void h() {
        this.e.add(new c.a());
    }

    private void i() {
        this.e.add(new t(l()));
    }

    private void j() {
        this.e.add(new s(l()));
    }

    private void k() {
        this.e.add(new q(l()));
    }

    private void a(boolean z, boolean z2) {
        int i = 1;
        int i2 = 0;
        CharSequence b = a.b(this.c.e(")"));
        Matcher matcher = f.matcher(b);
        Matcher matcher2 = g.matcher(b);
        if ("odd".equals(b)) {
            i2 = 1;
            i = 2;
        } else if ("even".equals(b)) {
            i = 2;
        } else if (matcher.matches()) {
            if (matcher.group(3) != null) {
                i = Integer.parseInt(matcher.group(1).replaceFirst("^\\+", ""));
            }
            if (matcher.group(4) != null) {
                i2 = Integer.parseInt(matcher.group(4).replaceFirst("^\\+", ""));
            }
        } else if (matcher2.matches()) {
            i = 0;
            i2 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
        } else {
            throw new SelectorParseException("Could not parse nth-index '%s': unexpected format", b);
        }
        if (z2) {
            if (z) {
                this.e.add(new ab(i, i2));
            } else {
                this.e.add(new ac(i, i2));
            }
        } else if (z) {
            this.e.add(new aa(i, i2));
        } else {
            this.e.add(new z(i, i2));
        }
    }

    private int l() {
        String trim = this.c.e(")").trim();
        c.a(org.jsoup.helper.b.b(trim), "Index must be numeric");
        return Integer.parseInt(trim);
    }

    private void m() {
        this.c.c(":has");
        String a = this.c.a('(', ')');
        c.a(a, ":has(el) subselect must not be empty");
        this.e.add(new a(a(a)));
    }

    private void a(boolean z) {
        this.c.c(z ? ":containsOwn" : ":contains");
        String f = g.f(this.c.a('(', ')'));
        c.a(f, ":contains(text) query must not be empty");
        if (z) {
            this.e.add(new m(f));
        } else {
            this.e.add(new n(f));
        }
    }

    private void n() {
        this.c.c(":containsData");
        String f = g.f(this.c.a('(', ')'));
        c.a(f, ":containsData(text) query must not be empty");
        this.e.add(new l(f));
    }

    private void b(boolean z) {
        this.c.c(z ? ":matchesOwn" : ":matches");
        String a = this.c.a('(', ')');
        c.a(a, ":matches(regex) query must not be empty");
        if (z) {
            this.e.add(new ah(Pattern.compile(a)));
        } else {
            this.e.add(new ag(Pattern.compile(a)));
        }
    }

    private void o() {
        this.c.c(":not");
        String a = this.c.a('(', ')');
        c.a(a, ":not(selector) subselect must not be empty");
        this.e.add(new d(a(a)));
    }
}
