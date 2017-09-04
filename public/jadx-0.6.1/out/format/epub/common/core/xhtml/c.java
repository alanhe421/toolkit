package format.epub.common.core.xhtml;

import android.text.TextUtils;
import com.qq.taf.jce.JceStruct;
import com.tencent.mm.performance.WxPerformanceHandle;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.base.a;
import format.epub.common.a.b;
import format.epub.common.c.a.e;
import format.epub.common.c.a.f;
import format.epub.common.c.a.h;
import format.epub.common.core.a.g;
import format.epub.common.text.model.a.d;
import format.epub.common.text.model.n;
import format.epub.options.ZLBoolean3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

/* compiled from: XHTMLReader */
public class c extends g {
    public static final XHTMLTagInfoList a = new XHTMLTagInfoList();
    private static final HashMap<String, d> v = new HashMap();
    private static ArrayList<String> z = new ArrayList();
    String b;
    String c;
    String d;
    boolean e;
    boolean f;
    boolean g;
    format.epub.common.c.a.g h = new format.epub.common.c.a.g();
    boolean i;
    f j;
    h k;
    Stack<a> l = new Stack();
    Map<String, e> m = new HashMap();
    int n;
    Stack<Integer> o = new Stack();
    int p;
    int q;
    d r;
    String s;
    public int t = -1;
    String u;
    private final b w;
    private final Map<String, Integer> x;
    private final Map<String, String> y = new HashMap();

    public static d a(String str, d dVar) {
        d dVar2 = (d) v.get(str);
        v.put(str, dVar);
        return dVar2;
    }

    public static void a() {
        if (v.isEmpty()) {
            a("body", new e());
            a("aside", new p());
            a("style", new r());
            a("p", new m(51));
            a("h1", new n((byte) 31));
            a("h2", new n((byte) 32));
            a("h3", new n((byte) 33));
            a("h4", new n((byte) 34));
            a("h5", new n((byte) 35));
            a("h6", new n((byte) 36));
            a("ol", new l(1));
            a("ul", new l(0));
            a("li", new j());
            a("strong", new f((byte) 18));
            a("b", new f((byte) 28));
            a("em", new f((byte) 17));
            a("i", new f((byte) 27));
            d fVar = new f((byte) 21);
            a("code", fVar);
            a("tt", fVar);
            a("kbd", fVar);
            a("var", fVar);
            a("samp", fVar);
            a("cite", new f(JceStruct.ZERO_TAG));
            a("sub", new f((byte) 19));
            a("sup", new f(a.z));
            a("dd", new f((byte) 30));
            a("dfn", new f((byte) 29));
            a("strike", new f((byte) 22));
            a("a", new g());
            a(SocialConstants.PARAM_IMG_URL, new h(null, "src"));
            a("image", new h("http://www.w3.org/1999/xlink", "href"));
            a("object", new h(null, "data"));
            a("div", new m(-1));
            a("dt", new m(-1));
            a("link", new k());
            a("pre", new o());
            a("td", new m(-1));
            a("th", new m(-1));
            a("video", new s(null, "poster"));
            a(SocialConstants.PARAM_SOURCE, new q());
        }
    }

    public c(b bVar, Map<String, Integer> map) {
        this.w = bVar;
        this.x = map;
    }

    final b b() {
        return this.w;
    }

    public final String a(String str) {
        String a = format.epub.common.b.a.a(format.epub.common.utils.d.b(str));
        Integer num = (Integer) this.x.get(a);
        if (num == null) {
            num = Integer.valueOf(this.x.size());
            this.x.put(a, num);
        }
        return num.toString();
    }

    final String b(String str) {
        String str2 = (String) this.y.get(str);
        if (str2 != null) {
            return str2;
        }
        str2 = a(this.c + str);
        this.y.put(str, str2);
        return str2;
    }

    public boolean a(format.epub.common.b.b bVar, String str) {
        a();
        this.d = str;
        this.b = format.epub.common.utils.d.a(bVar);
        String a = format.epub.common.utils.d.a(this.b);
        if (!a.equals(this.c)) {
            this.c = a;
            this.y.clear();
        }
        this.e = false;
        this.f = false;
        this.g = false;
        this.t = 0;
        this.i = true;
        this.h.a();
        this.l.clear();
        this.j = new f();
        if (this.k != null) {
            this.k.a();
        }
        format.epub.common.c.a.g.a(this.b);
        return a(bVar);
    }

