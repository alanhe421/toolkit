package format.epub.common.utils;

import format.epub.common.b.b;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: MiscUtil */
public class d {
    public static String a(b bVar) {
        String k = bVar.k();
        String c = bVar.c();
        return c.substring(0, c.length() - k.length());
    }

    public static String a(String str) {
        int lastIndexOf = str.lastIndexOf(58);
        return lastIndexOf >= 2 ? str.substring(lastIndexOf + 1) : str;
    }

    private static boolean a(char c) {
        return (c >= '0' && c <= '9') || ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        while (true) {
            int indexOf = str.indexOf(37, i);
            if (indexOf == -1 || indexOf >= str.length() - 2) {
                return str;
            }
            if (a(str.charAt(indexOf + 1)) && a(str.charAt(indexOf + 2))) {
                char intValue;
                try {
                    intValue = (char) Integer.decode("0x" + str.substring(indexOf + 1, indexOf + 3)).intValue();
                } catch (NumberFormatException e) {
                    intValue = '\u0000';
                }
                str = str.substring(0, indexOf) + intValue + str.substring(indexOf + 3);
            }
            i = indexOf + 1;
        }
    }

    public static <T> boolean a(T t, T t2) {
        if (t == null) {
            return t2 == null;
        } else {
            return t.equals(t2);
        }
    }

    public static List<String> c(String str) {
        List<String> linkedList = new LinkedList();
        Matcher matcher = Pattern.compile("([^\"\\s:;]+|\".+?\")").matcher(str);
        while (matcher.find()) {
            linkedList.add(matcher.group(1).replace("\"", ""));
        }
        return linkedList;
    }
}
