package com.sijla.j;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

public abstract class d {
    public static boolean a(File file, boolean z) {
        InputStream fileInputStream;
        OutputStream fileOutputStream;
        boolean z2;
        Exception e;
        Object obj;
        Throwable th;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file.getPath().replace(".gz", ""));
                try {
                    a(fileInputStream, fileOutputStream);
                    fileOutputStream.flush();
                    b.a(new Closeable[]{fileInputStream, fileOutputStream});
                    z2 = true;
                } catch (Exception e2) {
                    e = e2;
                    obj = fileInputStream;
                    try {
                        e.printStackTrace();
                        b.a(new Closeable[]{closeable, fileOutputStream});
                        z2 = false;
                        if (z) {
                            file.delete();
                        }
                        return z2;
                    } catch (Throwable th2) {
                        th = th2;
                        Closeable closeable2 = closeable;
                        obj = fileOutputStream;
                        b.a(new Closeable[]{fileInputStream, closeable});
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj = fileOutputStream;
                    b.a(new Closeable[]{fileInputStream, closeable});
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                obj = fileInputStream;
                e.printStackTrace();
                b.a(new Closeable[]{closeable, fileOutputStream});
                z2 = false;
                if (z) {
                    file.delete();
                }
                return z2;
            } catch (Throwable th4) {
                th = th4;
                b.a(new Closeable[]{fileInputStream, closeable});
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
            e.printStackTrace();
            b.a(new Closeable[]{closeable, fileOutputStream});
            z2 = false;
            if (z) {
                file.delete();
            }
            return z2;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            b.a(new Closeable[]{fileInputStream, closeable});
            throw th;
        }
        if (z) {
            file.delete();
        }
        return z2;
    }

    public static boolean a(File file) {
        return a(file, true);
    }

    public static void a(InputStream inputStream, OutputStream outputStream) {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = gZIPInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                gZIPInputStream.close();
                return;
            }
        }
    }
}
