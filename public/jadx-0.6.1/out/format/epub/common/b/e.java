package format.epub.common.b;

import format.epub.common.utils.i;

/* compiled from: ZLResourceFile */
public abstract class e extends b {
    private final String a;

    public static e a(String str) {
        return i.a().a(str);
    }

    static e a(e eVar, String str) {
        return i.a().a(eVar, str);
    }

    protected e(String str) {
        this.a = str;
        g();
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.a.substring(this.a.lastIndexOf(47) + 1);
    }

    public d f() {
        return null;
    }
}