    public boolean a(String str, format.epub.common.core.a.c cVar) {
        String toLowerCase = str.toLowerCase();
        if ("br".equals(toLowerCase)) {
            a(true, true);
        } else {
            if ("body".equals(toLowerCase)) {
                this.f = true;
            }
            List<String> arrayList = new ArrayList();
            String a = cVar.a(WxPerformanceHandle.MESSAGE_CLASS);
            if (a != null) {
                String[] split = a.split(" ");
                for (Object add : split) {
                    arrayList.add(add);
                }
            }
            if (!this.l.isEmpty()) {
                ((a) this.l.lastElement()).e.add(new i(toLowerCase, arrayList));
            }
            this.l.add(new a());
            a aVar = (a) this.l.lastElement();
            String a2 = cVar.a("id");
            if (a2 != null) {
                this.w.a(this.d + a2);
            }
            ZLBoolean3 a3 = this.h.a(toLowerCase, "");
            aVar.c = this.h.b(toLowerCase, "");
            ZLBoolean3 zLBoolean3 = a3;
            for (String a22 : arrayList) {
                ZLBoolean3 a4 = this.h.a(toLowerCase, a22);
                if (a4 != ZLBoolean3.B3_UNDEFINED) {
                    zLBoolean3 = a4;
                }
                a3 = this.h.b(toLowerCase, a22);
                if (a3 != ZLBoolean3.B3_UNDEFINED) {
                    aVar.c = a3;
                }
            }
            if (zLBoolean3 == ZLBoolean3.B3_TRUE) {
                this.w.c();
            }
            boolean z = this.g;
            boolean h = b().h();
            d dVar = (d) v.get(str.toLowerCase());
            if (dVar != null && dVar.a(this.t)) {
                dVar.a(this, cVar);
            }
            List arrayList2 = new ArrayList();
            a(toLowerCase, "", arrayList2);
            for (String str2 : arrayList) {
                a("", str2, arrayList2);
                a(toLowerCase, str2, arrayList2);
            }
            Object add2 = cVar.a("style");
            if (!TextUtils.isEmpty(add2)) {
                arrayList2.add(this.j.c(add2));
            }
            n a5 = n.a(arrayList2);
            if (this.f && a5 == null && arrayList2.size() == 0) {
                a5 = new format.epub.common.text.model.a.a((short) 0);
                a5.e((byte) 6);
            }
            if (a5 != null) {
                if (toLowerCase.equals("span")) {
                    a5.h(53);
                } else if (toLowerCase.equals(SocialConstants.PARAM_IMG_URL) && (z || h)) {
                    a5.h(54);
                }
                a(a5);
            }
            if (dVar != null && dVar.a(this.t)) {
                dVar.b(this, cVar);
            }
            if (aVar.d() == 1) {
                a(false, false);
            }
            this.u = toLowerCase;
        }
        return false;
    }

    public boolean c(String str) {
        String toLowerCase = str.toLowerCase();
        if (!"br".equals(toLowerCase)) {
            a aVar = (a) this.l.get(this.l.size() - 1);
            int size = aVar.b().size();
            this.l.size();
            d dVar = (d) v.get(str.toLowerCase());
            int i = size;
            boolean z = true;
            while (i > 0) {
                if ("div".equals(toLowerCase)) {
                    this.w.b(z);
                } else {
                    this.w.a(z);
                }
                i--;
                z = false;
            }
            if (dVar != null && dVar.a(this.t)) {
                dVar.a(this);
                this.g = false;
            }
            if (aVar.c == ZLBoolean3.B3_TRUE) {
                this.w.c();
            } else if (aVar.d() == 1) {
                a(false, false);
            }
            this.l.pop();
        }
        return false;
    }

    void c() {
        this.w.b();
    }

    void a(int i) {
        if (i != -1) {
            ((a) this.l.lastElement()).c().add(Integer.valueOf(i));
        }
    }

