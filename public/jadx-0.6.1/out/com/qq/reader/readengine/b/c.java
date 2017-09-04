package com.qq.reader.readengine.b;

import com.qq.reader.ReaderApplication;
import com.qq.reader.readengine.fileparse.SingleBookFile;
import com.qq.reader.readengine.fileparse.d;
import com.qq.reader.readengine.fileparse.e;
import com.qq.reader.readengine.fileparse.g;
import com.qq.reader.readengine.model.BookTxt;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.readengine.model.MulitFile;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import format.epub.common.text.model.i;
import format.epub.common.text.model.l;
import format.epub.common.utils.m;
import format.epub.common.utils.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ReaderTextSearch */
public class c {
    private static c b;
    private static List<d> g = Collections.synchronizedList(new ArrayList());
    private static a i = null;
    private static b j = null;
    b a;
    private e c;
    private String d;
    private int e;
    private volatile boolean f = false;
    private int h = 0;
    private MulitFile k;
    private int l;
    private List<SingleBookFile> m = new ArrayList();
    private SingleBookFile n = null;
    private int o = 0;
    private StringBuffer p = new StringBuffer();

    /* compiled from: ReaderTextSearch */
    private class a extends Thread {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void run() {
            if (this.a.c != null && this.a.d != null && this.a.d.trim().length() != 0) {
                System.currentTimeMillis();
                if (this.a.c instanceof d) {
                    this.a.c();
                } else if (this.a.c instanceof com.qq.reader.readengine.kernel.a.a) {
                    this.a.d();
                }
            }
        }
    }

    /* compiled from: ReaderTextSearch */
    public interface b {
        void a(c cVar, int i, d dVar);
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    public static void b() {
        b = null;
        g.clear();
        if (i != null) {
            i.d();
            i = null;
            j = null;
        }
    }

