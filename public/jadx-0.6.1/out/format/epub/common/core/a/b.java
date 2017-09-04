package format.epub.common.core.a;

import format.epub.common.utils.j;

/* compiled from: ZLMutableString */
final class b {
    char[] a;
    int b;

    b(int i) {
        this.a = new char[i];
    }

    b() {
        this(20);
    }

    b(b bVar) {
        int i = bVar.b;
        this.a = j.a(bVar.a, i, i);
        this.b = i;
    }

    public void a(char[] cArr, int i, int i2) {
        int i3 = this.b;
        Object obj = this.a;
        int i4 = i3 + i2;
        if (obj.length < i4) {
            obj = j.a((char[]) obj, i3, i4);
            this.a = obj;
        }
        System.arraycopy(cArr, i, obj, i3, i2);
        this.b = i4;
    }

    public void a() {
        this.b = 0;
    }

    public boolean equals(Object obj) {
        b bVar = (b) obj;
        int i = this.b;
        if (i != bVar.b) {
            return false;
        }
        char[] cArr = this.a;
        char[] cArr2 = bVar.a;
        do {
            i--;
            if (i < 0) {
                return true;
            }
        } while (cArr[i] == cArr2[i]);
        return false;
    }

    public int hashCode() {
        int i = this.b;
        char[] cArr = this.a;
        int i2 = i * 31;
        if (i > 1) {
            i2 = ((i2 + cArr[0]) * 31) + cArr[1];
            if (i > 2) {
                return (i2 * 31) + cArr[2];
            }
            return i2;
        } else if (i > 0) {
            return i2 + cArr[0];
        } else {
            return i2;
        }
    }

    public String toString() {
        return new String(this.a, 0, this.b).intern();
    }
}
