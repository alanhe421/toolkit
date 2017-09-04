package format.epub.common.core.a;

import format.epub.common.utils.j;

/* compiled from: ZLStringMap */
public final class c {
    private String[] a = new String[8];
    private String[] b = new String[8];
    private int c;

    public void a(String str, String str2) {
        int i = this.c;
        this.c = i + 1;
        String[] strArr = this.a;
        if (strArr.length == i) {
            strArr = j.a(strArr, i, i << 1);
            this.a = strArr;
            this.b = j.a(this.b, i, i << 1);
        }
        strArr[i] = str;
        this.b[i] = str2;
    }

    public String a(String str) {
        int i = this.c;
        if (i > 0) {
            String[] strArr = this.a;
            do {
                i--;
                if (i >= 0) {
                }
            } while (strArr[i] != str);
            return this.b[i];
        }
        return null;
    }

    public int a() {
        return this.c;
    }

    public String a(int i) {
        return this.a[i];
    }

    String b(int i) {
        return this.b[i];
    }

    public void b() {
        this.c = 0;
    }
}