    public void a(char[] cArr, int i, int i2) {
        switch (this.t) {
            case 1:
                if (this.k != null) {
                    this.k.a(cArr, i, i2, false);
                    return;
                }
                return;
            case 2:
                if (this.e) {
                    char c = cArr[i];
                    if (c == '\r' || c == '\n') {
                        a(true, true);
                        i++;
                        i2--;
                    }
                    int i3 = 0;
                    while (i3 < i2 && Character.isWhitespace(cArr[i + i3])) {
                        i3++;
                    }
                    this.w.a((short) i3);
                    i += i3;
                    i2 -= i3;
                } else if (this.g || !this.w.g()) {
                    while (Character.isWhitespace(cArr[i])) {
                        i++;
                        i2--;
                        if (i2 == 0) {
                        }
                    }
                }
                if (i2 > 0) {
                    this.i = false;
                    if (!this.w.g()) {
                        this.w.a();
                    }
                    this.w.a(cArr, i, i2, false);
                    this.g = false;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static List<String> d() {
        if (z.isEmpty()) {
            z.add("formats/xhtml/xhtml-lat1.ent");
            z.add("formats/xhtml/xhtml-special.ent");
            z.add("formats/xhtml/xhtml-symbol.ent");
        }
        return z;
    }

    public List<String> e() {
        return d();
    }

    public boolean f() {
        return true;
    }

    public boolean g() {
        return true;
    }

    public void a(boolean z, boolean z2) {
        if (z && this.i) {
            this.w.a((short) 1);
        }
        int size = this.l.size();
        n nVar = new n((short) size);
        nVar.e((byte) 9);
        nVar.a(6, (short) 0, (byte) 0);
        a(nVar, size);
        c();
        if (z2) {
            a(false);
        } else {
            a(true);
        }
        nVar = new n((short) size);
        nVar.e((byte) 9);
        nVar.a(5, (short) 0, (byte) 0);
        a(nVar, size);
    }

    private void a(n nVar, int i) {
        this.w.a(nVar, i);
    }

    void a(boolean z) {
        this.i = true;
        this.w.a();
        int size = this.l.size();
        int i = 0;
        while (i < size) {
            boolean z2;
            a aVar = (a) this.l.get(i);
            for (Integer byteValue : aVar.a) {
                this.w.a(byteValue.byteValue(), true);
            }
            List<n> list = aVar.b;
            if (z && i + 1 == size) {
                z2 = false;
            } else {
                z2 = true;
            }
            int i2 = i + 1;
            for (n nVar : list) {
                a(z2 ? nVar.n() : nVar.m(), i2);
            }
            i++;
        }
    }

    final XHTMLTagInfoList b(int i) {
        if (this.l.size() < i + 2) {
            return a;
        }
        return ((a) this.l.get((this.l.size() - i) - 2)).a();
    }

    boolean a(format.epub.common.c.a.b.a aVar, int i, int i2) {
        int i3 = 1;
        if (aVar == null) {
            return true;
        }
        format.epub.common.c.a.b a = aVar.a();
        int i4;
        switch (aVar.b()) {
            case (byte) 0:
                if (a.c() == null || a.c().b() == (byte) 0) {
                    while (i3 < (this.l.size() - i) - 1) {
                        if (b(i + i3).matches(a, -1)) {
                            return a(a.c(), i3, -1);
                        }
                        i3++;
                    }
                    return false;
                }
                i4 = 1;
                while (i4 < (this.l.size() - i) - 1) {
                    if (b(i + i4).matches(a, -1) && a(a.c(), i4, -1)) {
                        return true;
                    }
                    i4++;
                }
                return false;
            case (byte) 1:
                if (b(i + 1).matches(a, -1) && a(a.c(), i + 1, -1)) {
                    return true;
                }
                return false;
            case (byte) 2:
                if (b(i).matches(a, i2 - 1) && a(a.c(), i, i2 - 1)) {
                    return true;
                }
                return false;
            case (byte) 3:
                if (a.c() == null || a.c().b() != (byte) 2) {
                    i4 = b(i).find(a, 0, i2);
                    if (i4 == -1 || !a(a.c(), i, i4)) {
                        return false;
                    }
                    return true;
                }
                do {
                    i2 = b(i).find(a, 1, i2);
                    if (i2 == -1) {
                        return false;
                    }
                } while (!a(a.c(), i, i2));
                return true;
            default:
                return false;
        }
    }

    void a(n nVar) {
        if (nVar != null) {
            a(nVar.m(), this.l.size());
            a aVar = (a) this.l.get(this.l.size() - 1);
            aVar.b().add(nVar);
            int l = nVar.l();
            if (l != -1) {
                aVar.a(l);
            }
        }
    }

    final void a(String str, String str2, List<n> list) {
        for (Entry entry : this.h.d(str, str2)) {
            if (a(((format.epub.common.c.a.b) entry.getKey()).c(), 0, -1)) {
                list.add(entry.getValue());
            }
        }
    }

    final boolean a(String str, List<String> list) {
        for (int size = this.l.size() - 1; size >= 0; size--) {
            for (n a : ((a) this.l.get(size)).b) {
                if (a.a()) {
                    return true;
                }
            }
        }
        return false;
    }

    final int b(String str, List<String> list) {
        for (int size = this.l.size() - 1; size >= 0; size--) {
            for (n nVar : ((a) this.l.get(size)).b) {
                if (nVar != null && nVar.b() != 0) {
                    return nVar.b();
                }
            }
        }
        return 0;
    }

    final int c(String str, List<String> list) {
        for (int size = this.l.size() - 1; size >= 0; size--) {
            for (n nVar : ((a) this.l.get(size)).b) {
                if (nVar != null && nVar.e() != (byte) 0) {
                    return nVar.e();
                }
            }
        }
        return 0;
    }

    public n d(String str, List<String> list) {
        List arrayList = new ArrayList();
        for (String d : list) {
            for (Entry entry : this.h.d(str, d)) {
                if (a(((format.epub.common.c.a.b) entry.getKey()).c(), 0, -1)) {
                    arrayList.add(entry.getValue());
                }
            }
        }
        if (arrayList.size() > 0) {
            return n.a(arrayList);
        }
        return null;
    }

    public n a(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return null;
        }
        return this.h.c(str, str2);
    }

    final String e(String str, List<String> list) {
        for (int size = this.l.size() - 1; size >= 0; size--) {
            for (n nVar : ((a) this.l.get(size)).b) {
                if (nVar != null && nVar.c() != null) {
                    return nVar.c();
                }
            }
        }
        return null;
    }

    public void h() {
        this.p++;
    }

    public int i() {
        return this.p;
    }

    public void j() {
        this.p = 0;
        this.q = 0;
    }

    public int k() {
        return this.q;
    }

    public void c(int i) {
        this.q = i;
    }

    public String l() {
        return this.s;
    }

    public void d(String str) {
        this.s = str;
    }
}
