package format.epub.common.utils;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ZLStringUtil */
public class o {
    public static String a(String str) {
        return str.trim();
    }

    public static List<String> a(String str, String str2, boolean z) {
        List<String> arrayList = new ArrayList();
        int i = 0;
        int indexOf = str.indexOf(str2);
        while (indexOf != -1) {
            String substring = str.substring(i, (i + indexOf) - i);
            if (!z || substring.length() > 0) {
                arrayList.add(substring);
            }
            i = str2.length() + indexOf;
            indexOf = str.indexOf(str2, i);
        }
        String substring2 = str.substring(i);
        if (!z || substring2.length() > 0) {
            arrayList.add(substring2);
        }
        return arrayList;
    }

    public static boolean a(String str, String str2) {
        return str != null && str.endsWith(str2);
    }

    public static boolean b(String str, String str2) {
        return str != null && str.startsWith(str2);
    }

    public static short b(String str) {
        short s = (short) 0;
        if (!(str == null || str.trim().length() == 0)) {
            String trim = str.trim();
            int length = trim.length();
            int i = 0;
            while (i < length) {
                char charAt = trim.charAt(i);
                if ((i != 0 || (charAt != '+' && charAt != '-')) && (charAt < '0' || charAt > '9')) {
                    break;
                }
                i++;
            }
            i = length;
            try {
                if (trim.charAt(0) == '+') {
                    trim = trim.substring(1);
                    i--;
                }
                s = Short.parseShort(trim.substring(0, i));
            } catch (Exception e) {
            }
        }
        return s;
    }

    public static int a(String str, int i) {
        int i2 = 1;
        if (str == null || str.length() == 0) {
            return i;
        }
        if (!Character.isDigit(str.charAt(0)) && (str.length() == 1 || str.charAt(0) != '-' || !Character.isDigit(str.charAt(1)))) {
            return i;
        }
        while (i2 < str.length()) {
            if (!Character.isDigit(str.charAt(i2))) {
                return i;
            }
            i2++;
        }
        return b(str);
    }
}
