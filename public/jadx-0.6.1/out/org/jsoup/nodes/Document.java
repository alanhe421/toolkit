package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.parser.d;
import org.jsoup.parser.f;

public class Document extends g {
    private OutputSettings f = new OutputSettings();
    private QuirksMode g = QuirksMode.noQuirks;
    private String h;
    private boolean i = false;

    public static class OutputSettings implements Cloneable {
        private EscapeMode a = EscapeMode.base;
        private Charset b = Charset.forName("UTF-8");
        private boolean c = true;
        private boolean d = false;
        private int e = 1;
        private Syntax f = Syntax.html;

        public enum Syntax {
            html,
            xml
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return g();
        }

        public EscapeMode a() {
            return this.a;
        }

        public OutputSettings a(Charset charset) {
            this.b = charset;
            return this;
        }

        public OutputSettings a(String str) {
            a(Charset.forName(str));
            return this;
        }

        CharsetEncoder b() {
            return this.b.newEncoder();
        }

        public Syntax c() {
            return this.f;
        }

        public boolean d() {
            return this.c;
        }

        public boolean e() {
            return this.d;
        }

        public int f() {
            return this.e;
        }

        public OutputSettings g() {
            try {
                OutputSettings outputSettings = (OutputSettings) super.clone();
                outputSettings.a(this.b.name());
                outputSettings.a = EscapeMode.valueOf(this.a.name());
                return outputSettings;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public /* synthetic */ g g() {
        return d();
    }

    public /* synthetic */ Node h() {
        return d();
    }

    public Document(String str) {
        super(f.a("#root", d.a), str);
        this.h = str;
    }

    public g b() {
        return a("body", this);
    }

    private g a(String str, Node node) {
        if (node.a().equals(str)) {
            return (g) node;
        }
        for (Node a : node.b) {
            node = a(str, a);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    public String c() {
        return super.D();
    }

    public g a(String str) {
        b().a(str);
        return this;
    }

    public String a() {
        return "#document";
    }

    public Document d() {
        Document document = (Document) super.g();
        document.f = this.f.g();
        return document;
    }

    public OutputSettings e() {
        return this.f;
    }

    public QuirksMode f() {
        return this.g;
    }

    public Document a(QuirksMode quirksMode) {
        this.g = quirksMode;
        return this;
    }
}
