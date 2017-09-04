package format.epub.common.book;

import java.util.HashMap;

/* compiled from: Tag */
public final class b {
    private static final HashMap<b, b> c = new HashMap();
    public final b a;
    public final String b;

    public static b a(b bVar, String str) {
        if (str == null) {
            return bVar;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return bVar;
        }
        b bVar2 = new b(bVar, trim);
        b bVar3 = (b) c.get(bVar2);
        if (bVar3 != null) {
            return bVar3;
        }
        c.put(bVar2, bVar2);
        return bVar2;
    }

    private b(b bVar, String str) {
        this.a = bVar;
        this.b = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a == bVar.a && this.b.equals(bVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a == null ? this.b.hashCode() : this.a.hashCode() + this.b.hashCode();
    }
}
