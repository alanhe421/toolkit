package com.tencent.android.tpush.service.channel.c;

import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ProGuard */
public class c extends OutputStream {
    final /* synthetic */ a a;

    protected c(a aVar) {
        this.a = aVar;
    }

    public void close() {
        synchronized (this.a) {
            if (!this.a.k) {
                flush();
            }
            this.a.k = true;
        }
    }

    public void flush() {
        if (this.a.k) {
            throw new IOException("OutputStream has been closed; cannot flush a closed OutputStream.");
        } else if (this.a.i) {
            throw new IOException("Buffer closed by inputStream; cannot flush.");
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            synchronized (this.a) {
                if (this.a.k) {
                    throw new IOException("OutputStream has been closed; cannot write to a closed OutputStream.");
                } else if (this.a.i) {
                    throw new IOException("Buffer closed by InputStream; cannot write to a closed buffer.");
                } else {
                    int c = this.a.f();
                    while (this.a.f && c < i2) {
                        this.a.e();
                        c = this.a.f();
                    }
                    if (this.a.g || c >= i2) {
                        c = Math.min(i2, c);
                        int min = Math.min(c, this.a.a.length - this.a.c);
                        c = Math.min(c - min, (this.a.a.length - this.a.d) - 1);
                        int i3 = min + c;
                        if (min > 0) {
                            System.arraycopy(bArr, i, this.a.a, this.a.c, min);
                        }
                        if (c > 0) {
                            System.arraycopy(bArr, min + i, this.a.a, 0, c);
                            this.a.c = c;
                        } else {
                            a aVar = this.a;
                            aVar.c += i3;
                        }
                        if (this.a.c == this.a.a.length) {
                            this.a.c = 0;
                        }
                        i += i3;
                        i2 -= i3;
                    } else {
                        throw new IORefusedException("CircularByteBuffer is full; cannot write " + i2 + " bytes");
                    }
                }
            }
            if (i2 > 0) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    throw new IOException("Waiting for available space in buffer interrupted.");
                }
            }
        }
    }

    public void write(int i) {
        Object obj = null;
        while (obj == null) {
            synchronized (this.a) {
                if (this.a.k) {
                    throw new IOException("OutputStream has been closed; cannot write to a closed OutputStream.");
                } else if (this.a.i) {
                    throw new IOException("Buffer closed by InputStream; cannot write to a closed buffer.");
                } else {
                    int c = this.a.f();
                    while (this.a.f && c < 1) {
                        this.a.e();
                        c = this.a.f();
                    }
                    if (this.a.g || c >= 1) {
                        if (c > 0) {
                            this.a.a[this.a.c] = (byte) (i & 255);
                            a aVar = this.a;
                            aVar.c++;
                            if (this.a.c == this.a.a.length) {
                                this.a.c = 0;
                            }
                            obj = 1;
                        }
                    } else {
                        throw new IORefusedException("CircularByteBuffer is full; cannot write 1 byte");
                    }
                }
            }
            if (obj == null) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    throw new IOException("Waiting for available space in buffer interrupted.");
                }
            }
        }
    }
}
