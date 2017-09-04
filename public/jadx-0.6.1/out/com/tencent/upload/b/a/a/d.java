package com.tencent.upload.b.a.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

public class d extends AbstractHttpEntity {
    private static final Log a = new c();
    private static byte[] b = EncodingUtils.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private e[] c;
    private byte[] d;
    private HttpParams e;
    private boolean f = false;

    public d(e[] eVarArr) {
        setContentType("multipart/form-data");
        if (eVarArr == null) {
            throw new IllegalArgumentException("parts cannot be null");
        }
        this.c = eVarArr;
        this.e = null;
    }

    private byte[] a() {
        if (this.d == null) {
            Random random = new Random();
            byte[] bArr = new byte[(random.nextInt(11) + 30)];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = b[random.nextInt(b.length)];
            }
            this.d = bArr;
        }
        return this.d;
    }

    public InputStream getContent() {
        if (isRepeatable() || !this.f) {
            this.f = true;
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            e.a(byteArrayOutputStream, this.c, this.d);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
        throw new IllegalStateException("Content has been consumed");
    }

    public long getContentLength() {
        try {
            return e.a(this.c, a());
        } catch (Throwable e) {
            a.error("An exception occurred while getting the length of the parts", e);
            return 0;
        }
    }

    public Header getContentType() {
        StringBuffer stringBuffer = new StringBuffer("multipart/form-data");
        stringBuffer.append("; boundary=");
        stringBuffer.append(EncodingUtils.getAsciiString(a()));
        return new BasicHeader("Content-Type", stringBuffer.toString());
    }

    public boolean isRepeatable() {
        for (int i = 0; i < this.c.length; i++) {
            e[] eVarArr = this.c;
            e.f();
        }
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        e.a(outputStream, this.c, a());
    }
}
