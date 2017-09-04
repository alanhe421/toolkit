package com.tencent.upload.b.a.a;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.http.util.EncodingUtils;

public class a extends f {
    private static final Log b = new c();
    private static final byte[] c = EncodingUtils.getAsciiBytes("; filename=");
    private g d;

    private a(String str, g gVar, String str2, String str3) {
        super(str, "application/octet-stream", "ISO-8859-1", "binary");
        if (gVar == null) {
            throw new IllegalArgumentException("Source may not be null");
        }
        this.d = gVar;
    }

    public a(String str, String str2, File file) {
        this(str, new g(str2, file), null, null);
    }

    protected final long a() {
        b.trace("enter lengthOfData()");
        return this.d.a();
    }

    protected final void a(OutputStream outputStream) {
        b.trace("enter sendDispositionHeader(OutputStream out)");
        super.a(outputStream);
        String b = this.d.b();
        if (b != null) {
            outputStream.write(c);
            outputStream.write(a);
            outputStream.write(EncodingUtils.getAsciiBytes(b));
            outputStream.write(a);
        }
    }

    protected final void b(OutputStream outputStream) {
        b.trace("enter sendData(OutputStream out)");
        if (a() == 0) {
            b.debug("No data to send.");
            return;
        }
        byte[] bArr = new byte[4096];
        InputStream c = this.d.c();
        while (true) {
            try {
                int read = c.read(bArr);
                if (read < 0) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            } finally {
                c.close();
            }
        }
    }
}
