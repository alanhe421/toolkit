package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* compiled from: AbstractUnicodeExtraField */
public abstract class a implements r {
    private long a;
    private byte[] b;
    private byte[] c;

    protected a() {
    }

    private void a() {
        if (this.b != null) {
            this.c = new byte[(this.b.length + 5)];
            this.c[0] = (byte) 1;
            System.arraycopy(ZipLong.getBytes(this.a), 0, this.c, 1, 4);
            System.arraycopy(this.b, 0, this.c, 5, this.b.length);
        }
    }

    public byte[] getCentralDirectoryData() {
        if (this.c == null) {
            a();
        }
        if (this.c == null) {
            return null;
        }
        Object obj = new byte[this.c.length];
        System.arraycopy(this.c, 0, obj, 0, obj.length);
        return obj;
    }

    public ZipShort getCentralDirectoryLength() {
        if (this.c == null) {
            a();
        }
        return new ZipShort(this.c.length);
    }

    public byte[] getLocalFileDataData() {
        return getCentralDirectoryData();
    }

    public ZipShort getLocalFileDataLength() {
        return getCentralDirectoryLength();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 < 5) {
            throw new ZipException("UniCode path extra data must have at least 5 bytes.");
        }
        byte b = bArr[i];
        if (b != (byte) 1) {
            throw new ZipException("Unsupported version [" + b + "] for UniCode path extra data.");
        }
        this.a = ZipLong.getValue(bArr, i + 1);
        this.b = new byte[(i2 - 5)];
        System.arraycopy(bArr, i + 5, this.b, 0, i2 - 5);
        this.c = null;
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }
}
