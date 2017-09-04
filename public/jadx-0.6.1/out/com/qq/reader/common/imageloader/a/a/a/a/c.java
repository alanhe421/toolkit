package com.qq.reader.common.imageloader.a.a.a.a;

import android.graphics.Bitmap.CompressFormat;
import com.qq.reader.common.imageloader.a.a.b;
import com.qq.reader.common.imageloader.d.a.a;
import com.qq.reader.common.monitor.f;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: LruDiscCache */
public class c implements b {
    public static final CompressFormat a = CompressFormat.PNG;
    protected a b;
    protected final com.qq.reader.common.imageloader.a.a.b.b c;
    protected int d = 32768;
    protected CompressFormat e = a;
    protected int f = 100;

    public c(File file, com.qq.reader.common.imageloader.a.a.b.b bVar, long j, int i) {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (bVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            if (j == 0) {
                j = Long.MAX_VALUE;
            }
            if (i == 0) {
                i = Integer.MAX_VALUE;
            }
            this.c = bVar;
            a(file, j, i);
        }
    }

    private void a(File file, long j, int i) {
        try {
            this.b = a.a(file, 1, 1, j, i);
        } catch (IOException e) {
            f.a("ImageLoader", e.getMessage());
        }
    }

    public File a() {
        File file = null;
        try {
            file = this.b.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public File a(String str) {
        Exception e;
        Throwable th;
        File file = null;
        com.qq.reader.common.imageloader.a.a.a.a.a.c a;
        try {
            a = this.b.a(c(str));
            if (a != null) {
                try {
                    file = a.a(0);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("ImageLoader", e.getMessage());
                        if (a != null) {
                            a.close();
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (Exception e3) {
            e = e3;
            a = file;
            f.a("ImageLoader", e.getMessage());
            if (a != null) {
                a.close();
            }
            return file;
        } catch (Throwable th3) {
            a = file;
            th = th3;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return file;
    }

    public boolean a(String str, InputStream inputStream, a aVar) throws IOException {
        boolean z = false;
        if (this.b != null) {
            a.a b = this.b.b(c(str));
            if (b != null) {
                Closeable bufferedOutputStream = new BufferedOutputStream(b.a(0), this.d);
                try {
                    z = com.qq.reader.common.imageloader.d.a.a(inputStream, bufferedOutputStream, aVar, this.d);
                    com.qq.reader.common.imageloader.d.a.a(bufferedOutputStream);
                    if (z) {
                        b.a();
                    } else {
                        b.b();
                    }
                } catch (Throwable th) {
                    com.qq.reader.common.imageloader.d.a.a(bufferedOutputStream);
                    b.b();
                }
            }
        }
        return z;
    }

    public boolean b(String str) {
        try {
            return this.b.c(c(str));
        } catch (Exception e) {
            f.a("ImageLoader", e.getMessage());
            return false;
        }
    }

    private String c(String str) {
        return this.c.a(str);
    }
}
