package com.tencent.tesla.soload;

import java.nio.ByteOrder;

public final class HeapBufferIterator extends BufferIterator {
    private final byte[] buffer;
    private final int byteCount;
    private final int offset;
    private final ByteOrder order;
    private int position;

    public final class SizeOf {
        public static final int CHAR = 2;
        public static final int DOUBLE = 8;
        public static final int FLOAT = 4;
        public static final int INT = 4;
        public static final int LONG = 8;
        public static final int SHORT = 2;

        private SizeOf() {
        }
    }

    HeapBufferIterator(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        this.buffer = bArr;
        this.offset = i;
        this.byteCount = i2;
        this.order = byteOrder;
    }

    public void seek(int i) {
        this.position = i;
    }

    public void skip(int i) {
        this.position += i;
    }

    public int readInt() {
        int peekInt = Memory.peekInt(this.buffer, this.offset + this.position, this.order);
        this.position += 4;
        return peekInt;
    }

    public short readShort() {
        short peekShort = Memory.peekShort(this.buffer, this.offset + this.position, this.order);
        this.position += 2;
        return peekShort;
    }

    public static HeapBufferIterator iterator(byte[] bArr, int i, int i2, ByteOrder byteOrder) {
        return new HeapBufferIterator(bArr, i, i2, byteOrder);
    }
}
