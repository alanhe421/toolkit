package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* compiled from: Zip64ExtendedInformationExtraField */
public class m implements r {
    static final ZipShort a = new ZipShort(1);
    private static final byte[] b = new byte[0];
    private ZipEightByteInteger c;
    private ZipEightByteInteger d;
    private ZipEightByteInteger e;
    private ZipLong f;
    private byte[] g;

    public ZipShort getHeaderId() {
        return a;
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.c != null ? 16 : 0);
    }

    public ZipShort getCentralDirectoryLength() {
        int i = 8;
        int i2 = 0;
        int i3 = (this.c != null ? 8 : 0) + (this.d != null ? 8 : 0);
        if (this.e == null) {
            i = 0;
        }
        i3 += i;
        if (this.f != null) {
            i2 = 4;
        }
        return new ZipShort(i3 + i2);
    }

    public byte[] getLocalFileDataData() {
        if (this.c == null && this.d == null) {
            return b;
        }
        if (this.c == null || this.d == null) {
            throw new IllegalArgumentException("Zip64 extended information must contain both size values in the local file header.");
        }
        byte[] bArr = new byte[16];
        a(bArr);
        return bArr;
    }

    public byte[] getCentralDirectoryData() {
        Object obj = new byte[getCentralDirectoryLength().getValue()];
        int a = a(obj);
        if (this.e != null) {
            System.arraycopy(this.e.getBytes(), 0, obj, a, 8);
            a += 8;
        }
        if (this.f != null) {
            System.arraycopy(this.f.getBytes(), 0, obj, a, 4);
            a += 4;
        }
        return obj;
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 != 0) {
            if (i2 < 16) {
                throw new ZipException("Zip64 extended information must contain both size values in the local file header.");
            }
            this.c = new ZipEightByteInteger(bArr, i);
            int i3 = i + 8;
            this.d = new ZipEightByteInteger(bArr, i3);
            int i4 = i3 + 8;
            i3 = i2 - 16;
            if (i3 >= 8) {
                this.e = new ZipEightByteInteger(bArr, i4);
                i4 += 8;
                i3 -= 8;
            }
            if (i3 >= 4) {
                this.f = new ZipLong(bArr, i4);
                i4 += 4;
                i3 -= 4;
            }
        }
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        this.g = new byte[i2];
        System.arraycopy(bArr, i, this.g, 0, i2);
        if (i2 >= 28) {
            parseFromLocalFileData(bArr, i, i2);
        } else if (i2 == 24) {
            this.c = new ZipEightByteInteger(bArr, i);
            int i3 = i + 8;
            this.d = new ZipEightByteInteger(bArr, i3);
            this.e = new ZipEightByteInteger(bArr, i3 + 8);
        } else if (i2 % 8 == 4) {
            this.f = new ZipLong(bArr, (i + i2) - 4);
        }
    }

    private int a(byte[] bArr) {
        int i;
        if (this.c != null) {
            System.arraycopy(this.c.getBytes(), 0, bArr, 0, 8);
            i = 8;
        } else {
            i = 0;
        }
        if (this.d == null) {
            return i;
        }
        System.arraycopy(this.d.getBytes(), 0, bArr, i, 8);
        return i + 8;
    }
}
