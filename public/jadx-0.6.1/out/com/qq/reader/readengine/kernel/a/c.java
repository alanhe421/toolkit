package com.qq.reader.readengine.kernel.a;

import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.framework.mark.UserMark;
import com.qq.reader.module.readpage.h;
import com.qq.reader.plugin.tts.a;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.model.IBook;
import format.epub.common.book.BookEPub;
import format.epub.common.text.model.i;
import format.epub.view.k;
import java.io.IOException;

/* compiled from: QBookCoreEPub */
public class c extends b implements format.epub.b {
    private format.epub.a.b f;
    private h g;

    public c() {
        this.b = new e();
        this.g = new h();
        this.c = new a(this.b);
    }

    public Mark a(int i) {
        if (3 == i) {
            return null;
        }
        try {
            if (!a() || !n()) {
                return null;
            }
            Mark userMark;
            IBook t = this.e.t();
            long e = h().e();
            double doubleValue = g().doubleValue();
            String h = this.b.h();
            if (h.length() == 0) {
                h = "[图片]";
            }
            if (doubleValue > 1.0d) {
                doubleValue = 1.0d;
            }
            String a = ao.a(doubleValue);
            if (i == 0 || i == 7) {
                userMark = new UserMark(t.getBookNetId(), t.getBookPath(), t.getBookName(), 0, 0, e, i, System.currentTimeMillis(), a, h);
            } else {
                userMark = new LocalMark(t.getBookName(), t.getBookPath(), t.getLength(), i, true);
            }
            userMark.setStartPoint(e).setDescriptionStr(h).setPercentStr(a).setAuthor(t.getAuthor()).setEncoding(101).setBookId(t.getBookNetId());
            return userMark;
        } catch (Exception e2) {
            f.a("YT", "bulidBookmark Exception : " + e2.toString());
            return null;
        }
    }

    public void a(boolean z, boolean z2, boolean z3) {
    }

    public long m() {
        i a = ((a) this.e).a();
        return (long) a.b(a.b() - 1);
    }

    public boolean a(g gVar, boolean z, boolean z2, boolean z3) throws IOException {
        ((e) this.b).a(new k((int) ((gVar.e() >> 32) & -1), (int) ((gVar.e() >> 8) & 16777215), (int) (gVar.e() & 255)));
        return true;
    }

    public int a(long j, long j2) {
        return ((e) this.b).a(j, j2);
    }

    public long a(int i, int i2) {
        return ((e) this.b).a(i, i2);
    }

    boolean a(Mark mark, boolean z) {
        boolean v;
        boolean z2;
        Throwable th;
        Throwable th2;
        Message obtain;
        boolean z3 = false;
        boolean z4 = true;
        if (this.e != null) {
            BookEPub bookEPub = (BookEPub) this.e.t();
            if (bookEPub != null) {
                try {
                    v = v();
                    try {
                        Object bookName = mark.getBookName();
                        String author = mark.getAuthor();
                        String bookShortName = mark.getBookShortName();
                        if (bookEPub != null) {
                            if (TextUtils.isEmpty(bookName)) {
                                bookEPub.readMetaInfo();
                            } else {
                                bookEPub.setBookName(bookName);
                                bookEPub.setTitle(bookShortName);
                                bookEPub.setAuthor(author);
                            }
                        }
                        format.epub.common.a.a.a(bookEPub);
                        System.gc();
                        format.epub.common.a.a a = format.epub.common.a.a.a(bookEPub, (format.epub.b) this);
                        ((a) this.e).b = a;
                        a(a);
                        long j = 0;
                        if (mark != null) {
                            j = mark.getStartPoint();
                        }
                        com.qq.reader.readengine.kernel.h kVar = new k((int) ((j >> 32) & -1), (int) ((j >> 8) & 16777215), (int) (j & 255));
                        if (kVar != null) {
                            ((e) this.b).a(kVar);
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (v) {
                            com.qq.reader.common.drm.teb.a.c(bookEPub.getBookPath());
                        }
                        a(mark, true);
                        throw th2;
                    }
                    try {
                        ((a) this.e).a = true;
                        if (v) {
                            com.qq.reader.common.drm.teb.a.c(bookEPub.getBookPath());
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        z3 = true;
                        if (v) {
                            com.qq.reader.common.drm.teb.a.c(bookEPub.getBookPath());
                        }
                        a(mark, true);
                        throw th2;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    v = false;
                    if (v) {
                        com.qq.reader.common.drm.teb.a.c(bookEPub.getBookPath());
                    }
                    if (!(z3 || z)) {
                        a(mark, true);
                    }
                    throw th2;
                }
                if (z4) {
                    obtain = Message.obtain();
                    obtain.what = 1127;
                    this.d.sendMessage(obtain);
                } else if (z) {
                    obtain = Message.obtain();
                    obtain.what = 1117;
                    this.d.sendMessage(obtain);
                }
                return z4;
            }
        }
        z4 = false;
        if (z4) {
            obtain = Message.obtain();
            obtain.what = 1127;
            this.d.sendMessage(obtain);
        } else if (z) {
            obtain = Message.obtain();
            obtain.what = 1117;
            this.d.sendMessage(obtain);
        }
        return z4;
    }

    public boolean a(Mark mark) {
        return a(mark, false);
    }

    private boolean v() {
        IBook t = this.e.t();
        if (!com.qq.reader.readengine.model.a.j(t.getBookPath()) || t.getEncrypted_flag() != 0) {
            return false;
        }
        com.qq.reader.common.drm.teb.a.b(t.getBookPath());
        return true;
    }

    public void d(int i) {
        Message obtain = Message.obtain();
        obtain.what = 1206;
        obtain.arg1 = i;
        this.d.sendMessage(obtain);
    }

    public void a(final format.epub.common.a.a aVar) {
        this.f = new format.epub.a.b();
        ((format.epub.view.a) c()).a(this.f, new format.epub.a.a(this) {
            final /* synthetic */ c b;

            public void a(String str) {
                if (aVar != null) {
                    format.epub.common.a.a.a a = aVar.a(str);
                    if (a != null && a.a == null) {
                        ((e) this.b.b).a(new k(a.b, 0, 0));
                    }
                }
                Message obtainMessage = this.b.d.obtainMessage();
                obtainMessage.what = 1116;
                this.b.d.sendMessage(obtainMessage);
            }
        });
    }

    public h o() {
        return this.g;
    }
}
