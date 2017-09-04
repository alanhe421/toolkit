package format.epub.common.book;

/* compiled from: Author */
public final class a {
    public final String a;
    public final String b;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b.equals(aVar.b) && this.a.equals(aVar.a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.b.hashCode() + this.a.hashCode();
    }
}
