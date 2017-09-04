package format.epub.common.core.a;

import format.epub.common.b.b;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ZLXMLReaderAdapter */
public abstract class g implements f {
    private Map<String, String> a = Collections.emptyMap();

    public boolean a(b bVar) {
        return e.a(this, bVar);
    }

    public boolean f() {
        return false;
    }

    public boolean a(String str, c cVar) {
        return false;
    }

    public boolean c(String str) {
        return false;
    }

    public void a(char[] cArr, int i, int i2) {
    }

    public void b(char[] cArr, int i, int i2) {
        a(cArr, i, i2);
    }

    public void m() {
    }

    public void n() {
    }

    public boolean g() {
        return false;
    }

    public void a(Map<String, String> map) {
        if (map == null) {
            map = Collections.emptyMap();
        }
        this.a = map;
    }

    public String a(c cVar, String str, String str2) {
        if (str == null) {
            return cVar.a(str2);
        }
        int a = cVar.a();
        if (a == 0) {
            return null;
        }
        String str3 = ":" + str2;
        for (a--; a >= 0; a--) {
            String a2 = cVar.a(a);
            if (a2.endsWith(str3)) {
                if (str.equals(this.a.get(a2.substring(0, a2.length() - str3.length())))) {
                    return cVar.b(a);
                }
            }
        }
        return null;
    }

    public void a(HashMap<String, char[]> hashMap) {
    }

    public List<String> e() {
        return Collections.emptyList();
    }
}
