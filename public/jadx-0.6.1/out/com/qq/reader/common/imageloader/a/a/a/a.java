package com.qq.reader.common.imageloader.a.a.a;

import android.graphics.Bitmap.CompressFormat;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.imageloader.a.a.b;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: BaseDiscCache */
public abstract class a implements b {
    public static final CompressFormat a = CompressFormat.PNG;
    protected final File b;
    protected final com.qq.reader.common.imageloader.a.a.b.b c;
    protected int d = 32768;
    protected CompressFormat e = a;
    protected int f = 100;

    public a(File file, com.qq.reader.common.imageloader.a.a.b.b bVar) {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (bVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.b = file;
            this.c = bVar;
        }
    }

    public File a() {
        return this.b;
    }

    public File a(String str) {
        return c(str);
    }

    public boolean a(String str, InputStream inputStream, com.qq.reader.common.imageloader.d.a.a aVar) throws IOException {
        Throwable th;
        File c = c(str);
        File file = new File(c.getAbsolutePath() + f.DOWNLOAD_FILE_TMP);
        Closeable bufferedOutputStream;
        boolean a;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.d);
            a = com.qq.reader.common.imageloader.d.a.a(inputStream, bufferedOutputStream, aVar, this.d);
            try {
                com.qq.reader.common.imageloader.d.a.a(bufferedOutputStream);
                com.qq.reader.common.imageloader.d.a.a(inputStream);
                if (a && !file.renameTo(c)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                com.qq.reader.common.imageloader.d.a.a(inputStream);
                if (a && !file.renameTo(c)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = false;
            com.qq.reader.common.imageloader.d.a.a(inputStream);
            a = false;
            if (a) {
                file.delete();
            }
            throw th;
        }
    }

    public boolean b(String str) {
        return c(str).delete();
    }

    protected File c(String str) {
        return new File(this.b, this.c.a(str));
    }
}
