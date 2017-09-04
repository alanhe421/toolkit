package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.b;
import org.jsoup.nodes.Document.OutputSettings;

/* compiled from: TextNode */
public class i extends Node {
    String f;

    public i(String str, String str2) {
        this.d = str2;
        this.f = str;
    }

    public String a() {
        return "#text";
    }

    public String b() {
        return this.c == null ? this.f : this.c.a("text");
    }

    public boolean d() {
        return b.a(b());
    }

    void a(Appendable appendable, int i, OutputSettings outputSettings) throws IOException {
        boolean z;
        if (outputSettings.d() && ((S() == 0 && (this.a instanceof g) && ((g) this.a).j().c() && !d()) || (outputSettings.e() && Q().size() > 0 && !d()))) {
            c(appendable, i, outputSettings);
        }
        if (outputSettings.d() && (E() instanceof g) && !g.c(E())) {
            z = true;
        } else {
            z = false;
        }
        Entities.a(appendable, b(), outputSettings, false, z, false);
    }

    void b(Appendable appendable, int i, OutputSettings outputSettings) {
    }

    public String toString() {
        return c();
    }

    static boolean a(StringBuilder stringBuilder) {
        return stringBuilder.length() != 0 && stringBuilder.charAt(stringBuilder.length() - 1) == ' ';
    }

    private void e() {
        if (this.c == null) {
            this.c = new b();
            this.c.a("text", this.f);
        }
    }

    public String q(String str) {
        e();
        return super.q(str);
    }

    public b F() {
        e();
        return super.F();
    }

    public Node b(String str, String str2) {
        e();
        return super.b(str, str2);
    }

    public boolean r(String str) {
        e();
        return super.r(str);
    }

    public Node s(String str) {
        e();
        return super.s(str);
    }

    public String u(String str) {
        e();
        return super.u(str);
    }
}
