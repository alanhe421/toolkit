package format.epub.view.style;

import format.epub.common.text.model.h;
import format.epub.common.text.model.n;
import format.epub.common.text.model.n.a;
import format.epub.options.ZLBoolean3;
import format.epub.options.g;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ZLTextNGStyleDescription */
public class e {
    private static final Map<String, Object> p = new HashMap();
    private static final Object q = new Object();
    public final String a;
    public final g b;
    public final g c;
    public final g d;
    public final g e;
    public final g f;
    public final g g;
    public final g h;
    public final g i;
    public final g j;
    public final g k;
    public final g l;
    public final g m;
    public final g n;
    public final g o;

    private static g a(String str, String str2, Map<String, String> map) {
        return new g("Style", str + "::" + str2, (String) map.get(str2));
    }

    e(String str, Map<String, String> map) {
        this.a = (String) map.get("fbreader-name");
        this.b = a(str, "font-family", (Map) map);
        this.c = a(str, "font-size", (Map) map);
        this.d = a(str, "font-weight", (Map) map);
        this.e = a(str, "font-style", (Map) map);
        this.f = a(str, "text-decoration", (Map) map);
        this.g = a(str, "hyphens", (Map) map);
        this.h = a(str, "margin-top", (Map) map);
        this.i = a(str, "margin-bottom", (Map) map);
        this.j = a(str, "margin-left", (Map) map);
        this.k = a(str, "margin-right", (Map) map);
        this.l = a(str, "text-indent", (Map) map);
        this.m = a(str, "text-align", (Map) map);
        this.n = a(str, "vertical-align", (Map) map);
        this.o = a(str, "line-height", (Map) map);
    }

    int a(h hVar, int i) {
        a a = a(this.c.a());
        return a == null ? i : n.a(a, hVar, i, 9);
    }

    int a(h hVar, int i, int i2) {
        a a = a(this.n.a());
        return a == null ? i : n.a(a, hVar, i2, 9);
    }

    int b(h hVar, int i, int i2) {
        a a = a(this.j.a());
        return a == null ? i : i + n.a(a, hVar, i2, 2);
    }

    int c(h hVar, int i, int i2) {
        a a = a(this.k.a());
        return a == null ? i : i + n.a(a, hVar, i2, 3);
    }

    int d(h hVar, int i, int i2) {
        return i;
    }

    int e(h hVar, int i, int i2) {
        return i;
    }

    int f(h hVar, int i, int i2) {
        a a = a(this.l.a());
        return a == null ? i : n.a(a, hVar, i2, 4);
    }

    ZLBoolean3 a() {
        String a = this.d.a();
        if ("bold".equals(a)) {
            return ZLBoolean3.B3_TRUE;
        }
        if ("normal".equals(a)) {
            return ZLBoolean3.B3_FALSE;
        }
        return ZLBoolean3.B3_UNDEFINED;
    }

    ZLBoolean3 b() {
        String a = this.e.a();
        if ("italic".equals(a) || "oblique".equals(a)) {
            return ZLBoolean3.B3_TRUE;
        }
        if ("normal".equals(a)) {
            return ZLBoolean3.B3_FALSE;
        }
        return ZLBoolean3.B3_UNDEFINED;
    }

    ZLBoolean3 c() {
        String a = this.f.a();
        if ("underline".equals(a)) {
            return ZLBoolean3.B3_TRUE;
        }
        if ("".equals(a) || "inherit".equals(a)) {
            return ZLBoolean3.B3_UNDEFINED;
        }
        return ZLBoolean3.B3_FALSE;
    }

    ZLBoolean3 d() {
        String a = this.f.a();
        if ("line-through".equals(a)) {
            return ZLBoolean3.B3_TRUE;
        }
        if ("".equals(a) || "inherit".equals(a)) {
            return ZLBoolean3.B3_UNDEFINED;
        }
        return ZLBoolean3.B3_FALSE;
    }

    byte e() {
        String a = this.m.a();
        if (a.length() == 0) {
            return (byte) 0;
        }
        if ("center".equals(a)) {
            return (byte) 3;
        }
        if ("left".equals(a)) {
            return (byte) 1;
        }
        if ("right".equals(a)) {
            return (byte) 2;
        }
        if ("justify".equals(a)) {
            return (byte) 4;
        }
        return (byte) 0;
    }

    private static a a(String str) {
        a aVar = null;
        if (str.length() == 0) {
            return null;
        }
        Object obj = p.get(str);
        if (obj != null) {
            return obj == q ? null : (a) obj;
        }
        try {
            if (str.endsWith("%")) {
                aVar = new a(Short.valueOf(str.substring(0, str.length() - 1)).shortValue(), (byte) 5);
            } else if (str.endsWith("rem")) {
                aVar = new a((short) ((int) (Double.valueOf(str.substring(0, str.length() - 2)).doubleValue() * 100.0d)), (byte) 3);
            } else if (str.endsWith("em")) {
                aVar = new a((short) ((int) (Double.valueOf(str.substring(0, str.length() - 2)).doubleValue() * 100.0d)), (byte) 2);
            } else if (str.endsWith("ex")) {
                aVar = new a((short) ((int) (Double.valueOf(str.substring(0, str.length() - 2)).doubleValue() * 100.0d)), (byte) 4);
            } else if (str.endsWith("px")) {
                aVar = new a(Short.valueOf(str.substring(0, str.length() - 2)).shortValue(), (byte) 0);
            } else if (str.endsWith("pt")) {
                aVar = new a(Short.valueOf(str.substring(0, str.length() - 2)).shortValue(), (byte) 1);
            }
        } catch (Exception e) {
        }
        Map map = p;
        if (aVar != null) {
            obj = aVar;
        } else {
            obj = q;
        }
        map.put(str, obj);
        return aVar;
    }

    public int b(h hVar, int i) {
        a a = a(this.h.a());
        if (a == null) {
            return 0;
        }
        return n.a(a, hVar, i, 5);
    }

    public int c(h hVar, int i) {
        a a = a(this.k.a());
        if (a == null) {
            return 0;
        }
        return n.a(a, hVar, i, 3);
    }

    public int d(h hVar, int i) {
        a a = a(this.i.a());
        if (a == null) {
            return 0;
        }
        return n.a(a, hVar, i, 6);
    }

    public int e(h hVar, int i) {
        a a = a(this.j.a());
        if (a == null) {
            return 0;
        }
        return n.a(a, hVar, i, 2);
    }
}
