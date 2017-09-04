package com.tencent.mobileqq.pb;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public final class CodedInputStreamMicro {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 64;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit;
    private int sizeLimit;
    private int totalBytesRetired;

    public static CodedInputStreamMicro newInstance(InputStream inputStream) {
        return new CodedInputStreamMicro(inputStream);
    }

    public static CodedInputStreamMicro newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStreamMicro newInstance(byte[] bArr, int i, int i2) {
        return new CodedInputStreamMicro(bArr, i, i2);
    }

    public int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        this.lastTag = readRawVarint32();
        if (this.lastTag != 0) {
            return this.lastTag;
        }
        throw InvalidProtocolBufferMicroException.invalidTag();
    }

    public void checkLastTagWas(int i) throws InvalidProtocolBufferMicroException {
        if (this.lastTag != i) {
            throw InvalidProtocolBufferMicroException.invalidEndTag();
        }
    }

    public boolean skipField(int i) throws IOException {
        switch (WireFormatMicro.getTagWireType(i)) {
            case 0:
                readInt32();
                return true;
            case 1:
                readRawLittleEndian64();
                return true;
            case 2:
                skipRawBytes(readRawVarint32());
                return true;
            case 3:
                skipMessage();
                checkLastTagWas(WireFormatMicro.makeTag(WireFormatMicro.getTagFieldNumber(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                readRawLittleEndian32();
                return true;
            default:
                throw InvalidProtocolBufferMicroException.invalidWireType();
        }
    }

    public void skipMessage() throws IOException {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag));
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public long readUInt64() throws IOException {
        return readRawVarint64();
    }

    public long readInt64() throws IOException {
        return readRawVarint64();
    }

    public int readInt32() throws IOException {
        return readRawVarint32();
    }

    public long readFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public boolean readBool() throws IOException {
        return readRawVarint32() != 0;
    }

    public String readString() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return new String(readRawBytes(readRawVarint32), "UTF-8");
        }
        String str = new String(this.buffer, this.bufferPos, readRawVarint32, "UTF-8");
        this.bufferPos = readRawVarint32 + this.bufferPos;
        return str;
    }

    public void readGroup(MessageMicro<?> messageMicro, int i) throws IOException {
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
        }
        this.recursionDepth++;
        messageMicro.mergeFrom(this);
        checkLastTagWas(WireFormatMicro.makeTag(i, 4));
        this.recursionDepth--;
    }

    public void readMessage(MessageMicro<?> messageMicro) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth >= this.recursionLimit) {
            throw InvalidProtocolBufferMicroException.recursionLimitExceeded();
        }
        readRawVarint32 = pushLimit(readRawVarint32);
        this.recursionDepth++;
        messageMicro.mergeFrom(this);
        checkLastTagWas(0);
        this.recursionDepth--;
        popLimit(readRawVarint32);
    }

    public ByteStringMicro readBytes() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return ByteStringMicro.copyFrom(readRawBytes(readRawVarint32));
        }
        ByteStringMicro copyFrom = ByteStringMicro.copyFrom(this.buffer, this.bufferPos, readRawVarint32);
        this.bufferPos = readRawVarint32 + this.bufferPos;
        return copyFrom;
    }

    public int readUInt32() throws IOException {
        return readRawVarint32();
    }

    public int readEnum() throws IOException {
        return readRawVarint32();
    }

    public int readSFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readSInt32() throws IOException {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64() throws IOException {
        return decodeZigZag64(readRawVarint64());
    }

    public int readRawVarint32() throws IOException {
        byte readRawByte = readRawByte();
        if (readRawByte >= (byte) 0) {
            return readRawByte;
        }
        int i = readRawByte & Opcodes.NEG_FLOAT;
        byte readRawByte2 = readRawByte();
        if (readRawByte2 >= (byte) 0) {
            return i | (readRawByte2 << 7);
        }
        i |= (readRawByte2 & Opcodes.NEG_FLOAT) << 7;
        readRawByte2 = readRawByte();
        if (readRawByte2 >= (byte) 0) {
            return i | (readRawByte2 << 14);
        }
        i |= (readRawByte2 & Opcodes.NEG_FLOAT) << 14;
        readRawByte2 = readRawByte();
        if (readRawByte2 >= (byte) 0) {
            return i | (readRawByte2 << 21);
        }
        i |= (readRawByte2 & Opcodes.NEG_FLOAT) << 21;
        readRawByte2 = readRawByte();
        i |= readRawByte2 << 28;
        if (readRawByte2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (readRawByte() >= (byte) 0) {
                return i;
            }
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
    }

    static int readRawVarint32(InputStream inputStream) throws IOException {
        int i = 0;
        int i2 = 0;
        while (i < 32) {
            int read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            }
            i2 |= (read & Opcodes.NEG_FLOAT) << i;
            if ((read & 128) == 0) {
                break;
            }
            i += 7;
        }
        while (i < 64) {
            read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            } else if ((read & 128) == 0) {
                return i2;
            } else {
                i += 7;
            }
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
    }

    public long readRawVarint64() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readRawByte = readRawByte();
            j |= ((long) (readRawByte & Opcodes.NEG_FLOAT)) << i;
            if ((readRawByte & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferMicroException.malformedVarint();
    }

    public int readRawLittleEndian32() throws IOException {
        return (((readRawByte() & 255) | ((readRawByte() & 255) << 8)) | ((readRawByte() & 255) << 16)) | ((readRawByte() & 255) << 24);
    }

    public long readRawLittleEndian64() throws IOException {
        byte readRawByte = readRawByte();
        byte readRawByte2 = readRawByte();
        return ((((((((((long) readRawByte2) & 255) << 8) | (((long) readRawByte) & 255)) | ((((long) readRawByte()) & 255) << 16)) | ((((long) readRawByte()) & 255) << 24)) | ((((long) readRawByte()) & 255) << 32)) | ((((long) readRawByte()) & 255) << 40)) | ((((long) readRawByte()) & 255) << 48)) | ((((long) readRawByte()) & 255) << 56);
    }

    public static int decodeZigZag32(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long decodeZigZag64(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private CodedInputStreamMicro(byte[] bArr, int i, int i2) {
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.buffer = bArr;
        this.bufferSize = i + i2;
        this.bufferPos = i;
        this.input = null;
    }

    private CodedInputStreamMicro(InputStream inputStream) {
        this.currentLimit = Integer.MAX_VALUE;
        this.recursionLimit = 64;
        this.sizeLimit = 67108864;
        this.buffer = new byte[4096];
        this.bufferSize = 0;
        this.bufferPos = 0;
        this.input = inputStream;
    }

    public int setRecursionLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
        }
        int i2 = this.recursionLimit;
        this.recursionLimit = i;
        return i2;
    }

    public int setSizeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Size limit cannot be negative: " + i);
        }
        int i2 = this.sizeLimit;
        this.sizeLimit = i;
        return i2;
    }

    public void resetSizeCounter() {
        this.totalBytesRetired = 0;
    }

    public int pushLimit(int i) throws InvalidProtocolBufferMicroException {
        if (i < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        }
        int i2 = (this.totalBytesRetired + this.bufferPos) + i;
        int i3 = this.currentLimit;
        if (i2 > i3) {
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
        this.currentLimit = i2;
        recomputeBufferSizeAfterLimit();
        return i3;
    }

    private void recomputeBufferSizeAfterLimit() {
        this.bufferSize += this.bufferSizeAfterLimit;
        int i = this.totalBytesRetired + this.bufferSize;
        if (i > this.currentLimit) {
            this.bufferSizeAfterLimit = i - this.currentLimit;
            this.bufferSize -= this.bufferSizeAfterLimit;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public void popLimit(int i) {
        this.currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public int getBytesUntilLimit() {
        if (this.currentLimit == Integer.MAX_VALUE) {
            return -1;
        }
        return this.currentLimit - (this.totalBytesRetired + this.bufferPos);
    }

    public boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !refillBuffer(false);
    }

    private boolean refillBuffer(boolean z) throws IOException {
        if (this.bufferPos < this.bufferSize) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        } else if (this.totalBytesRetired + this.bufferSize != this.currentLimit) {
            this.totalBytesRetired += this.bufferSize;
            this.bufferPos = 0;
            this.bufferSize = this.input == null ? -1 : this.input.read(this.buffer);
            if (this.bufferSize == 0 || this.bufferSize < -1) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.bufferSize + "\nThe InputStream implementation is buggy.");
            } else if (this.bufferSize == -1) {
                this.bufferSize = 0;
                if (!z) {
                    return false;
                }
                throw InvalidProtocolBufferMicroException.truncatedMessage();
            } else {
                recomputeBufferSizeAfterLimit();
                int i = (this.totalBytesRetired + this.bufferSize) + this.bufferSizeAfterLimit;
                if (i <= this.sizeLimit && i >= 0) {
                    return true;
                }
                throw InvalidProtocolBufferMicroException.sizeLimitExceeded();
            }
        } else if (!z) {
            return false;
        } else {
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        }
    }

    public byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(true);
        }
        byte[] bArr = this.buffer;
        int i = this.bufferPos;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    public byte[] readRawBytes(int i) throws IOException {
        if (i < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        } else if (i <= this.bufferSize - this.bufferPos) {
            Object obj = new byte[i];
            System.arraycopy(this.buffer, this.bufferPos, obj, 0, i);
            this.bufferPos += i;
            return obj;
        } else if (i < 4096) {
            Object obj2 = new byte[i];
            r0 = this.bufferSize - this.bufferPos;
            System.arraycopy(this.buffer, this.bufferPos, obj2, 0, r0);
            this.bufferPos = this.bufferSize;
            refillBuffer(true);
            while (i - r0 > this.bufferSize) {
                System.arraycopy(this.buffer, 0, obj2, r0, this.bufferSize);
                r0 += this.bufferSize;
                this.bufferPos = this.bufferSize;
                refillBuffer(true);
            }
            System.arraycopy(this.buffer, 0, obj2, r0, i - r0);
            this.bufferPos = i - r0;
            return obj2;
        } else {
            int read;
            int i2 = this.bufferPos;
            int i3 = this.bufferSize;
            this.totalBytesRetired += this.bufferSize;
            this.bufferPos = 0;
            this.bufferSize = 0;
            r0 = i - (i3 - i2);
            ArrayList arrayList = new ArrayList();
            int i4 = r0;
            while (i4 > 0) {
                Object obj3 = new byte[Math.min(i4, 4096)];
                r0 = 0;
                while (r0 < obj3.length) {
                    read = this.input == null ? -1 : this.input.read(obj3, r0, obj3.length - r0);
                    if (read == -1) {
                        throw InvalidProtocolBufferMicroException.truncatedMessage();
                    }
                    this.totalBytesRetired += read;
                    r0 += read;
                }
                r0 = i4 - obj3.length;
                arrayList.add(obj3);
                i4 = r0;
            }
            Object obj4 = new byte[i];
            r0 = i3 - i2;
            System.arraycopy(this.buffer, i2, obj4, 0, r0);
            int i5 = r0;
            for (read = 0; read < arrayList.size(); read++) {
                byte[] bArr = (byte[]) arrayList.get(read);
                System.arraycopy(bArr, 0, obj4, i5, bArr.length);
                i5 += bArr.length;
            }
            return obj4;
        }
    }

    public void skipRawBytes(int i) throws IOException {
        if (i < 0) {
            throw InvalidProtocolBufferMicroException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferMicroException.truncatedMessage();
        } else if (i <= this.bufferSize - this.bufferPos) {
            this.bufferPos += i;
        } else {
            int i2 = this.bufferSize - this.bufferPos;
            this.totalBytesRetired += i2;
            this.bufferPos = 0;
            this.bufferSize = 0;
            int i3 = i2;
            while (i3 < i) {
                i2 = this.input == null ? -1 : (int) this.input.skip((long) (i - i3));
                if (i2 <= 0) {
                    throw InvalidProtocolBufferMicroException.truncatedMessage();
                }
                i3 += i2;
                this.totalBytesRetired = i2 + this.totalBytesRetired;
            }
        }
    }
}
