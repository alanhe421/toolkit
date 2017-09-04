package format.epub.common.text.model;

/* compiled from: ZLTextMetrics */
public final class h {
    public int a;
    public int b;
    public int c;
    public int d;

    public h(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (this.a == hVar.a && this.b == hVar.b && this.c == hVar.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a + ((this.c + (this.b * 13)) * 13);
    }
}
