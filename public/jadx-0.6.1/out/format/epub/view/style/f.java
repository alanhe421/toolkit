package format.epub.view.style;

import format.epub.common.b.e;
import format.epub.common.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: ZLTextStyleCollection */
public class f {
    private static f e;
    public final String a;
    private a b;
    private final List<e> c;
    private final e[] d = new e[256];

    /* compiled from: ZLTextStyleCollection */
    private class a extends DefaultHandler {
        final /* synthetic */ f a;

        private a(f fVar) {
            this.a = fVar;
        }

        private int a(Attributes attributes, String str, int i) {
            String value = attributes.getValue(str);
            if (value != null) {
                try {
                    i = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                }
            }
            return i;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if ("base".equals(str2) && this.a.a.equals(attributes.getValue("screen"))) {
                this.a.b = new a(this.a.a, attributes.getValue("family"), a(attributes, "fontSize", 0));
            }
        }
    }

    public static f a() {
        if (e == null) {
            e = new f("Base");
        }
        return e;
    }

    public static void b() {
    }

    public f(String str) {
        this.a = str;
        Map a = new SimpleCSSReader().a(e.a("default/styles.css"));
        this.c = Collections.unmodifiableList(new ArrayList(a.values()));
        for (Entry entry : a.entrySet()) {
            this.d[((Integer) entry.getKey()).intValue() & 255] = (e) entry.getValue();
        }
        g.a(e.a("default/styles.xml"), new a());
    }

    public a c() {
        return this.b;
    }

    public e a(byte b) {
        return this.d[b & 255];
    }
}
