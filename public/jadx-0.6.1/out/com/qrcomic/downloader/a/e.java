package com.qrcomic.downloader.a;

import android.text.TextUtils;
import com.qrcomic.a.h;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: QRComicOfflineFile */
public class e<K extends ComicSectionPicInfo, V extends a> {
    private static volatile e<ComicSectionPicInfo, a> a;
    private File b = null;
    private String c;

    private e(String str, File file) {
        this.c = str;
        this.b = file;
    }

    public static synchronized e<ComicSectionPicInfo, a> a() {
        e<ComicSectionPicInfo, a> eVar;
        synchronized (e.class) {
            if (a == null || b(a.c)) {
                File file = new File(c.b());
                if (!file.exists()) {
                    file.mkdirs();
                }
                a = new e(b.a().b().a(), file);
            }
            eVar = a;
        }
        return eVar;
    }

    private static boolean b(String str) {
        h b = b.a().b();
        if (b == null) {
            return false;
        }
        String a = b.a();
        if (a == null || a.equals(str)) {
            return false;
        }
        return true;
    }

    public long a(K k) {
        if (k == null) {
            return -1;
        }
        Object b = c.b();
        if (TextUtils.isEmpty(b)) {
            return -1;
        }
        String a = c.a(this.c, k.comicId, k.sectionId);
        String a2 = c.a((ComicSectionPicInfo) k);
        File file = null;
        if (!TextUtils.isEmpty(a)) {
            file = new File(a, a2);
        }
        if (file != null && file.exists() && file.isFile()) {
            return file.length();
        }
        file = new File(b);
        if (!file.isDirectory()) {
            return -1;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                String str = file2.getAbsolutePath() + "/" + k.comicId + "/" + k.sectionId;
                File file3 = new File(str, a2);
                if (file3.exists() && file3.isFile()) {
                    com.qrcomic.f.b.a(str + "/" + a2, a + "/" + a2);
                    return file3.length();
                }
            }
        }
        return -1;
    }

    public V b(K k) {
        String a;
        InputStream fileInputStream;
        V aVar;
        Exception e;
        String str;
        StringBuilder append;
        String str2;
        Throwable th;
        if (k != null) {
            Object b = c.b();
            if (!TextUtils.isEmpty(b)) {
                File file;
                a = c.a(this.c, k.comicId, k.sectionId);
                String a2 = c.a((ComicSectionPicInfo) k);
                if (TextUtils.isEmpty(a)) {
                    file = null;
                } else {
                    file = new File(a, a2);
                }
                if (file == null || !file.exists() || !file.isFile()) {
                    File file2 = new File(b);
                    if (file2.isDirectory()) {
                        for (File file3 : file2.listFiles()) {
                            if (file3.isDirectory()) {
                                String str3 = file3.getAbsolutePath() + "/" + k.comicId + "/" + k.sectionId;
                                file = new File(str3, a2);
                                if (file.exists() && file.isFile()) {
                                    com.qrcomic.f.b.a(str3 + "/" + a2, a + "/" + a2);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (file != null && file.exists() && file.isFile()) {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            long length = file.length();
                            aVar = new a((int) length);
                            aVar.a(fileInputStream);
                            if (g.a()) {
                                g.b("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "offlineFile exists and fileName=" + a2 + ",fileLength=" + length + ",count=" + aVar.b);
                            }
                            if (aVar.a == null || aVar.b <= 0) {
                                aVar = null;
                            } else {
                                c.a((ComicSectionPicInfo) k, aVar.a);
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                        aVar = null;
                                    }
                                }
                                aVar = null;
                                if (g.a()) {
                                    str = "qqcomic.downloader.cache.QRComicOfflineFile";
                                    a = g.d;
                                    append = new StringBuilder().append("vipcomic offline file get form sdcard picInfo=").append(k == null ? k.toString() : "null").append(",result ");
                                    if (aVar != null) {
                                        str2 = "is null";
                                    } else {
                                        str2 = "not null";
                                    }
                                    g.b(str, a, append.append(str2).toString());
                                }
                                return aVar;
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e22) {
                                        e22.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e5) {
                        e4 = e5;
                        fileInputStream = null;
                        e4.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        aVar = null;
                        if (g.a()) {
                            str = "qqcomic.downloader.cache.QRComicOfflineFile";
                            a = g.d;
                            if (k == null) {
                            }
                            append = new StringBuilder().append("vipcomic offline file get form sdcard picInfo=").append(k == null ? k.toString() : "null").append(",result ");
                            if (aVar != null) {
                                str2 = "not null";
                            } else {
                                str2 = "is null";
                            }
                            g.b(str, a, append.append(str2).toString());
                        }
                        return aVar;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                    if (g.a()) {
                        str = "qqcomic.downloader.cache.QRComicOfflineFile";
                        a = g.d;
                        if (k == null) {
                        }
                        append = new StringBuilder().append("vipcomic offline file get form sdcard picInfo=").append(k == null ? k.toString() : "null").append(",result ");
                        if (aVar != null) {
                            str2 = "is null";
                        } else {
                            str2 = "not null";
                        }
                        g.b(str, a, append.append(str2).toString());
                    }
                    return aVar;
                }
            }
        }
        aVar = null;
        if (g.a()) {
            str = "qqcomic.downloader.cache.QRComicOfflineFile";
            a = g.d;
            if (k == null) {
            }
            append = new StringBuilder().append("vipcomic offline file get form sdcard picInfo=").append(k == null ? k.toString() : "null").append(",result ");
            if (aVar != null) {
                str2 = "not null";
            } else {
                str2 = "is null";
            }
            g.b(str, a, append.append(str2).toString());
        }
        return aVar;
    }

    public boolean a(K k, V v) {
        OutputStream outputStream;
        InputStream inputStream;
        IOException e;
        InputStream inputStream2;
        Throwable th;
        byte[] bArr = null;
        boolean z = false;
        if (!(k == null || v.a == null)) {
            String a = c.a(this.c, k.comicId, k.sectionId);
            if (a != null) {
                OutputStream bufferedOutputStream;
                try {
                    Object a2 = c.a((ComicSectionPicInfo) k);
                    if (TextUtils.isEmpty(a2)) {
                        outputStream = null;
                        inputStream = null;
                    } else {
                        File file = new File(a, a2);
                        File parentFile = file.getParentFile();
                        if (!(parentFile.exists() && parentFile.isDirectory())) {
                            parentFile.mkdirs();
                        }
                        outputStream = new FileOutputStream(file);
                        try {
                            byte[] bArr2 = v.a;
                            c.a((ComicSectionPicInfo) k, bArr2);
                            if (bArr2 == null || v.b <= 0) {
                                inputStream = null;
                            } else {
                                inputStream = new ByteArrayInputStream(bArr2, 0, v.b);
                                try {
                                    bufferedOutputStream = new BufferedOutputStream(outputStream, 4096);
                                } catch (IOException e2) {
                                    e = e2;
                                    bufferedOutputStream = outputStream;
                                    inputStream2 = inputStream;
                                    try {
                                        e.printStackTrace();
                                        try {
                                            c.a.a(bArr);
                                            if (inputStream2 != null) {
                                                inputStream2.close();
                                            }
                                            if (bufferedOutputStream != null) {
                                                bufferedOutputStream.close();
                                            }
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                        }
                                        if (g.a()) {
                                            g.b("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "vipcomic offline file save picInfo=" + k.toString() + ",result=" + z);
                                        }
                                        return z;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        inputStream = inputStream2;
                                        outputStream = bufferedOutputStream;
                                        try {
                                            c.a.a(bArr);
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                            if (outputStream != null) {
                                                outputStream.close();
                                            }
                                        } catch (Exception e32) {
                                            e32.printStackTrace();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    c.a.a(bArr);
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    throw th;
                                }
                                try {
                                    bArr = c.a.a(4096);
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        bufferedOutputStream.write(bArr, 0, read);
                                    }
                                    bufferedOutputStream.flush();
                                    z = true;
                                    outputStream = bufferedOutputStream;
                                } catch (IOException e4) {
                                    e = e4;
                                    inputStream2 = inputStream;
                                    e.printStackTrace();
                                    c.a.a(bArr);
                                    if (inputStream2 != null) {
                                        inputStream2.close();
                                    }
                                    if (bufferedOutputStream != null) {
                                        bufferedOutputStream.close();
                                    }
                                    if (g.a()) {
                                        g.b("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "vipcomic offline file save picInfo=" + k.toString() + ",result=" + z);
                                    }
                                    return z;
                                } catch (Throwable th4) {
                                    th = th4;
                                    outputStream = bufferedOutputStream;
                                    c.a.a(bArr);
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    throw th;
                                }
                            }
                        } catch (IOException e5) {
                            e = e5;
                            bufferedOutputStream = outputStream;
                            inputStream2 = null;
                            e.printStackTrace();
                            c.a.a(bArr);
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            if (g.a()) {
                                g.b("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "vipcomic offline file save picInfo=" + k.toString() + ",result=" + z);
                            }
                            return z;
                        } catch (Throwable th5) {
                            th = th5;
                            inputStream = null;
                            c.a.a(bArr);
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            throw th;
                        }
                    }
                    try {
                        c.a.a(bArr);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Exception e322) {
                        e322.printStackTrace();
                    }
                } catch (IOException e6) {
                    e = e6;
                    bufferedOutputStream = null;
                    inputStream2 = null;
                    e.printStackTrace();
                    c.a.a(bArr);
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (g.a()) {
                        g.b("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "vipcomic offline file save picInfo=" + k.toString() + ",result=" + z);
                    }
                    return z;
                } catch (Throwable th6) {
                    th = th6;
                    outputStream = null;
                    inputStream = null;
                    c.a.a(bArr);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            }
            if (g.a()) {
                g.b("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "vipcomic offline file save picInfo=" + k.toString() + ",result=" + z);
            }
        }
        return z;
    }

    public boolean a(String str, String str2) {
        try {
            com.qrcomic.f.b.c(c.b(this.c, str, str2));
            return true;
        } catch (Exception e) {
            if (g.a()) {
                g.c("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "delete section file fail comicId - " + str + " sectionId - " + str2);
            }
            return false;
        }
    }

    public boolean a(String str) {
        try {
            com.qrcomic.f.b.c(c.a(this.c, str));
            return true;
        } catch (Exception e) {
            if (g.a()) {
                g.c("qqcomic.downloader.cache.QRComicOfflineFile", g.d, "delete comic file fail " + str);
            }
            return false;
        }
    }
}
