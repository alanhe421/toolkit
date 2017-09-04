package format.epub.common.c.a;

import format.epub.common.utils.o;
import java.util.List;
import java.util.Map;

/* compiled from: StyleSheetMultiStyleParser */
public class c extends d {
    public c(String str) {
        super(str);
    }

    protected void a(b bVar, Map<String, String> map) {
    }

    public void a(String str, Map<String, String> map) {
        String a = o.a(str);
        if (a != null && a.length() != 0) {
            if (a.charAt(0) == '@') {
                b(a, map);
                return;
            }
            List a2 = o.a(a, ",", true);
            int size = a2.size();
            for (int i = 0; i < size; i++) {
                b a3 = b.a((String) a2.get(i));
                if (a3 != null) {
                    a(a3, (Map) map);
                }
            }
        }
    }

    private void b(String str, Map<String, String> map) {
        if (str != "@font-face") {
        }
    }
}
