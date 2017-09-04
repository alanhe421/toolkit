package org.apache.commons.compress.archivers.dump;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.Inflater;
import oicq.wlogin_sdk.tools.util;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants.COMPRESSION_TYPE;

/* compiled from: TapeInputStream */
class e extends FilterInputStream {
    private byte[] a = new byte[1024];
    private int b = -1;
    private int c = 1024;
    private int d = 1024;
    private boolean e = false;
    private long f = 0;

    public e(InputStream inputStream) {
        super(inputStream);
    }

    public void a(int i, boolean z) throws IOException {
        this.e = z;
        this.c = i * 1024;
        Object obj = this.a;
        this.a = new byte[this.c];
        System.arraycopy(obj, 0, this.a, 0, 1024);
        a(this.a, 1024, this.c + util.E_ENCRYPTION_METHOD);
        this.b = 0;
        this.d = 1024;
    }

    public int available() throws IOException {
        if (this.d < this.c) {
            return this.c - this.d;
        }
        return this.in.available();
    }

    public int read() throws IOException {
        throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 % 1024 != 0) {
            throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
        }
        int i3 = 0;
        while (i3 < i2) {
            if (this.d == this.c && !a(true)) {
                return -1;
            }
            int i4;
            if (this.d + (i2 - i3) <= this.c) {
                i4 = i2 - i3;
            } else {
                i4 = this.c - this.d;
            }
            System.arraycopy(this.a, this.d, bArr, i, i4);
            this.d += i4;
            i3 += i4;
            i += i4;
        }
        return i3;
    }

    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j % 1024 != 0) {
            throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
        }
        while (j2 < j) {
            long j3;
            if (this.d == this.c) {
                if (!a(j - j2 < ((long) this.c))) {
                    return -1;
                }
            }
            if (((long) this.d) + (j - j2) <= ((long) this.c)) {
                j3 = j - j2;
            } else {
                j3 = (long) (this.c - this.d);
            }
            this.d = (int) (((long) this.d) + j3);
            j2 += j3;
        }
        return j2;
    }

    public void close() throws IOException {
        if (this.in != null && this.in != System.in) {
            this.in.close();
        }
    }

    public byte[] a() throws IOException {
        byte[] bArr = new byte[1024];
        if (-1 != read(bArr, 0, bArr.length)) {
            return bArr;
        }
        throw new ShortFileException();
    }

    private boolean a(boolean z) throws IOException {
        byte b = (byte) 1;
        if (this.in == null) {
            throw new IOException("input buffer is closed");
        }
        boolean a;
        if (!this.e || this.b == -1) {
            a = a(this.a, 0, this.c);
            this.f += (long) this.c;
        } else if (!a(this.a, 0, 4)) {
            return false;
        } else {
            this.f += 4;
            int b2 = d.b(this.a, 0);
            if ((b2 & 1) != 1) {
                b = (byte) 0;
            }
            if (b == (byte) 0) {
                a = a(this.a, 0, this.c);
                this.f += (long) this.c;
            } else {
                int i = (b2 >> 1) & 7;
                b2 = 268435455 & (b2 >> 4);
                byte[] bArr = new byte[b2];
                a = a(bArr, 0, b2);
                this.f += (long) b2;
                if (z) {
                    switch (COMPRESSION_TYPE.find(i & 3)) {
                        case ZLIB:
                            try {
                                Inflater inflater = new Inflater();
                                inflater.setInput(bArr, 0, bArr.length);
                                if (inflater.inflate(this.a) == this.c) {
                                    inflater.end();
                                    break;
                                }
                                throw new ShortFileException();
                            } catch (Throwable e) {
                                throw new DumpArchiveException("bad data", e);
                            }
                        case BZLIB:
                            throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                        case LZO:
                            throw new UnsupportedCompressionAlgorithmException("LZO");
                        default:
                            throw new UnsupportedCompressionAlgorithmException();
                    }
                }
                Arrays.fill(this.a, (byte) 0);
            }
        }
        this.b++;
        this.d = 0;
        return a;
    }

    private boolean a(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = this.in.read(bArr, i + i3, i2 - i3);
            if (read == -1) {
                throw new ShortFileException();
            }
            i3 += read;
        }
        return true;
    }

    public long b() {
        return this.f;
    }
}
