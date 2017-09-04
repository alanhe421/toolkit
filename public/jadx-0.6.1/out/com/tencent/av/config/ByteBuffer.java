package com.tencent.av.config;

import java.io.UnsupportedEncodingException;

public class ByteBuffer {
    private static final int SIZE = 512;
    private byte[] buffer;
    private int bufferSize;
    private int curIndex;

    public ByteBuffer() {
        this.buffer = null;
        this.curIndex = 0;
        this.bufferSize = 0;
        this.buffer = new byte[512];
        for (int i = 0; i < 512; i++) {
            this.buffer[i] = (byte) 0;
        }
        this.curIndex = 0;
        this.bufferSize = 0;
    }

    public ByteBuffer(byte[] bArr) {
        this.buffer = null;
        this.curIndex = 0;
        this.bufferSize = 0;
        if (bArr == null) {
            this.buffer = new byte[512];
            for (int i = 0; i < 512; i++) {
                this.buffer[i] = (byte) 0;
            }
            this.curIndex = 0;
            this.bufferSize = 0;
            return;
        }
        this.buffer = bArr;
        this.curIndex = 0;
        this.bufferSize = bArr.length;
    }

    public void WriteByteBuffer(byte[] bArr) {
        if (bArr != null && bArr.length >= 1) {
            System.arraycopy(bArr, 0, this.buffer, this.curIndex, bArr.length);
            this.curIndex += bArr.length;
            this.bufferSize += bArr.length;
        }
    }

    public void WriteUInt8(byte b) {
        this.buffer[this.curIndex] = b;
        this.curIndex++;
        this.bufferSize++;
    }

    public void WriteUInt16(short s) {
        System.arraycopy(new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)}, 0, this.buffer, this.curIndex, 2);
        this.curIndex += 2;
        this.bufferSize += 2;
    }

    public void WriteUInt32(int i) {
        System.arraycopy(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)}, 0, this.buffer, this.curIndex, 4);
        this.curIndex += 4;
        this.bufferSize += 4;
    }

    public void WriteString(String str) {
        if (str != null && str.length() >= 1) {
            try {
                Object bytes = str.getBytes("GBK");
                System.arraycopy(bytes, 0, this.buffer, this.curIndex, bytes.length);
                this.curIndex += bytes.length;
                this.bufferSize = bytes.length + this.bufferSize;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public byte ReadUInt8() {
        if (length() < 1) {
            return (byte) -1;
        }
        byte[] bArr = this.buffer;
        int i = this.curIndex;
        this.curIndex = i + 1;
        return bArr[i];
    }

    public short ReadUInt16() {
        if (length() < 2) {
            return (short) -1;
        }
        byte[] bArr = new byte[2];
        byte[] bArr2 = this.buffer;
        int i = this.curIndex;
        this.curIndex = i + 1;
        bArr[0] = bArr2[i];
        bArr2 = this.buffer;
        i = this.curIndex;
        this.curIndex = i + 1;
        bArr[1] = bArr2[i];
        return (short) (bArr[1] + (bArr[0] * 256));
    }

    public short ReadUInt16Length() {
        if (length() < 2) {
            return (short) -1;
        }
        byte[] bArr = this.buffer;
        int i = this.curIndex;
        this.curIndex = i + 1;
        short s = (short) (bArr[i] << 8);
        byte[] bArr2 = this.buffer;
        int i2 = this.curIndex;
        this.curIndex = i2 + 1;
        return (short) (s + ((short) (bArr2[i2] & 255)));
    }

    public int ReadUInt32() {
        int i = 0;
        if (length() < 4) {
            return -1;
        }
        r3 = new byte[4];
        byte[] bArr = this.buffer;
        int i2 = this.curIndex;
        this.curIndex = i2 + 1;
        r3[0] = bArr[i2];
        byte[] bArr2 = this.buffer;
        int i3 = this.curIndex;
        this.curIndex = i3 + 1;
        r3[1] = bArr2[i3];
        bArr2 = this.buffer;
        i3 = this.curIndex;
        this.curIndex = i3 + 1;
        r3[2] = bArr2[i3];
        bArr2 = this.buffer;
        i3 = this.curIndex;
        this.curIndex = i3 + 1;
        r3[3] = bArr2[i3];
        int i4 = 0;
        while (i4 < 4) {
            i2 = (r3[3 - i4] * Common.intPow(16, i4 * 2)) + i;
            i4++;
            i = i2;
        }
        return i;
    }

    public String ReadString(int i) {
        if (i <= 0 || length() < i) {
            return null;
        }
        Object obj = new byte[i];
        System.arraycopy(this.buffer, this.curIndex, obj, 0, i);
        this.curIndex += i;
        try {
            return new String(obj, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean Consume(int i) {
        if (i <= 0 || length() < i) {
            return false;
        }
        System.arraycopy(this.buffer, this.curIndex, new byte[i], 0, i);
        this.curIndex += i;
        return true;
    }

    public int length() {
        return this.bufferSize - this.curIndex;
    }

    public byte[] Data() {
        Object obj = new byte[this.bufferSize];
        System.arraycopy(this.buffer, 0, obj, 0, this.bufferSize);
        return obj;
    }
}
