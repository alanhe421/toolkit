package com.sijla.f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class a {
    private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public static String a(byte[] bArr) {
        int length = bArr.length;
        StringBuilder stringBuilder = new StringBuilder((bArr.length * 3) / 2);
        int i = length - 3;
        int i2 = 0;
        int i3 = 0;
        while (i3 <= i) {
            int i4 = (((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8)) | (bArr[i3 + 2] & 255);
            stringBuilder.append(a[(i4 >> 18) & 63]);
            stringBuilder.append(a[(i4 >> 12) & 63]);
            stringBuilder.append(a[(i4 >> 6) & 63]);
            stringBuilder.append(a[i4 & 63]);
            i4 = i3 + 3;
            i3 = i2 + 1;
            if (i2 >= 14) {
                stringBuilder.append(" ");
                i3 = 0;
            }
            i2 = i3;
            i3 = i4;
        }
        if (i3 == (0 + length) - 2) {
            i3 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16);
            stringBuilder.append(a[(i3 >> 18) & 63]);
            stringBuilder.append(a[(i3 >> 12) & 63]);
            stringBuilder.append(a[(i3 >> 6) & 63]);
            stringBuilder.append("=");
        } else if (i3 == (0 + length) - 1) {
            i3 = (bArr[i3] & 255) << 16;
            stringBuilder.append(a[(i3 >> 18) & 63]);
            stringBuilder.append(a[(i3 >> 12) & 63]);
            stringBuilder.append("==");
        }
        return stringBuilder.toString();
    }

    private static int a(char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 65;
        }
        if (c >= 'a' && c <= 'z') {
            return (c - 97) + 26;
        }
        if (c >= '0' && c <= '9') {
            return ((c - 48) + 26) + 26;
        }
        switch (c) {
            case '+':
                return 62;
            case '/':
                return 63;
            case '=':
                return 0;
            default:
                throw new RuntimeException("unexpected code: " + c);
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = null;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(str, byteArrayOutputStream);
            bArr = byteArrayOutputStream.toByteArray();
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                }
            }
        }
        return bArr;
    }

    private static void a(String str, OutputStream outputStream) {
        int i = 0;
        int length = str.length();
        while (true) {
            if (i < length && str.charAt(i) <= ' ') {
                i++;
            } else if (i != length) {
                int a = (((a(str.charAt(i)) << 18) + (a(str.charAt(i + 1)) << 12)) + (a(str.charAt(i + 2)) << 6)) + a(str.charAt(i + 3));
                outputStream.write((a >> 16) & 255);
                if (str.charAt(i + 2) != '=') {
                    outputStream.write((a >> 8) & 255);
                    if (str.charAt(i + 3) != '=') {
                        outputStream.write(a & 255);
                        i += 4;
                    } else {
                        return;
                    }
                }
                return;
            } else {
                return;
            }
        }
    }
}
