package com.sijla.j.b.a.a;

import com.sijla.j.b.a.a;
import java.nio.charset.Charset;

public class c {
    private static byte[] a(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }

    public static byte[] a(String str) {
        return a(str, a.f);
    }

    private static String a(byte[] bArr, Charset charset) {
        return bArr == null ? null : new String(bArr, charset);
    }

    public static String a(byte[] bArr) {
        return a(bArr, a.f);
    }
}
