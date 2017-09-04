package org.jsoup.parser;

import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.helper.c;

/* compiled from: Tag */
public class f {
    private static final Map<String, f> a = new HashMap();
    private static final String[] k = new String[]{"html", "head", "body", "frameset", "script", "noscript", "style", "meta", "link", "title", "frame", "noframes", "section", "nav", "aside", "hgroup", "header", "footer", "p", "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "pre", "div", "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "dl", "dt", "dd", "li", "table", "caption", "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "video", "audio", "canvas", "details", "menu", "plaintext", "template", "article", "main", "svg", "math"};
    private static final String[] l = new String[]{"object", "base", "font", "tt", "i", "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", "a", SocialConstants.PARAM_IMG_URL, "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", "span", "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", SocialConstants.PARAM_SOURCE, "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", SocialConstants.PARAM_SOURCE, "track", "data", "bdi", "s"};
    private static final String[] m = new String[]{"meta", "link", "base", "frame", SocialConstants.PARAM_IMG_URL, "br", "wbr", "embed", "hr", "input", "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", SocialConstants.PARAM_SOURCE, "track"};
    private static final String[] n = new String[]{"title", "a", "p", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style", "ins", "del", "s"};
    private static final String[] o = new String[]{"pre", "plaintext", "title", "textarea"};
    private static final String[] p = new String[]{"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"};
    private static final String[] q = new String[]{"input", "keygen", "object", "select", "textarea"};
    private String b;
    private boolean c = true;
    private boolean d = true;
    private boolean e = true;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;

    static {
        int i = 0;
        for (String fVar : k) {
            a(new f(fVar));
        }
        for (String fVar2 : l) {
            f fVar3 = new f(fVar2);
            fVar3.c = false;
            fVar3.d = false;
            a(fVar3);
        }
        for (Object obj : m) {
            Object obj2 = (f) a.get(obj2);
            c.a(obj2);
            obj2.e = false;
            obj2.f = true;
        }
        for (Object obj22 : n) {
            obj22 = (f) a.get(obj22);
            c.a(obj22);
            obj22.d = false;
        }
        for (Object obj222 : o) {
            obj222 = (f) a.get(obj222);
            c.a(obj222);
            obj222.h = true;
        }
        for (Object obj2222 : p) {
            obj2222 = (f) a.get(obj2222);
            c.a(obj2222);
            obj2222.i = true;
        }
        String[] strArr = q;
        int length = strArr.length;
        while (i < length) {
            obj2222 = (f) a.get(strArr[i]);
            c.a(obj2222);
            obj2222.j = true;
            i++;
        }
    }

    private f(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public static f a(String str, d dVar) {
        c.a((Object) str);
        f fVar = (f) a.get(str);
        if (fVar != null) {
            return fVar;
        }
        String a = dVar.a(str);
        c.a(a);
        fVar = (f) a.get(a);
        if (fVar != null) {
            return fVar;
        }
        fVar = new f(a);
        fVar.c = false;
        return fVar;
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.f;
    }

    public boolean e() {
        return this.f || this.g;
    }

    public boolean f() {
        return a.containsKey(this.b);
    }

    public boolean g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    f i() {
        this.g = true;
        return this;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (!this.b.equals(fVar.b) || this.e != fVar.e || this.f != fVar.f || this.d != fVar.d || this.c != fVar.c || this.h != fVar.h || this.g != fVar.g || this.i != fVar.i) {
            return false;
        }
        if (this.j != fVar.j) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int hashCode = ((this.c ? 1 : 0) + (this.b.hashCode() * 31)) * 31;
        if (this.d) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.e) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.f) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.g) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.h) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.i) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + hashCode) * 31;
        if (!this.j) {
            i2 = 0;
        }
        return i + i2;
    }

    public String toString() {
        return this.b;
    }

    private static void a(f fVar) {
        a.put(fVar.b, fVar);
    }
}
