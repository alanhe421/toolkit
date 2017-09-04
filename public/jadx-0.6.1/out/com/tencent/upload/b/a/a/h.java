package com.tencent.upload.b.a.a;

import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.http.util.EncodingUtils;

public class h extends f {
    private static final Log b = new c();
    private byte[] c;
    private String d;

    public h(String str, String str2) {
        this(str, str2, null);
    }

    private h(String str, String str2, String str3) {
        super(str, "text/plain", "US-ASCII", "8bit");
        if (str2 == null) {
            throw new IllegalArgumentException("Value may not be null");
        } else if (str2.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        } else {
            this.d = str2;
        }
    }

    private byte[] g() {
        if (this.c == null) {
            this.c = EncodingUtils.getBytes(this.d, d());
        }
        return this.c;
    }

    protected final long a() {
        b.trace("enter lengthOfData()");
        return (long) g().length;
    }

    protected final void b(OutputStream outputStream) {
        b.trace("enter sendData(OutputStream)");
        outputStream.write(g());
    }
}
