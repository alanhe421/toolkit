package com.yw.game.sdk.login.util.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;

/* compiled from: ResponseBody */
public class f implements Closeable {
    private final String a;
    private final long b;
    private final BufferedInputStream c;
    private HttpURLConnection d;

    public f(String str, long j, BufferedInputStream bufferedInputStream, HttpURLConnection httpURLConnection) {
        this.a = str;
        this.b = j;
        this.c = bufferedInputStream;
        this.d = httpURLConnection;
    }

    public void close() throws IOException {
        this.c.close();
        this.d.disconnect();
        this.d = null;
    }

    public byte[] a() throws IOException {
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = this.c.read(bArr);
                if (-1 == read) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            bArr = byteArrayOutputStream.toByteArray();
            return bArr;
        } finally {
            c.a(byteArrayOutputStream);
            c.a((Closeable) this);
        }
    }

    public final String b() throws IOException {
        return new String(a(), "UTF-8");
    }
}
