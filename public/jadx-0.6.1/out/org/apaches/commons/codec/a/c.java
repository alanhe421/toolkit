package org.apaches.commons.codec.a;

import java.io.UnsupportedEncodingException;

/* compiled from: StringUtils */
public class c {
    private static IllegalStateException a(String str, UnsupportedEncodingException unsupportedEncodingException) {
        return new IllegalStateException(new StringBuilder(String.valueOf(str)).append(": ").append(unsupportedEncodingException).toString());
    }

    public static String a(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            throw a(str, e);
        }
    }

    public static String a(byte[] bArr) {
        return a(bArr, "UTF-8");
    }
}
