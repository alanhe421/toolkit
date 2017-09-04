package com.qrcomic.downloader.a;

import android.text.TextUtils;
import com.qrcomic.downloader.b.a;
import com.qrcomic.downloader.b.a.c;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.util.g;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: QRComicFileCache */
public class b<K extends ComicSectionPicInfo, V extends a> {
    private static volatile b<ComicSectionPicInfo, a> b;
    protected a a;

    public static b<ComicSectionPicInfo, a> a() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    File file = new File(c.c());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    b = new b(file, 157286400);
                }
            }
        }
        return b;
    }

    private b(File file, long j) {
        a(file, j);
    }

    private void a(File file, long j) {
        try {
            this.a = a.a(file, 1, 1, j);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public V a(K k) {
        Exception e;
        String a = c.a((ComicSectionPicInfo) k);
        if (!(this.a == null || this.a.a() || TextUtils.isEmpty(a))) {
            c a2;
            try {
                a2 = this.a.a(a);
                if (a2 != null) {
                    try {
                        InputStream a3 = a2.a(0);
                        if (a3 != null) {
                            V aVar = new a((int) a2.b(0));
                            aVar.a(a3);
                            if (aVar.a == null || aVar.b <= 0) {
                                a3.close();
                            } else {
                                if (g.a()) {
                                    g.b("qqcomic.downloader.cache.QRComicFileCache", g.d, "QRComicFileCache get success dataLen=" + aVar.a.length + ",count=" + aVar.b);
                                }
                                c.a((ComicSectionPicInfo) k, aVar.a);
                                a2.close();
                                a3.close();
                                return aVar;
                            }
                        }
                        a2.close();
                    } catch (Exception e2) {
                        e = e2;
                        if (a2 != null) {
                            a2.close();
                        }
                        e.printStackTrace();
                        return null;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                a2 = null;
                if (a2 != null) {
                    a2.close();
                }
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public boolean a(K k, V v) {
        ByteArrayInputStream byteArrayInputStream;
        OutputStream bufferedOutputStream;
        IOException e;
        Throwable th;
        byte[] bArr = null;
        String a = c.a((ComicSectionPicInfo) k);
        if (!(this.a == null || this.a.a() || TextUtils.isEmpty(a) || v == null || v.a == null)) {
            try {
                a.a b = this.a.b(a);
                if (b != null) {
                    byte[] bArr2 = v.a;
                    byte[] a2 = c.a((ComicSectionPicInfo) k, bArr2);
                    if (a2 == null || v.b <= 0) {
                        b.b();
                    } else {
                        byteArrayInputStream = new ByteArrayInputStream(a2, 0, v.b);
                        try {
                            bufferedOutputStream = new BufferedOutputStream(b.a(0), 32768);
                        } catch (IOException e2) {
                            e = e2;
                            bufferedOutputStream = null;
                            try {
                                e.printStackTrace();
                                try {
                                    c.a.a(bArr);
                                    if (byteArrayInputStream != null) {
                                        byteArrayInputStream.close();
                                    }
                                    if (bufferedOutputStream != null) {
                                        bufferedOutputStream.close();
                                    }
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    c.a.a(bArr);
                                    if (byteArrayInputStream != null) {
                                        byteArrayInputStream.close();
                                    }
                                    if (bufferedOutputStream != null) {
                                        bufferedOutputStream.close();
                                    }
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedOutputStream = null;
                            c.a.a(bArr);
                            if (byteArrayInputStream != null) {
                                byteArrayInputStream.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            throw th;
                        }
                        try {
                            bArr = c.a.a(4096);
                            while (true) {
                                int read = byteArrayInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, read);
                            }
                            bufferedOutputStream.flush();
                            b.a();
                            if (g.a()) {
                                g.b("qqcomic.downloader.cache.QRComicFileCache", g.d, "QRComicFileCache save success dataLen=" + bArr2.length);
                            }
                            c.a.a(bArr);
                            if (byteArrayInputStream != null) {
                                byteArrayInputStream.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                        } catch (IOException e5) {
                            e = e5;
                            e.printStackTrace();
                            c.a.a(bArr);
                            if (byteArrayInputStream != null) {
                                byteArrayInputStream.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            return false;
                        }
                    }
                }
                bufferedOutputStream = null;
                byteArrayInputStream = null;
                try {
                    c.a.a(bArr);
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                } catch (Exception e32) {
                    e32.printStackTrace();
                }
            } catch (IOException e6) {
                e = e6;
                bufferedOutputStream = null;
                byteArrayInputStream = null;
                e.printStackTrace();
                c.a.a(bArr);
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                byteArrayInputStream = null;
                c.a.a(bArr);
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return false;
    }
}
