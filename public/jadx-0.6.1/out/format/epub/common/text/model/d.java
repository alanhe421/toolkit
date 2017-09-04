package format.epub.common.text.model;

import format.epub.common.image.ZLImageMap;
import format.epub.common.image.a;
import format.epub.common.image.b;
import java.util.Map;

/* compiled from: ZLImageEntry */
public final class d {
    public final String a;
    public final short b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public String f = "";
    public int g = 3;
    public String h;
    public String i;
    private final ZLImageMap j;
    private final Map<String, String> k;
    private boolean l;
    private int m;
    private byte n;
    private String o;

    d(ZLImageMap zLImageMap, Map<String, String> map, String str, short s, boolean z, String str2, boolean z2, boolean z3) {
        this.j = zLImageMap;
        this.k = map;
        this.a = str;
        this.b = s;
        this.c = z;
        this.f = str2;
        this.d = z2;
        this.e = z3;
    }

    public b a() {
        b image = this.j.getImage(this.a);
        if (image == null) {
            String str = (String) this.k.get(this.a);
            if (str != null) {
                b aVar = new a("image/auto", format.epub.common.b.b.b(str));
                this.j.put(this.a, aVar);
                return aVar;
            }
        }
        return image;
    }

    public boolean b() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public int c() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
    }

    public byte d() {
        return this.n;
    }

    public void a(byte b) {
        this.n = b;
    }

    public String e() {
        return this.o;
    }

    public void a(String str) {
        this.o = str;
    }
}
