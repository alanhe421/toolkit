package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* compiled from: JarMarker */
public final class f implements r {
    private static final ZipShort a = new ZipShort(51966);
    private static final ZipShort b = new ZipShort(0);
    private static final byte[] c = new byte[0];
    private static final f d = new f();

    public ZipShort getHeaderId() {
        return a;
    }

    public ZipShort getLocalFileDataLength() {
        return b;
    }

    public ZipShort getCentralDirectoryLength() {
        return b;
    }

    public byte[] getLocalFileDataData() {
        return c;
    }

    public byte[] getCentralDirectoryData() {
        return c;
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 != 0) {
            throw new ZipException("JarMarker doesn't expect any data");
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }
}
