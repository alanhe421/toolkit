package org.apache.a.a;

import java.util.zip.ZipException;

/* compiled from: JarMarker */
public final class c implements f {
    private static final j a = new j(51966);
    private static final j b = new j(0);
    private static final byte[] c = new byte[0];
    private static final c d = new c();

    public j a() {
        return a;
    }

    public j b() {
        return b;
    }

    public j c() {
        return b;
    }

    public byte[] d() {
        return c;
    }

    public byte[] e() {
        return c;
    }

    public void a(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 != 0) {
            throw new ZipException("JarMarker doesn't expect any data");
        }
    }
}
