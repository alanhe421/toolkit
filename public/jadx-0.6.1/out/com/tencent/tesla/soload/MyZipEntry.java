package com.tencent.tesla.soload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

class MyZipEntry implements MyZipConstants, Cloneable {
    public static final int DEFLATED = 8;
    public static final int STORED = 0;
    String comment;
    int commentLength = -1;
    long compressedSize = -1;
    int compressionMethod = -1;
    long crc = -1;
    int diskNumbers = -1;
    long externalFileAttri = -1;
    byte[] extra;
    int extraLength = -1;
    int flags = -1;
    boolean hasDD = false;
    int internalFileAttri = -1;
    long mLocContentSize = -1;
    long mLocalHeaderRelOffset = -1;
    int modDate = -1;
    String name;
    byte[] nameBytes;
    int nameLength = -1;
    long size = -1;
    int time = -1;
    int version = -1;
    int versionMinimum = -1;

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    MyZipEntry(byte[] bArr, InputStream inputStream) throws IOException {
        Streams.readFully(inputStream, bArr, 0, bArr.length);
        HeapBufferIterator it = HeapBufferIterator.iterator(bArr, 0, bArr.length, ByteOrder.LITTLE_ENDIAN);
        it.readInt();
        this.version = it.readShort();
        this.versionMinimum = it.readShort();
        this.flags = it.readShort();
        this.compressionMethod = it.readShort();
        this.time = it.readShort();
        this.modDate = it.readShort();
        this.crc = ((long) it.readInt()) & 4294967295L;
        this.compressedSize = ((long) it.readInt()) & 4294967295L;
        this.size = ((long) it.readInt()) & 4294967295L;
        this.nameLength = it.readShort();
        this.extraLength = it.readShort();
        this.commentLength = it.readShort();
        this.diskNumbers = it.readShort();
        this.internalFileAttri = it.readShort();
        this.externalFileAttri = ((long) it.readInt()) & 4294967295L;
        this.mLocalHeaderRelOffset = ((long) it.readInt()) & 4294967295L;
        this.nameBytes = new byte[this.nameLength];
        Streams.readFully(inputStream, this.nameBytes, 0, this.nameBytes.length);
        this.name = new String(this.nameBytes, 0, this.nameBytes.length, "UTF-8");
        if (this.commentLength > 0) {
            byte[] bArr2 = new byte[this.commentLength];
            Streams.readFully(inputStream, bArr2, 0, this.commentLength);
            this.comment = new String(bArr2, 0, bArr2.length, "UTF-8");
        }
        if (this.extraLength > 0) {
            this.extra = new byte[this.extraLength];
            Streams.readFully(inputStream, this.extra, 0, this.extraLength);
        }
    }
}
