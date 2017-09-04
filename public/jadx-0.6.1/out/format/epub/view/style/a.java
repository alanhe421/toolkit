package format.epub.view.style;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import format.epub.common.text.model.h;
import format.epub.options.b;
import format.epub.options.e;
import format.epub.options.g;
import format.epub.view.m;
import format.epub.view.v;

/* compiled from: ZLTextBaseStyle */
public class a extends v {
    public final b c = new b("Style", "css:textAlignment", true);
    public final b d = new b("Style", "css:margins", true);
    public final b e = new b("Style", "css:fontSize", true);
    public final b f = new b("Style", "css:fontFamily", true);
    public final b g = new b("Options", "AutoHyphenation", true);
    public final b h;
    public final b i;
    public final b j;
    public final b k;
    public final e l;
    public final e m;
    public final g n;
    public final e o;

    public a(String str, String str2, int i) {
        super(null, m.c);
        this.n = new g("Style", str + ":fontFamily", str2);
        int i2 = (com.qq.reader.common.c.a.bY * i) / 160;
        this.o = new e("Style", str + ":fontSize", 5, Math.max(Opcodes.ADD_INT, i2 * 2), i2);
        this.h = new b("Style", str + ":bold", false);
        this.i = new b("Style", str + ":italic", false);
        this.j = new b("Style", str + ":underline", false);
        this.k = new b("Style", str + ":strikeThrough", false);
        this.l = new e("Style", str + ":alignment", 1, 4, 4);
        this.m = new e("Style", str + ":lineSpacing", 5, 20, 10);
    }

    public int u() {
        return this.o.a();
    }

    public int a(h hVar) {
        return u();
    }

    public String a() {
        return this.n.a();
    }

    public boolean c() {
        return this.h.a();
    }

    public boolean d() {
        return this.i.a();
    }

    public boolean e() {
        return this.j.a();
    }

    public boolean f() {
        return this.k.a();
    }

    public int f(h hVar) {
        return 0;
    }

    public int g(h hVar) {
        return 0;
    }

    public int h(h hVar) {
        return 0;
    }

    public int i(h hVar) {
        return 0;
    }

    public int l(h hVar) {
        return 0;
    }

    public float j() {
        return (float) (this.m.a() * 10);
    }

    public int m(h hVar) {
        return 0;
    }

    public int n(h hVar) {
        return 0;
    }

    public int o(h hVar) {
        return 0;
    }

    public byte k() {
        return (byte) this.l.a();
    }

    public int l() {
        return 0;
    }

    public int j(h hVar) {
        return 0;
    }

    public int k(h hVar) {
        return 0;
    }
}
