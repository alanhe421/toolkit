package org.apache.commons.compress.archivers.zip;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* compiled from: AsiExtraField */
public class b implements Cloneable, r {
    private static final ZipShort a = new ZipShort(30062);
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private String e = "";
    private boolean f = false;
    private CRC32 g = new CRC32();

    public ZipShort getHeaderId() {
        return a;
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(c().getBytes().length + 14);
    }

    public ZipShort getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    public byte[] getLocalFileDataData() {
        Object obj = new byte[(getLocalFileDataLength().getValue() - 4)];
        System.arraycopy(ZipShort.getBytes(e()), 0, obj, 0, 2);
        Object bytes = c().getBytes();
        System.arraycopy(ZipLong.getBytes((long) bytes.length), 0, obj, 2, 4);
        System.arraycopy(ZipShort.getBytes(a()), 0, obj, 6, 2);
        System.arraycopy(ZipShort.getBytes(b()), 0, obj, 8, 2);
        System.arraycopy(bytes, 0, obj, 10, bytes.length);
        this.g.reset();
        this.g.update(obj);
        bytes = new byte[(obj.length + 4)];
        System.arraycopy(ZipLong.getBytes(this.g.getValue()), 0, bytes, 0, 4);
        System.arraycopy(obj, 0, bytes, 4, obj.length);
        return bytes;
    }

    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public boolean d() {
        return c().length() != 0;
    }

    public void a(int i) {
        this.b = b(i);
    }

    public int e() {
        return this.b;
    }

    public void a(boolean z) {
        this.f = z;
        this.b = b(this.b);
    }

    public boolean f() {
        return this.f && !d();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        boolean z = false;
        long value = ZipLong.getValue(bArr, i);
        Object obj = new byte[(i2 - 4)];
        System.arraycopy(bArr, i + 4, obj, 0, i2 - 4);
        this.g.reset();
        this.g.update(obj);
        long value2 = this.g.getValue();
        if (value != value2) {
            throw new ZipException("bad CRC checksum " + Long.toHexString(value) + " instead of " + Long.toHexString(value2));
        }
        int value3 = ZipShort.getValue(obj, 0);
        Object obj2 = new byte[((int) ZipLong.getValue(obj, 2))];
        this.c = ZipShort.getValue(obj, 6);
        this.d = ZipShort.getValue(obj, 8);
        if (obj2.length == 0) {
            this.e = "";
        } else {
            System.arraycopy(obj, 10, obj2, 0, obj2.length);
            this.e = new String(obj2);
        }
        if ((value3 & 16384) != 0) {
            z = true;
        }
        a(z);
        a(value3);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }

    protected int b(int i) {
        int i2 = 32768;
        if (d()) {
            i2 = 40960;
        } else if (f()) {
            i2 = 16384;
        }
        return i2 | (i & 4095);
    }

    public Object clone() {
        try {
            b bVar = (b) super.clone();
            bVar.g = new CRC32();
            return bVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
