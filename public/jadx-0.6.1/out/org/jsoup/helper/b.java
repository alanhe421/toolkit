package org.jsoup.helper;

import com.tencent.av.utils.QLog;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: StringUtil */
public final class b {
    private static final String[] a = new String[]{"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", QLog.SPLIT};

    public static String a(Collection collection, String str) {
        return a(collection.iterator(), str);
    }

    public static String a(Iterator it, String str) {
        if (!it.hasNext()) {
            return "";
        }
        String obj = it.next().toString();
        if (!it.hasNext()) {
            return obj;
        }
        StringBuilder append = new StringBuilder(64).append(obj);
        while (it.hasNext()) {
            append.append(str);
            append.append(it.next());
        }
        return append.toString();
    }

    public static String a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("width must be > 0");
        } else if (i < a.length) {
            return a[i];
        } else {
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < i; i2++) {
                cArr[i2] = ' ';
            }
            return String.valueOf(cArr);
        }
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!b(str.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(int i) {
        return i == 32 || i == 9 || i == 10 || i == 12 || i == 13;
    }

    public static void a(StringBuilder stringBuilder, String str, boolean z) {
        int length = str.length();
        int i = 0;
        Object obj = null;
        Object obj2 = null;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (!b(codePointAt)) {
                stringBuilder.appendCodePoint(codePointAt);
                int i2 = 1;
                obj2 = null;
            } else if (!(z && r0 == null) && r2 == null) {
                stringBuilder.append(' ');
                int i3 = 1;
            }
            i += Character.charCount(codePointAt);
        }
    }

    public static boolean a(String str, String... strArr) {
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str, String[] strArr) {
        return Arrays.binarySearch(strArr, str) >= 0;
    }

    public static URL a(URL url, String str) throws MalformedURLException {
        if (str.startsWith("?")) {
            str = url.getPath() + str;
        }
        if (str.indexOf(46) == 0 && url.getFile().indexOf(47) != 0) {
            url = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/" + url.getFile());
        }
        return new URL(url, str);
    }

    public static String a(String str, String str2) {
        try {
            try {
                return a(new URL(str), str2).toExternalForm();
            } catch (MalformedURLException e) {
                return "";
            }
        } catch (MalformedURLException e2) {
            return new URL(str2).toExternalForm();
        }
    }
}
