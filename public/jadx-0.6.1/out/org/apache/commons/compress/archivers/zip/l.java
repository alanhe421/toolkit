package org.apache.commons.compress.archivers.zip;

/* compiled from: UnrecognizedExtraField */
public class l implements r {
    private ZipShort a;
    private byte[] b;
    private byte[] c;

    public void a(ZipShort zipShort) {
        this.a = zipShort;
    }

    public ZipShort getHeaderId() {
        return this.a;
    }

    public void a(byte[] bArr) {
        this.b = s.b(bArr);
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.b.length);
    }

    public byte[] getLocalFileDataData() {
        return s.b(this.b);
    }

    public void b(byte[] bArr) {
        this.c = s.b(bArr);
    }

    public ZipShort getCentralDirectoryLength() {
        if (this.c != null) {
            return new ZipShort(this.c.length);
        }
        return getLocalFileDataLength();
    }

    public byte[] getCentralDirectoryData() {
        if (this.c != null) {
            return s.b(this.c);
        }
        return getLocalFileDataData();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        a(bArr2);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        b(bArr2);
        if (this.b == null) {
            a(bArr2);
        }
    }
}
