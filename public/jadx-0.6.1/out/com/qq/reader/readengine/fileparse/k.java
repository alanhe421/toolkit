package com.qq.reader.readengine.fileparse;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: UMDInputStream */
public class k {
    private volatile RandomAccessFile a;

    public k(RandomAccessFile randomAccessFile) {
        this.a = randomAccessFile;
    }

    public final int a(byte[] bArr) throws IOException {
        return this.a.read(bArr, 0, bArr.length);
    }

    public final byte a() throws IOException {
        int read = this.a.read();
        if (read >= 0) {
            return (byte) read;
        }
        throw new EOFException();
    }

    public final int b() throws IOException {
        int read = this.a.read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    public final int c() throws IOException {
        int read = this.a.read();
        int read2 = this.a.read();
        if ((read2 | read) < 0) {
            throw new EOFException();
        }
        return (read << 0) + (read2 << 8);
    }

    public final char d() throws IOException {
        int read = this.a.read();
        int read2 = this.a.read();
        if ((read2 | read) < 0) {
            throw new EOFException();
        }
        return (char) ((read << 0) + (read2 << 8));
    }

    public final int e() throws IOException {
        int read = this.a.read();
        int read2 = this.a.read();
        int read3 = this.a.read();
        int read4 = this.a.read();
        if ((((read4 | read3) | read2) | read) < 0) {
            throw new EOFException();
        }
        return (read << 0) + ((read2 << 8) + ((read3 << 16) + (read4 << 24)));
    }

    public final int a(int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int skipBytes = this.a.skipBytes(i - i2);
            if (skipBytes <= 0) {
                break;
            }
            i2 += skipBytes;
        }
        return i2;
    }

    public final long f() {
        try {
            return this.a.getFilePointer();
        } catch (IOException e) {
            return -1;
        }
    }

    public void g() throws IOException {
        this.a.close();
    }

    public void a(long j) throws IOException {
        this.a.seek(j);
    }
}
