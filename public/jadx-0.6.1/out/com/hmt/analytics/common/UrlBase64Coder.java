package com.hmt.analytics.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apaches.commons.codec.a.a;

public class UrlBase64Coder {
    public static String encoded(String str) throws UnsupportedEncodingException {
        return new String(a.a(str.getBytes("UTF-8")), "UTF-8");
    }

    public static String compress(String str) throws IOException {
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

    public static String uncompress(String str) throws IOException {
        byte[] bytes = str.getBytes("ISO-8859-1");
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        bytes = new byte[256];
        while (true) {
            int read = gZIPInputStream.read(bytes);
            if (read < 0) {
                String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                return str2;
            }
            byteArrayOutputStream.write(bytes, 0, read);
        }
    }

    public static String compressDes(String str) throws IOException {
        return DESUtil.encode(HMTConstants.l, compress(str));
    }

    public static String uncompressDes(String str) throws IOException {
        return uncompress(DESUtil.decode(HMTConstants.l, str));
    }
}
