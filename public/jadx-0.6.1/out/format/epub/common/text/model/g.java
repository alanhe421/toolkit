package format.epub.common.text.model;

/* compiled from: ZLTextMark */
public class g implements Comparable<g> {
    public final int a;
    public final int b;
    public final int c;

    public /* synthetic */ int compareTo(Object obj) {
        return a((g) obj);
    }

    public g(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public int a(g gVar) {
        int i = this.a - gVar.a;
        return i != 0 ? i : this.b - gVar.b;
    }

    public String toString() {
        return this.a + " " + this.b + " " + this.c;
    }
}