    public void a(e eVar, String str, boolean z) throws Exception {
        this.l = eVar.t().getReadType();
        if (g.size() > 0) {
            g.clear();
        }
        if (z) {
            this.c = eVar.u();
            this.d = str.trim();
            this.e = eVar.t().getEncoding();
            i = null;
            j = null;
            this.h = 0;
            this.m.clear();
            this.k = eVar.t().getMulitFile();
            if (this.k != null) {
                for (SingleBookFile singleBookFile : this.k.getBookFileList()) {
                    if (singleBookFile != null && singleBookFile.isExist()) {
                        this.m.add(singleBookFile);
                    }
                }
                if (this.m.size() > 0) {
                    this.o = 0;
                    this.n = (SingleBookFile) this.m.get(this.o);
                }
            }
        }
        this.f = false;
        new a().start();
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void c() {
        String str;
        switch (this.e) {
            case 1:
            case 100:
                str = "UTF-16LE";
                break;
            case 2:
            case 12:
            case 14:
                str = "GBK";
                break;
            case 4:
            case 10:
                str = "UTF-8";
                break;
            case 8:
                str = "UTF-16BE";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            if (this.l != 1 || this.m.size() != 0) {
                try {
                    String bookPath;
                    IBook t;
                    BookTxt bookTxt;
                    String str2;
                    byte[] bytes = this.d.getBytes(str);
                    char[] toCharArray = this.d.toCharArray();
                    int[] a = format.epub.common.utils.c.a(toCharArray);
                    int length = toCharArray.length;
                    int length2 = this.d.substring(this.d.length() - 1, this.d.length()).getBytes(str).length;
                    String str3 = null;
                    if (i == null) {
                        if (this.e == 100) {
                            i = new f((d) this.c, str);
                        } else if (this.l == 1) {
                            bookPath = this.n.getBookPath();
                            t = this.c.t();
                            str3 = com.qq.reader.cservice.onlineread.b.a(ReaderApplication.getApplicationImp(), bookPath, t.getBookNetId(), this.n.getChapterId());
                            bookTxt = new BookTxt(t.getBookName(), str3, t.getAuthor(), 4, "", t.getBookNetId());
                            bookTxt.setEncodingStr(this.c.t().getEncodingStr());
                            bookTxt.setReadType(1);
                            i = new e(new g(bookTxt), str);
                        } else {
                            i = new e((d) this.c, str);
                        }
                        j = new b(i, str);
                        str2 = str3;
                    } else {
                        str2 = null;
                    }
                    while (true) {
                        int chapterId;
                        int i = 0;
                        long c = i.c();
                        char c2 = '\u0000';
                        Object obj = 1;
                        while (c < i.b()) {
                            if (this.f || g.size() >= 50) {
                                this.f = true;
                                if (!this.f) {
                                    if (i != null) {
                                        i.d();
                                    }
                                    if (this.a != null) {
                                        this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                                        return;
                                    }
                                    return;
                                } else if (this.a != null) {
                                    this.a.a(b, 602, null);
                                    return;
                                } else {
                                    return;
                                }
                            }
                            char a2;
                            if (obj != null) {
                                a2 = j.a();
                            } else {
                                a2 = c2;
                            }
                            long c3 = i.c();
                            if (i == -1 || a2 == toCharArray[i]) {
                                int i2 = i + 1;
                                if (i2 == length) {
                                    double a3;
                                    long length3 = c3 - ((long) bytes.length);
                                    long j = c3 - ((long) length2);
                                    c = i.a(length3);
                                    long b = i.b(c3);
                                    String replaceAll = i.a(c, b, length3).replaceAll("||||\t|||", " ");
                                    int e = i.e();
                                    d dVar = new d(length3, j);
                                    if (this.l == 1) {
                                        chapterId = this.n.getChapterId();
                                        com.qq.reader.readengine.kernel.g gVar = new com.qq.reader.readengine.kernel.g();
                                        gVar.a(chapterId, length3);
                                        dVar.c(chapterId);
                                        a3 = d.a(gVar, ((d) this.c).t().getFileCount(), this.k.getFileLength(chapterId - 1));
                                    } else {
                                        a3 = ((double) length3) / ((double) i.b());
                                    }
                                    dVar.a(c);
                                    dVar.b(b);
                                    dVar.b(e + 3);
                                    this.p.setLength(0);
                                    this.p.append("...");
                                    this.p.append(replaceAll);
                                    this.p.append("...");
                                    dVar.a(this.p.toString());
                                    dVar.a(a3);
                                    dVar.a(1);
                                    g.add(dVar);
                                    c = c3;
                                    obj = 1;
                                    i = 0;
                                    c2 = a2;
                                } else {
                                    c = c3;
                                    obj = 1;
                                    i = i2;
                                    c2 = a2;
                                }
                            } else {
                                c = c3;
                                i = a[i];
                                obj = null;
                                c2 = a2;
                            }
                        }
                        if (this.l == 1) {
                            if (str2 != null) {
                                com.qq.reader.cservice.onlineread.b.a(str2);
                            }
                            if (this.o < this.m.size() - 1) {
                                List list = this.m;
                                chapterId = this.o + 1;
                                this.o = chapterId;
                                this.n = (SingleBookFile) list.get(chapterId);
                                bookPath = this.n.getBookPath();
                                t = this.c.t();
                                str3 = com.qq.reader.cservice.onlineread.b.a(ReaderApplication.getApplicationImp(), bookPath, t.getBookNetId(), this.n.getChapterId());
                                bookTxt = new BookTxt(t.getBookName(), str3, t.getAuthor(), 4, "", t.getBookNetId());
                                bookTxt.setEncodingStr(this.c.t().getEncodingStr());
                                bookTxt.setReadType(1);
                                d gVar2 = new g(bookTxt);
                                if (i != null) {
                                    i.d();
                                    i = null;
                                }
                                i = new e(gVar2, str);
                                j = new b(i, str);
                                str2 = str3;
                            }
                        }
                        if (!this.f) {
                            if (i != null) {
                                i.d();
                            }
                            if (this.a != null) {
                                this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                                return;
                            }
                            return;
                        } else if (this.a != null) {
                            this.a.a(b, 602, null);
                            return;
                        } else {
                            return;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (!this.f) {
                        if (i != null) {
                            i.d();
                        }
                        if (this.a != null) {
                            this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                        }
                    } else if (this.a != null) {
                        this.a.a(b, 602, null);
                    }
                } catch (Throwable th) {
                    if (!this.f) {
                        if (i != null) {
                            i.d();
                        }
                        if (this.a != null) {
                            this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                        }
                    } else if (this.a != null) {
                        this.a.a(b, 602, null);
                    }
                }
            } else if (!this.f) {
                if (i != null) {
                    i.d();
                }
                if (this.a != null) {
                    this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                }
            } else if (this.a != null) {
                this.a.a(b, 602, null);
            }
        }
    }

    public void d() {
        try {
            i a = ((com.qq.reader.readengine.kernel.a.a) this.c).a();
            int i = this.h;
            int b = a.b();
            m mVar = new m(this.d, false);
            l lVar = (l) a;
            lVar.getClass();
            format.epub.common.text.model.l.a aVar = new format.epub.common.text.model.l.a(lVar, i);
            int i2 = i;
            i = 0;
            while (i2 < b) {
                if (g.size() >= 50) {
                    this.h = i2;
                    this.f = true;
                    if (this.f) {
                        if (this.a != null) {
                            this.a.a(b, 602, null);
                            return;
                        }
                        return;
                    } else if (this.a != null) {
                        this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                        return;
                    } else {
                        return;
                    }
                }
                int a2;
                int i3 = 0;
                while (aVar.l()) {
                    if (!this.f) {
                        aVar.m();
                        if (aVar.a() == (byte) 1) {
                            char[] b2 = aVar.b();
                            int c = aVar.c();
                            int d = aVar.d();
                            a2 = n.a(b2, c, d, mVar);
                            while (a2 != -1) {
                                if (!this.f) {
                                    int i4 = i3 + a2;
                                    long max = (long) Math.max(c, (c + a2) - 20);
                                    long min = (long) Math.min(((c + a2) + mVar.a()) + 20, (c + d) - 1);
                                    long max2 = ((((long) i2) << 32) | (((long) Math.max(i3, (i3 + a2) - 20)) << 8)) | 0;
                                    long min2 = ((((long) i2) << 32) | (((long) Math.min((i3 + d) - 1, ((i3 + a2) + mVar.a()) + 20)) << 8)) | 0;
                                    d dVar = new d(((((long) i2) << 32) | (((long) i4) << 8)) | 0, ((((long) (((i3 + a2) + mVar.a()) - 1)) << 8) | (((long) i2) << 32)) | 0);
                                    dVar.a(max2);
                                    dVar.b(min2);
                                    dVar.b(Math.min(20, a2) + 3);
                                    String replaceAll = new String(b2, (int) max, (int) ((min - max) + 1)).replaceAll("||||\t|||", " ");
                                    this.p.setLength(0);
                                    this.p.append("...");
                                    this.p.append(replaceAll);
                                    this.p.append("...");
                                    dVar.a(this.p.toString());
                                    dVar.a(((double) (i4 + a.b(i2 - 1))) / ((double) a.b(b - 1)));
                                    dVar.a(2);
                                    g.add(dVar);
                                    i++;
                                    a2 = n.a(b2, c, d, mVar, a2 + 1);
                                } else if (this.f) {
                                    if (this.a != null) {
                                        this.a.a(b, 602, null);
                                        return;
                                    }
                                    return;
                                } else if (this.a != null) {
                                    this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                                    return;
                                } else {
                                    return;
                                }
                            }
                            i3 += d;
                        } else if (aVar.a() != (byte) 4) {
                            i3++;
                        }
                    } else if (this.f) {
                        if (this.a != null) {
                            this.a.a(b, 602, null);
                            return;
                        }
                        return;
                    } else if (this.a != null) {
                        this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
                        return;
                    } else {
                        return;
                    }
                }
                a2 = i2 + 1;
                aVar.a(a2);
                i2 = a2;
            }
            if (this.f) {
                if (this.a != null) {
                    this.a.a(b, 602, null);
                }
            } else if (this.a != null) {
                this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.f) {
                if (this.a != null) {
                    this.a.a(b, 602, null);
                }
            } else if (this.a != null) {
                this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
            }
        } catch (Throwable th) {
            if (this.f) {
                if (this.a != null) {
                    this.a.a(b, 602, null);
                }
            } else if (this.a != null) {
                this.a.a(b, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE, null);
            }
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    public static List<d> e() {
        return g;
    }
}
