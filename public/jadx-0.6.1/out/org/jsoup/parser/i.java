package org.jsoup.parser;

import java.util.ArrayList;
import org.jsoup.helper.c;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.b;
import org.jsoup.nodes.g;

/* compiled from: TreeBuilder */
abstract class i {
    private f a = new f();
    private e b = new e();
    a c;
    h d;
    protected Document e;
    protected ArrayList<g> f;
    protected String g;
    protected Token h;
    protected ParseErrorList i;
    protected d j;

    abstract d a();

    protected abstract boolean a(Token token);

    i() {
    }

    protected void b(String str, String str2, ParseErrorList parseErrorList, d dVar) {
        c.a((Object) str, "String input must not be null");
        c.a((Object) str2, "BaseURI must not be null");
        this.e = new Document(str2);
        this.j = dVar;
        this.c = new a(str);
        this.i = parseErrorList;
        this.d = new h(this.c, parseErrorList);
        this.f = new ArrayList(32);
        this.g = str2;
    }

    Document a(String str, String str2, ParseErrorList parseErrorList, d dVar) {
        b(str, str2, parseErrorList, dVar);
        z();
        return this.e;
    }

    protected void z() {
        Token a;
        do {
            a = this.d.a();
            a(a);
            a.b();
        } while (a.a != TokenType.EOF);
    }

    protected boolean l(String str) {
        if (this.h == this.a) {
            return a(new f().a(str));
        }
        return a(this.a.n().a(str));
    }

    public boolean a(String str, b bVar) {
        if (this.h == this.a) {
            return a(new f().a(str, bVar));
        }
        this.a.n();
        this.a.a(str, bVar);
        return a(this.a);
    }

    protected boolean m(String str) {
        if (this.h == this.b) {
            return a(new e().a(str));
        }
        return a(this.b.n().a(str));
    }

    protected g A() {
        int size = this.f.size();
        return size > 0 ? (g) this.f.get(size - 1) : null;
    }
}
