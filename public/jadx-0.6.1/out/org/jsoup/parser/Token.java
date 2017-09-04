package org.jsoup.parser;

abstract class Token {
    TokenType a;

    enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    static final class a extends Token {
        private String b;

        a() {
            super();
            this.a = TokenType.Character;
        }

        Token b() {
            this.b = null;
            return this;
        }

        a a(String str) {
            this.b = str;
            return this;
        }

        String n() {
            return this.b;
        }

        public String toString() {
            return n();
        }
    }

    static final class b extends Token {
        final StringBuilder b;
        boolean c;

        Token b() {
            Token.a(this.b);
            this.c = false;
            return this;
        }

        b() {
            super();
            this.b = new StringBuilder();
            this.c = false;
            this.a = TokenType.Comment;
        }

        String n() {
            return this.b.toString();
        }

        public String toString() {
            return "<!--" + n() + "-->";
        }
    }

    static final class c extends Token {
        final StringBuilder b;
        String c;
        final StringBuilder d;
        final StringBuilder e;
        boolean f;

        c() {
            super();
            this.b = new StringBuilder();
            this.c = null;
            this.d = new StringBuilder();
            this.e = new StringBuilder();
            this.f = false;
            this.a = TokenType.Doctype;
        }

        Token b() {
            Token.a(this.b);
            this.c = null;
            Token.a(this.d);
            Token.a(this.e);
            this.f = false;
            return this;
        }

        String n() {
            return this.b.toString();
        }

        String o() {
            return this.c;
        }

        String p() {
            return this.d.toString();
        }

        public String q() {
            return this.e.toString();
        }

        public boolean r() {
            return this.f;
        }
    }

    static final class d extends Token {
        d() {
            super();
            this.a = TokenType.EOF;
        }

        Token b() {
            return this;
        }
    }

    static abstract class g extends Token {
        protected String b;
        protected String c;
        boolean d = false;
        org.jsoup.nodes.b e;
        private String f;
        private StringBuilder g = new StringBuilder();
        private String h;
        private boolean i = false;
        private boolean j = false;

        g() {
            super();
        }

        /* synthetic */ Token b() {
            return n();
        }

        g n() {
            this.b = null;
            this.c = null;
            this.f = null;
            Token.a(this.g);
            this.h = null;
            this.i = false;
            this.j = false;
            this.d = false;
            this.e = null;
            return this;
        }

        final void o() {
            if (this.e == null) {
                this.e = new org.jsoup.nodes.b();
            }
            if (this.f != null) {
                this.f = this.f.trim();
                if (this.f.length() > 0) {
                    org.jsoup.nodes.a aVar;
                    if (this.j) {
                        aVar = new org.jsoup.nodes.a(this.f, this.g.length() > 0 ? this.g.toString() : this.h);
                    } else if (this.i) {
                        aVar = new org.jsoup.nodes.a(this.f, "");
                    } else {
                        aVar = new org.jsoup.nodes.c(this.f);
                    }
                    this.e.a(aVar);
                }
            }
            this.f = null;
            this.i = false;
            this.j = false;
            Token.a(this.g);
            this.h = null;
        }

        final void p() {
            if (this.f != null) {
                o();
            }
        }

        final String q() {
            boolean z = this.b == null || this.b.length() == 0;
            org.jsoup.helper.c.b(z);
            return this.b;
        }

        final String r() {
            return this.c;
        }

        final g a(String str) {
            this.b = str;
            this.c = org.jsoup.a.a.a(str);
            return this;
        }

        final boolean s() {
            return this.d;
        }

        final org.jsoup.nodes.b t() {
            return this.e;
        }

        final void b(String str) {
            if (this.b != null) {
                str = this.b.concat(str);
            }
            this.b = str;
            this.c = org.jsoup.a.a.a(this.b);
        }

        final void a(char c) {
            b(String.valueOf(c));
        }

        final void c(String str) {
            if (this.f != null) {
                str = this.f.concat(str);
            }
            this.f = str;
        }

        final void b(char c) {
            c(String.valueOf(c));
        }

        final void d(String str) {
            v();
            if (this.g.length() == 0) {
                this.h = str;
            } else {
                this.g.append(str);
            }
        }

        final void c(char c) {
            v();
            this.g.append(c);
        }

        final void a(int[] iArr) {
            v();
            for (int appendCodePoint : iArr) {
                this.g.appendCodePoint(appendCodePoint);
            }
        }

        final void u() {
            this.i = true;
        }

        private void v() {
            this.j = true;
            if (this.h != null) {
                this.g.append(this.h);
                this.h = null;
            }
        }
    }

    static final class e extends g {
        e() {
            this.a = TokenType.EndTag;
        }

        public String toString() {
            return "</" + q() + ">";
        }
    }

    static final class f extends g {
        /* synthetic */ Token b() {
            return n();
        }

        f() {
            this.e = new org.jsoup.nodes.b();
            this.a = TokenType.StartTag;
        }

        g n() {
            super.n();
            this.e = new org.jsoup.nodes.b();
            return this;
        }

        f a(String str, org.jsoup.nodes.b bVar) {
            this.b = str;
            this.e = bVar;
            this.c = org.jsoup.a.a.a(this.b);
            return this;
        }

        public String toString() {
            if (this.e == null || this.e.a() <= 0) {
                return "<" + q() + ">";
            }
            return "<" + q() + " " + this.e.toString() + ">";
        }
    }

    abstract Token b();

    private Token() {
    }

    String a() {
        return getClass().getSimpleName();
    }

    static void a(StringBuilder stringBuilder) {
        if (stringBuilder != null) {
            stringBuilder.delete(0, stringBuilder.length());
        }
    }

    final boolean c() {
        return this.a == TokenType.Doctype;
    }

    final c d() {
        return (c) this;
    }

    final boolean e() {
        return this.a == TokenType.StartTag;
    }

    final f f() {
        return (f) this;
    }

    final boolean g() {
        return this.a == TokenType.EndTag;
    }

    final e h() {
        return (e) this;
    }

    final boolean i() {
        return this.a == TokenType.Comment;
    }

    final b j() {
        return (b) this;
    }

    final boolean k() {
        return this.a == TokenType.Character;
    }

    final a l() {
        return (a) this;
    }

    final boolean m() {
        return this.a == TokenType.EOF;
    }
}
