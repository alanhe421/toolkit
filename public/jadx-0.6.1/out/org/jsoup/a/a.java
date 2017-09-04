package org.jsoup.a;

import java.util.Locale;

/* compiled from: Normalizer */
public class a {
    public static String a(String str) {
        return str.toLowerCase(Locale.ENGLISH);
    }

    public static String b(String str) {
        return a(str).trim();
    }
}
