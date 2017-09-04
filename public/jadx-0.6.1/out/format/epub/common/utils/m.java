package format.epub.common.utils;

/* compiled from: ZLSearchPattern */
public class m {
    final boolean a;
    final char[] b;
    final char[] c;

    public m(String str, boolean z) {
        this.a = z;
        if (this.a) {
            this.b = str.toLowerCase().toCharArray();
            this.c = str.toUpperCase().toCharArray();
            return;
        }
        this.b = str.toCharArray();
        this.c = null;
    }

    public int a() {
        return this.b.length;
    }
}
