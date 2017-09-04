package format.epub.common.utils;

/* compiled from: ZLSearchUtil */
public abstract class n {
    public static int a(char[] cArr, int i, int i2, m mVar) {
        return a(cArr, i, i2, mVar, 0);
    }

    public static int a(char[] cArr, int i, int i2, m mVar, int i3) {
        if (i3 < 0) {
            i3 = 0;
        }
        char[] cArr2 = mVar.b;
        int length = cArr2.length;
        int i4 = (i + i2) - length;
        int a;
        if (mVar.a) {
            a = c.a(cArr, cArr2, i3, i, i2);
            if (a != -1) {
                return a - i;
            }
        }
        char c = cArr2[0];
        for (int i5 = i + i3; i5 <= i4; i5++) {
            if (cArr[i5] == c) {
                int i6 = 1;
                a = i5 + 1;
                while (i6 < length && cArr2[i6] == cArr[a]) {
                    i6++;
                    a++;
                }
                if (i6 >= length) {
                    return i5 - i;
                }
            }
        }
        return -1;
    }
}
