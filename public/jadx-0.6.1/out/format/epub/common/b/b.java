package format.epub.common.b;

import com.qq.reader.readengine.model.a;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* compiled from: ZLFile */
public abstract class b {
    private static final HashMap<String, b> a = new HashMap();
    private String b;
    protected int c;
    private String d;
    private boolean e;

    public abstract boolean a();

    public abstract boolean b();

    public abstract String c();

    public abstract String d();

    public abstract b e();

    public abstract d f();

    public abstract long h();

    public abstract InputStream i() throws IOException;

    protected void g() {
        String d = d();
        int lastIndexOf = d.lastIndexOf(46);
        this.b = lastIndexOf > 0 ? d.substring(lastIndexOf + 1).toLowerCase().intern() : "";
        this.d = d.substring(d.lastIndexOf(47) + 1);
        lastIndexOf = 0;
        if (this.b == "zip") {
            lastIndexOf = 256;
        } else if (this.b == "oebzip") {
            lastIndexOf = 256;
        } else if (a.i(this.b)) {
            lastIndexOf = 256;
        } else if (this.b == "tar") {
            lastIndexOf = 512;
        }
        this.c = lastIndexOf;
    }

    public static b b(b bVar, String str) {
        b bVar2;
        if (bVar == null) {
            bVar2 = (b) a.get(str);
            if (bVar2 != null) {
                return bVar2;
            }
            if (str.startsWith("/")) {
                return new d(str);
            }
            return e.a(str);
        }
        b dVar;
        if ((bVar instanceof d) && bVar.e() == null) {
            dVar = new d(bVar.c() + '/' + str);
        } else if (bVar instanceof e) {
            dVar = e.a((e) bVar, str);
        } else {
            dVar = a.a(bVar, str);
        }
        if (!(a.isEmpty() || dVar == null)) {
            bVar2 = (b) a.get(dVar.c());
            if (bVar2 != null) {
                return bVar2;
            }
        }
        return dVar;
    }

    public static b b(String str) {
        if (str == null) {
            return null;
        }
        b bVar = (b) a.get(str);
        if (bVar != null) {
            return bVar;
        }
        if (!str.startsWith("/")) {
            return e.a(str);
        }
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf > 1) {
            return a.a(b(str.substring(0, lastIndexOf)), str.substring(lastIndexOf + 1));
        }
        return new d(str);
    }

    public final boolean j() {
        return (this.c & 65280) != 0;
    }

    public final String k() {
        return this.d;
    }

    public final String l() {
        return this.b;
    }

    protected List<b> m() {
        return Collections.emptyList();
    }

    public final List<b> n() {
        if (a()) {
            if (b()) {
                return m();
            }
            if (j()) {
                return a.a(this);
            }
        }
        return Collections.emptyList();
    }

    public int hashCode() {
        return c().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            return c().equals(((b) obj).c());
        }
        return false;
    }

    protected boolean o() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
        if (z) {
            a.put(c(), this);
            return;
        }
        a.remove(c());
        if ((this.c & 256) != 0) {
            f.c(this);
        }
    }

    public static void p() {
        a.clear();
    }
}
