package format.epub.common.b;

import java.util.Collections;
import java.util.List;

/* compiled from: ZLArchiveEntryFile */
public abstract class a extends b {
    protected final b a;
    protected final String b;

    public static String a(String str) {
        while (str.startsWith("./")) {
            str = str.substring(2);
        }
        while (true) {
            int lastIndexOf = str.lastIndexOf("/./");
            if (lastIndexOf == -1) {
                break;
            }
            str = str.substring(0, lastIndexOf) + str.substring(lastIndexOf + 2);
        }
        while (true) {
            lastIndexOf = str.indexOf("/../");
            if (lastIndexOf <= 0) {
                return str;
            }
            int lastIndexOf2 = str.lastIndexOf(47, lastIndexOf - 1);
            if (lastIndexOf2 == -1) {
                return str.substring(lastIndexOf + 4);
            }
            str = str.substring(0, lastIndexOf2) + str.substring(lastIndexOf + 3);
        }
    }

    public static a a(b bVar, String str) {
        if (bVar == null) {
            return null;
        }
        String a = a(str);
        switch (bVar.c & 65280) {
            case 256:
                return new f(bVar, a);
            default:
                return null;
        }
    }

    static List<b> a(b bVar) {
        switch (bVar.c & 65280) {
            case 256:
                return f.b(bVar);
            default:
                return Collections.emptyList();
        }
    }

    protected a(b bVar, String str) {
        this.a = bVar;
        this.b = str;
        g();
    }

    public boolean a() {
        return this.a.a();
    }

    public boolean b() {
        return false;
    }

    public String c() {
        return this.a.c() + ":" + this.b;
    }

    public String d() {
        return this.b;
    }

    public b e() {
        return this.a;
    }

    public d f() {
        b bVar = this.a;
        while (bVar != null && !(bVar instanceof d)) {
            bVar = bVar.e();
        }
        return (d) bVar;
    }
}
