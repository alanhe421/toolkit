package org.jsoup.parser;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.Arrays;
import org.jsoup.helper.c;
import org.jsoup.nodes.Entities;

/* compiled from: Tokeniser */
final class h {
    private static final char[] h = new char[]{'\t', '\n', '\r', '\f', ' ', '<', '&'};
    StringBuilder a = new StringBuilder(1024);
    g b;
    f c = new f();
    e d = new e();
    a e = new a();
    c f = new c();
    b g = new b();
    private final a i;
    private final ParseErrorList j;
    private TokeniserState k = TokeniserState.Data;
    private Token l;
    private boolean m = false;
    private String n = null;
    private StringBuilder o = new StringBuilder(1024);
    private String p;
    private boolean q = true;
    private final int[] r = new int[1];
    private final int[] s = new int[2];

    static {
        Arrays.sort(h);
    }

    h(a aVar, ParseErrorList parseErrorList) {
        this.i = aVar;
        this.j = parseErrorList;
    }

    Token a() {
        if (!this.q) {
            c("Self closing flag not acknowledged");
            this.q = true;
        }
        while (!this.m) {
            this.k.read(this, this.i);
        }
        if (this.o.length() > 0) {
            String stringBuilder = this.o.toString();
            this.o.delete(0, this.o.length());
            this.n = null;
            return this.e.a(stringBuilder);
        } else if (this.n != null) {
            Token a = this.e.a(this.n);
            this.n = null;
            return a;
        } else {
            this.m = false;
            return this.l;
        }
    }

    void a(Token token) {
        c.b(this.m, "There is an unread token pending!");
        this.l = token;
        this.m = true;
        if (token.a == TokenType.StartTag) {
            f fVar = (f) token;
            this.p = fVar.b;
            if (fVar.d) {
                this.q = false;
            }
        } else if (token.a == TokenType.EndTag && ((e) token).e != null) {
            c("Attributes incorrectly present on end tag");
        }
    }

    void a(String str) {
        if (this.n == null) {
            this.n = str;
            return;
        }
        if (this.o.length() == 0) {
            this.o.append(this.n);
        }
        this.o.append(str);
    }

    void a(int[] iArr) {
        a(new String(iArr, 0, iArr.length));
    }

    void a(char c) {
        a(String.valueOf(c));
    }

    void a(TokeniserState tokeniserState) {
        this.k = tokeniserState;
    }

    void b(TokeniserState tokeniserState) {
        this.i.f();
        this.k = tokeniserState;
    }

    void b() {
        this.q = true;
    }

    int[] a(Character ch, boolean z) {
        if (this.i.b()) {
            return null;
        }
        if (ch != null && ch.charValue() == this.i.c()) {
            return null;
        }
        if (this.i.d(h)) {
            return null;
        }
        int[] iArr = this.r;
        this.i.g();
        int intValue;
        if (this.i.d("#")) {
            boolean e = this.i.e("X");
            String n = e ? this.i.n() : this.i.o();
            if (n.length() == 0) {
                b("numeric reference with no numerals");
                this.i.h();
                return null;
            }
            if (!this.i.d(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                b("missing semicolon");
            }
            try {
                intValue = Integer.valueOf(n, e ? 16 : 10).intValue();
            } catch (NumberFormatException e2) {
                intValue = -1;
            }
            if (intValue == -1 || ((intValue >= 55296 && intValue <= 57343) || intValue > 1114111)) {
                b("character outside of valid range");
                iArr[0] = 65533;
                return iArr;
            }
            iArr[0] = intValue;
            return iArr;
        }
        String m = this.i.m();
        boolean c = this.i.c(';');
        intValue = (Entities.b(m) || (Entities.a(m) && c)) ? 1 : 0;
        if (intValue == 0) {
            this.i.h();
            if (c) {
                b(String.format("invalid named referenece '%s'", new Object[]{m}));
            }
            return null;
        } else if (z && (this.i.p() || this.i.q() || this.i.c('=', '-', '_'))) {
            this.i.h();
            return null;
        } else {
            if (!this.i.d(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                b("missing semicolon");
            }
            intValue = Entities.a(m, this.s);
            if (intValue == 1) {
                iArr[0] = this.s[0];
                return iArr;
            } else if (intValue == 2) {
                return this.s;
            } else {
                c.b("Unexpected characters returned for " + m);
                return this.s;
            }
        }
    }

    g a(boolean z) {
        this.b = z ? this.c.n() : this.d.n();
        return this.b;
    }

    void c() {
        this.b.p();
        a(this.b);
    }

    void d() {
        this.g.b();
    }

    void e() {
        a(this.g);
    }

    void f() {
        this.f.b();
    }

    void g() {
        a(this.f);
    }

    void h() {
        Token.a(this.a);
    }

    boolean i() {
        return this.p != null && this.b.q().equalsIgnoreCase(this.p);
    }

    String j() {
        if (this.p == null) {
            return null;
        }
        return this.p;
    }

    void c(TokeniserState tokeniserState) {
        if (this.j.canAddError()) {
            this.j.add(new c(this.i.a(), "Unexpected character '%s' in input state [%s]", Character.valueOf(this.i.c()), tokeniserState));
        }
    }

    void d(TokeniserState tokeniserState) {
        if (this.j.canAddError()) {
            this.j.add(new c(this.i.a(), "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    private void b(String str) {
        if (this.j.canAddError()) {
            this.j.add(new c(this.i.a(), "Invalid character reference: %s", str));
        }
    }

    private void c(String str) {
        if (this.j.canAddError()) {
            this.j.add(new c(this.i.a(), str));
        }
    }
}
