package com.qq.reader.common.utils.a;

/* compiled from: Base64 */
public class a {
    static byte[] a = new byte[64];
    static String b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    static {
        for (int i = 0; i < 64; i++) {
            a[i] = (byte) b.charAt(i);
        }
    }

    public static String a(String str) {
        return a(str.getBytes());
    }

    public static String a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static String a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[((((i2 + 2) / 3) * 4) + (i2 / 72))];
        int i3 = i2 + i;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i < i3) {
            int i8;
            byte b = bArr[i];
            i6++;
            switch (i6) {
                case 1:
                    i8 = i7 + 1;
                    bArr2[i7] = a[(b >> 2) & 63];
                    i7 = i6;
                    break;
                case 2:
                    i8 = i7 + 1;
                    bArr2[i7] = a[((i5 << 4) & 48) | ((b >> 4) & 15)];
                    i7 = i6;
                    break;
                case 3:
                    i6 = i7 + 1;
                    bArr2[i7] = a[((i5 << 2) & 60) | ((b >> 6) & 3)];
                    i8 = i6 + 1;
                    bArr2[i6] = a[b & 63];
                    i7 = 0;
                    break;
                default:
                    i8 = i7;
                    i7 = i6;
                    break;
            }
            i6 = i4 + 1;
            if (i6 >= 72) {
                i6 = i8 + 1;
                bArr2[i8] = (byte) 10;
                i8 = i6;
                i6 = 0;
            }
            i++;
            i4 = i6;
            byte b2 = b;
            i6 = i7;
            i7 = i8;
        }
        int i9;
        switch (i6) {
            case 1:
                i6 = i7 + 1;
                bArr2[i7] = a[(i5 << 4) & 48];
                i9 = i6 + 1;
                bArr2[i6] = (byte) 61;
                i6 = i9 + 1;
                bArr2[i9] = (byte) 61;
                break;
            case 2:
                i6 = i7 + 1;
                bArr2[i7] = a[(i5 << 2) & 60];
                i9 = i6 + 1;
                bArr2[i6] = (byte) 61;
                break;
        }
        return new String(bArr2);
    }

    public static byte[] b(String str) {
        int i;
        if (str.endsWith("=")) {
            i = 1;
        } else {
            i = 0;
        }
        if (str.endsWith("==")) {
            i++;
        }
        byte[] bArr = new byte[((((str.length() + 3) / 4) * 3) - i)];
        int i2 = 0;
        i = 0;
        while (i2 < str.length()) {
            try {
                int indexOf = b.indexOf(str.charAt(i2));
                if (indexOf == -1) {
                    return bArr;
                }
                int i3;
                switch (i2 % 4) {
                    case 0:
                        bArr[i] = (byte) (indexOf << 2);
                        break;
                    case 1:
                        i3 = i + 1;
                        bArr[i] = (byte) (bArr[i] | ((byte) ((indexOf >> 4) & 3)));
                        bArr[i3] = (byte) (indexOf << 4);
                        i = i3;
                        break;
                    case 2:
                        i3 = i + 1;
                        bArr[i] = (byte) (bArr[i] | ((byte) ((indexOf >> 2) & 15)));
                        bArr[i3] = (byte) (indexOf << 6);
                        i = i3;
                        break;
                    case 3:
                        i3 = i + 1;
                        bArr[i] = (byte) (((byte) (indexOf & 63)) | bArr[i]);
                        i = i3;
                        break;
                    default:
                        break;
                }
                i2++;
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return bArr;
    }
}
