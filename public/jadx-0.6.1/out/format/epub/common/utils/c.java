package format.epub.common.utils;

/* compiled from: KMPSearchUtils */
public class c {
    public static int[] a(char[] cArr) {
        int i;
        int[] iArr = new int[cArr.length];
        for (i = 0; i < cArr.length; i++) {
            iArr[i] = 0;
        }
        iArr[0] = -1;
        for (i = 1; i < iArr.length; i++) {
            char c = cArr[i - 1];
            for (int i2 = i - 1; i2 >= 0; i2 = iArr[i2]) {
                int i3 = iArr[i2];
                if (i3 == -1 || c == cArr[i3]) {
                    iArr[i] = i3 + 1;
                    break;
                }
            }
        }
        return iArr;
    }

    public static int a(char[] cArr, char[] cArr2, int i, int i2, int i3) {
        if (cArr == null || cArr.length == 0 || cArr2 == null || cArr2.length == 0) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        int[] a = a(cArr2);
        int length = cArr2.length;
        int i4 = 0;
        int i5 = i2 + i;
        while (i5 < i2 + i3) {
            if (i4 == -1 || cArr[i5] == cArr2[i4]) {
                i5++;
                i4++;
                if (i4 == length) {
                    return i5 - length;
                }
            } else {
                i4 = a[i4];
            }
        }
        return -1;
    }
}
