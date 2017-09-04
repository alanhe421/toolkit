package format.epub.common.utils;

/* compiled from: ZLArrayUtils */
public abstract class j {
    public static byte[] a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        if (i > 0) {
            System.arraycopy(bArr, 0, obj, 0, i);
        }
        return obj;
    }

    public static char[] a(char[] cArr, int i, int i2) {
        Object obj = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, obj, 0, i);
        }
        return obj;
    }

    public static String[] a(String[] strArr, int i, int i2) {
        Object obj = new String[i2];
        if (i > 0) {
            System.arraycopy(strArr, 0, obj, 0, i);
        }
        return obj;
    }
}
