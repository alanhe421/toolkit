package com.sijla.j.b;

import com.sijla.j.b.a.a.a;
import java.util.Random;

public class b {
    public static String a(String str) {
        String stringBuilder;
        Exception e;
        String str2 = "";
        try {
            StringBuilder stringBuilder2 = new StringBuilder();
            String a = a(8);
            stringBuilder2.append(b(a, "yoT2EXOyrvuPcbkUpAeYpkVxrBIBKhHJRg3s_hAayePTDtJCz-MDs1NF-54_jLQds4_jvB809t4130oO2yZIeOCCyDTaKO3gi0ZxjviiWtgHz_OO6knr7e29JNi7_IYZG8Iz21UByMdkiPeGU0XeS5XAqgrsDs_yY8UQvv6wvz_VYCq50zpIvlMOlucqgLwweZlbryAz8GXR6uAzsRowCj_Ms236MbzQ"));
            String str3 = "$";
            int length = str.length();
            int i = length / 400;
            int i2 = length % 400;
            for (int i3 = 0; i3 < i; i3++) {
                stringBuilder2.append(a(str.substring(i3 * 400, (i3 + 1) * 400), a)).append(str3);
            }
            stringBuilder2.append(a(str.substring(length - i2), a));
            stringBuilder = stringBuilder2.toString();
            try {
                if (stringBuilder.endsWith(str3)) {
                    stringBuilder = stringBuilder.substring(0, stringBuilder.lastIndexOf(str3));
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return stringBuilder;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            stringBuilder = str2;
            e = exception;
            e.printStackTrace();
            return stringBuilder;
        }
        return stringBuilder;
    }

    private static String a(String str, String str2) {
        return a.b(a.a(str.getBytes(), str2));
    }

    private static String b(String str, String str2) {
        return str.substring(0, 3) + ((char) a.a(str2, "Tvb!@#RS".getBytes())[0]) + str.substring(3);
    }

    private static String a(int i) {
        int i2 = 0;
        char[] cArr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer stringBuffer = new StringBuffer("");
        Random random = new Random();
        while (i2 < i) {
            int abs = Math.abs(random.nextInt(36));
            if (abs >= 0 && abs < cArr.length) {
                stringBuffer.append(cArr[abs]);
                i2++;
            }
        }
        return stringBuffer.toString();
    }

    public static String b(String str) {
        return new String(a.a(str.getBytes("UTF-8")), "UTF-8");
    }
}
