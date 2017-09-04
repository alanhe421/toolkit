package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.zip.ZipException;

public class X7875_NewUnix implements Serializable, Cloneable, r {
    private static final ZipShort HEADER_ID = new ZipShort(30837);
    private static final BigInteger ONE_THOUSAND = BigInteger.valueOf(1000);
    private static final long serialVersionUID = 1;
    private BigInteger gid;
    private BigInteger uid;
    private int version = 1;

    public X7875_NewUnix() {
        reset();
    }

    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    public long getUID() {
        return s.a(this.uid);
    }

    public long getGID() {
        return s.a(this.gid);
    }

    public void setUID(long j) {
        this.uid = s.a(j);
    }

    public void setGID(long j) {
        this.gid = s.a(j);
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort((trimLeadingZeroesForceMinLength(this.uid.toByteArray()).length + 3) + trimLeadingZeroesForceMinLength(this.gid.toByteArray()).length);
    }

    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public byte[] getLocalFileDataData() {
        byte[] toByteArray = this.uid.toByteArray();
        byte[] toByteArray2 = this.gid.toByteArray();
        toByteArray = trimLeadingZeroesForceMinLength(toByteArray);
        toByteArray2 = trimLeadingZeroesForceMinLength(toByteArray2);
        Object obj = new byte[((toByteArray.length + 3) + toByteArray2.length)];
        s.a(toByteArray);
        s.a(toByteArray2);
        obj[0] = s.b(this.version);
        obj[1] = s.b(toByteArray.length);
        System.arraycopy(toByteArray, 0, obj, 2, toByteArray.length);
        int length = toByteArray.length + 2;
        int i = length + 1;
        obj[length] = s.b(toByteArray2.length);
        System.arraycopy(toByteArray2, 0, obj, i, toByteArray2.length);
        return obj;
    }

    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        int i3 = i + 1;
        this.version = s.a(bArr[i]);
        int i4 = i3 + 1;
        i3 = s.a(bArr[i3]);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i4, bArr2, 0, i3);
        i3 += i4;
        this.uid = new BigInteger(1, s.a(bArr2));
        i4 = i3 + 1;
        i3 = s.a(bArr[i3]);
        bArr2 = new byte[i3];
        System.arraycopy(bArr, i4, bArr2, 0, i3);
        this.gid = new BigInteger(1, s.a(bArr2));
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        reset();
        parseFromLocalFileData(bArr, i, i2);
    }

    private void reset() {
        this.uid = ONE_THOUSAND;
        this.gid = ONE_THOUSAND;
    }

    public String toString() {
        return "0x7875 Zip Extra Field: UID=" + this.uid + " GID=" + this.gid;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof X7875_NewUnix)) {
            return false;
        }
        X7875_NewUnix x7875_NewUnix = (X7875_NewUnix) obj;
        if (this.version == x7875_NewUnix.version && this.uid.equals(x7875_NewUnix.uid) && this.gid.equals(x7875_NewUnix.gid)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((-1234567 * this.version) ^ Integer.rotateLeft(this.uid.hashCode(), 16)) ^ this.gid.hashCode();
    }

    static byte[] trimLeadingZeroesForceMinLength(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return bArr;
        }
        int length = bArr.length;
        int i2 = 0;
        while (i < length && bArr[i] == (byte) 0) {
            i2++;
            i++;
        }
        Object obj = new byte[Math.max(1, bArr.length - i2)];
        length = obj.length - (bArr.length - i2);
        System.arraycopy(bArr, i2, obj, length, obj.length - length);
        return obj;
    }
}
