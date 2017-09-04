package com.tencent.upload.b.a.a;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.http.util.EncodingUtils;

public abstract class e {
    protected static final byte[] a = EncodingUtils.getAsciiBytes("\"");
    private static final Log b = new c();
    private static byte[] c;
    private static final byte[] d;
    private static byte[] e = EncodingUtils.getAsciiBytes("\r\n");
    private static byte[] f = EncodingUtils.getAsciiBytes("--");
    private static byte[] g = EncodingUtils.getAsciiBytes("Content-Disposition: form-data; name=");
    private static byte[] h = EncodingUtils.getAsciiBytes("Content-Type: ");
    private static byte[] i = EncodingUtils.getAsciiBytes("; charset=");
    private static byte[] j = EncodingUtils.getAsciiBytes("Content-Transfer-Encoding: ");
    private byte[] k;

    static {
        byte[] asciiBytes = EncodingUtils.getAsciiBytes("----------------314159265358979323846");
        c = asciiBytes;
        d = asciiBytes;
    }

    public static long a(e[] eVarArr, byte[] bArr) {
        b.trace("getLengthOfParts(Parts[])");
        if (eVarArr == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        long j = 0;
        for (int i = 0; i < eVarArr.length; i++) {
            long j2;
            eVarArr[i].k = bArr;
            e eVar = eVarArr[i];
            b.trace("enter length()");
            if (eVar.a() < 0) {
                j2 = -1;
            } else {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                eVar.c(byteArrayOutputStream);
                eVar.a(byteArrayOutputStream);
                eVar.d(byteArrayOutputStream);
                eVar.e(byteArrayOutputStream);
                f(byteArrayOutputStream);
                g(byteArrayOutputStream);
                j2 = ((long) byteArrayOutputStream.size()) + eVar.a();
            }
            if (j2 < 0) {
                return -1;
            }
            j += j2;
        }
        return (((((long) f.length) + j) + ((long) bArr.length)) + ((long) f.length)) + ((long) e.length);
    }

    public static void a(OutputStream outputStream, e[] eVarArr, byte[] bArr) {
        if (eVarArr == null) {
            throw new IllegalArgumentException("Parts may not be null");
        } else if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("partBoundary may not be empty");
        } else {
            for (int i = 0; i < eVarArr.length; i++) {
                eVarArr[i].k = bArr;
                e eVar = eVarArr[i];
                b.trace("enter send(OutputStream out)");
                eVar.c(outputStream);
                eVar.a(outputStream);
                eVar.d(outputStream);
                eVar.e(outputStream);
                f(outputStream);
                eVar.b(outputStream);
                g(outputStream);
            }
            outputStream.write(f);
            outputStream.write(bArr);
            outputStream.write(f);
            outputStream.write(e);
        }
    }

    private void c(OutputStream outputStream) {
        b.trace("enter sendStart(OutputStream out)");
        outputStream.write(f);
        outputStream.write(this.k == null ? d : this.k);
        outputStream.write(e);
    }

    private void d(OutputStream outputStream) {
        b.trace("enter sendContentTypeHeader(OutputStream out)");
        String c = c();
        if (c != null) {
            outputStream.write(e);
            outputStream.write(h);
            outputStream.write(EncodingUtils.getAsciiBytes(c));
            c = d();
            if (c != null) {
                outputStream.write(i);
                outputStream.write(EncodingUtils.getAsciiBytes(c));
            }
        }
    }

    private void e(OutputStream outputStream) {
        b.trace("enter sendTransferEncodingHeader(OutputStream out)");
        String e = e();
        if (e != null) {
            outputStream.write(e);
            outputStream.write(j);
            outputStream.write(EncodingUtils.getAsciiBytes(e));
        }
    }

    private static void f(OutputStream outputStream) {
        b.trace("enter sendEndOfHeader(OutputStream out)");
        outputStream.write(e);
        outputStream.write(e);
    }

    public static boolean f() {
        return true;
    }

    private static void g(OutputStream outputStream) {
        b.trace("enter sendEnd(OutputStream out)");
        outputStream.write(e);
    }

    protected abstract long a();

    protected void a(OutputStream outputStream) {
        b.trace("enter sendDispositionHeader(OutputStream out)");
        outputStream.write(g);
        outputStream.write(a);
        outputStream.write(EncodingUtils.getAsciiBytes(b()));
        outputStream.write(a);
    }

    public abstract String b();

    protected abstract void b(OutputStream outputStream);

    public abstract String c();

    public abstract String d();

    public abstract String e();

    public String toString() {
        return b();
    }
}
