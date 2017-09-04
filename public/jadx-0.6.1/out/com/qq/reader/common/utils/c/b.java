package com.qq.reader.common.utils.c;

import com.google.zxing.common.StringUtils;
import com.qq.reader.common.monitor.f;

/* compiled from: Encoder */
public class b {
    private static a a = null;

    public static void a() {
        try {
            a.a();
            a = new a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int a(byte[] bArr) {
        int i = 0;
        try {
            if (a == null) {
                a();
            }
            if (!(bArr == null || bArr.length == 0)) {
                i = a.a(bArr);
            }
        } catch (Exception e) {
            f.b("ENCODING", "checkCharset 3");
            e.printStackTrace();
        }
        return i;
    }

    public static String a(int i) {
        if (i <= 0) {
            return null;
        }
        switch (i) {
            case 1:
            case 100:
                return "UTF-16LE";
            case 2:
            case 14:
                return "GBK";
            case 4:
            case 10:
                return "UTF-8";
            case 8:
                return "UTF-16BE";
            case 12:
                return StringUtils.GB2312;
            default:
                return null;
        }
    }

    public static String a(byte[] bArr, int i) {
        if (i > 0 && bArr != null) {
            try {
                if (bArr.length != 0) {
                    String a = a(i);
                    if (a != null) {
                        return a(bArr, bArr.length, a);
                    }
                    return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return "";
    }

    private static String a(byte[] bArr, int i, String str) {
        if (i == 0) {
            return null;
        }
        try {
            return new String(bArr, 0, i, str);
        } catch (Exception e) {
            f.b("Encode Exception : ", e.toString());
            return "";
        }
    }
}
