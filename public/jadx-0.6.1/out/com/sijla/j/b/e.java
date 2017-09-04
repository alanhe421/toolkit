package com.sijla.j.b;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class e {
    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes("UTF-8"));
        gZIPOutputStream.close();
        String str2 = new String(byteArrayOutputStream.toByteArray(), "ISO-8859-1");
        byteArrayOutputStream.close();
        return str2;
    }
}
