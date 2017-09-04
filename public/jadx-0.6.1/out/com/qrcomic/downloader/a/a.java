package com.qrcomic.downloader.a;

import com.qrcomic.downloader.b;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: QRComicFile */
public class a {
    public byte[] a;
    public int b = 0;
    private b c = b.b;

    public a(int i) {
        this.a = this.c.a(i);
    }

    private void a(int i) {
        if (this.b + i > this.a.length) {
            Object a = this.c.a((this.b + i) * 2);
            System.arraycopy(this.a, 0, a, 0, this.b);
            this.c.a(this.a);
            this.a = a;
        }
    }

    public synchronized void a(byte[] bArr, int i, int i2) {
        if ((i | i2) >= 0) {
            if (i <= bArr.length && bArr.length - i >= i2) {
                if (i2 != 0) {
                    a(i2);
                    System.arraycopy(bArr, i, this.a, this.b, i2);
                    this.b += i2;
                }
            }
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public synchronized void a(InputStream inputStream) {
        try {
            byte[] a = b.a.a(8192);
            while (true) {
                int read = inputStream.read(a);
                if (read != -1) {
                    a(a, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            inputStream.close();
            b.a.a(a);
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            b.a.a(null);
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            b.a.a(null);
        }
        return;
    }

    public void finalize() {
        try {
            super.finalize();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        } finally {
            this.c.a(this.a);
            this.a = null;
        }
    }

    public void a() {
        this.c.a(this.a);
        this.a = null;
    }
}
