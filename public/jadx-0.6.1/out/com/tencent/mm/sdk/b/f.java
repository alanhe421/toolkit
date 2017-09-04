package com.tencent.mm.sdk.b;

import java.util.TimeZone;

public final class f {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final long[] t = new long[]{300, 200, 300, 200};
    private static final long[] u = new long[]{300, 50, 300, 50};
    private static final char[] v = new char[]{'<', '>', '\"', '\'', '&', '\r', '\n', ' ', '\t'};
    private static final String[] w = new String[]{"&lt;", "&gt;", "&quot;", "&apos;", "&amp;", "&#x0D;", "&#x0A;", "&#x20;", "&#x09;"};

    public static boolean c(String str) {
        return str == null || str.length() <= 0;
    }
}
