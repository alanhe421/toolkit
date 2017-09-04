package com.qq.reader.module.readpage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.common.db.handle.l;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.cservice.cloud.a;
import com.qq.reader.cservice.cloud.a.e;
import com.qq.reader.cservice.cloud.b;
import com.qq.reader.cservice.cloud.d;
import com.qq.reader.cservice.cloud.f;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.readengine.model.BookTxt;
import com.qq.reader.readengine.model.BookUmd;
import com.qq.reader.readengine.model.IBook;
import format.epub.common.book.BookEPub;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: CloudModuleProxy */
public class c {
    private Handler a;
    private g b;
    private Mark[] c = null;
    private a d = null;
    private volatile boolean e = false;
    private Context f;
    private int g;

    public c(Context context, Handler handler, int i) {
        this.f = context;
        this.a = handler;
        this.g = i;
        this.d = new a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(f fVar, boolean z) {
                if (fVar.e() != 100 && fVar.b() == 0) {
                    this.a.e = true;
                    if (fVar.e() != 0 && fVar.e() != -1) {
                        if (this.a.b.g() == 0 && ((fVar.g() == 1 || fVar.g() == 0) && fVar.f() == 0)) {
                            this.a.b.c(fVar.d());
                            return;
                        }
                        g gVar = new g(fVar.c(), fVar.d(), this.a.g);
                        gVar.d(fVar.g());
                        gVar.a(fVar.f());
                        if (!this.a.b.a(gVar)) {
                            return;
                        }
                        if (this.a.b.a(gVar) >= 0) {
                            this.a.b.c(fVar.d());
                            return;
                        }
                        this.a.b.c(fVar.d());
                        if (this.a.a != null) {
                            Message obtainMessage = this.a.a.obtainMessage();
                            obtainMessage.what = APPluginErrorCode.ERROR_APP_SYSTEM;
                            obtainMessage.obj = fVar;
                            this.a.a.sendMessage(obtainMessage);
                        }
                    }
                }
            }

            public void a(d dVar) {
                if (this.a.a != null) {
                    this.a.a.obtainMessage(10010, dVar).sendToTarget();
                }
            }
        };
    }

    public void a() {
        b.a(this.f).a(hashCode(), this.d);
    }

    public void a(Mark[] markArr) {
        this.c = markArr;
    }

    public void a(com.qq.reader.readengine.kernel.b bVar, IBook iBook, Object obj) {
        b(bVar, iBook, obj);
        com.qq.reader.cservice.cloud.a.g eVar = new e(this.b.f(), this.b.h(), this.b.i(), this.b.g(), this.b.w());
        eVar.b(hashCode());
        com.qq.reader.framework.a.b b = s.a().b(null, iBook.getBookNetId());
        if (b != null) {
            eVar.d(b.b());
            eVar.c(b.d());
        } else {
            eVar.d(0);
            eVar.c(0);
        }
        b.a(this.f).a(eVar, false, this.d);
    }

    public void b(com.qq.reader.readengine.kernel.b bVar, IBook iBook, Object obj) {
        int[] iArr = new int[]{1, 0};
        if (this.b == null) {
            int[] a;
            ArrayList e = l.b().e();
            if (e != null && e.size() > 0) {
                Iterator it;
                if (obj instanceof Mark) {
                    Mark mark = (Mark) obj;
                    it = e.iterator();
                    while (it.hasNext()) {
                        g gVar = (g) it.next();
                        if (mark.getId().equals(gVar.e())) {
                            a = a(bVar, iBook, mark.getStartPoint(), false);
                            this.b = new g(gVar.f(), gVar.g(), this.g);
                            this.b.d((long) a[0]);
                            this.b.a(a[1]);
                            return;
                        }
                    }
                } else if (obj instanceof OnlineTag) {
                    OnlineTag onlineTag = (OnlineTag) obj;
                    it = e.iterator();
                    while (it.hasNext()) {
                        g gVar2 = (g) it.next();
                        if (onlineTag.k().equals(String.valueOf(gVar2.f()))) {
                            iArr[0] = onlineTag.g();
                            iArr[1] = (int) onlineTag.i();
                            this.b = new g(Long.valueOf(onlineTag.k()).longValue(), gVar2.g(), this.g);
                            this.b.d((long) iArr[0]);
                            this.b.a(iArr[1]);
                            return;
                        }
                    }
                }
            }
            if (this.b == null) {
                long j = -1;
                if (obj instanceof Mark) {
                    Mark mark2 = (Mark) obj;
                    long bookId = mark2.getBookId();
                    a = a(bVar, iBook, mark2.getStartPoint(), false);
                    j = bookId;
                } else {
                    if (obj instanceof OnlineTag) {
                        OnlineTag onlineTag2 = (OnlineTag) obj;
                        j = Long.valueOf(onlineTag2.k()).longValue();
                        iArr[0] = onlineTag2.g();
                        iArr[1] = (int) onlineTag2.i();
                    }
                    a = iArr;
                }
                if (j > 0) {
                    this.b = new g(j, 0, this.g);
                    this.b.d((long) a[0]);
                    this.b.a(a[1]);
                }
            }
        }
    }

    public void c(com.qq.reader.readengine.kernel.b bVar, IBook iBook, Object obj) {
        if (!com.qq.reader.common.login.c.b()) {
            return;
        }
        if ((this.c != null || iBook == null || iBook.getReadType() != 0) && this.b != null) {
            com.qq.reader.cservice.cloud.a.g bVar2;
            g b = l.b().b(this.b.f());
            int[] a;
            if (b == null) {
                if (obj instanceof LocalMark) {
                    a = a(bVar, iBook, ((LocalMark) obj).getStartPoint(), false);
                    bVar2 = new com.qq.reader.cservice.cloud.a.b(this.b.f(), (long) a[0], a[1], this.b.g(), this.g);
                } else if (!(obj instanceof OnlineTag)) {
                    bVar2 = null;
                } else if (this.b.w() == 2) {
                    bVar2 = new com.qq.reader.cservice.cloud.a.b(this.b.f(), this.b.h(), (int) ((OnlineTag) obj).i(), this.b.g(), this.b.w());
                } else {
                    bVar2 = new com.qq.reader.cservice.cloud.a.b(this.b.f(), ((OnlineTag) obj).g() > ((OnlineTag) obj).n() ? (long) ((OnlineTag) obj).n() : (long) ((OnlineTag) obj).g(), (int) ((OnlineTag) obj).i(), this.b.g(), this.g);
                }
                if (bVar2 == null || a(bVar2.h())) {
                    l.b().a(bVar2.b());
                    return;
                }
            } else if (this.e) {
                if (obj instanceof LocalMark) {
                    a = a(bVar, iBook, ((LocalMark) obj).getStartPoint(), false);
                    bVar2 = new com.qq.reader.cservice.cloud.a.f(this.b.f(), (long) a[0], a[1], this.b.g(), this.g);
                } else if (!(obj instanceof OnlineTag)) {
                    bVar2 = null;
                } else if (this.b.w() == 2) {
                    bVar2 = new com.qq.reader.cservice.cloud.a.f(this.b.f(), this.b.h(), (int) ((OnlineTag) obj).i(), this.b.g(), this.b.w());
                } else {
                    bVar2 = new com.qq.reader.cservice.cloud.a.f(this.b.f(), ((OnlineTag) obj).g() > ((OnlineTag) obj).n() ? (long) ((OnlineTag) obj).n() : (long) ((OnlineTag) obj).g(), (int) ((OnlineTag) obj).i(), this.b.g(), this.g);
                }
                if (bVar2 == null || a(bVar2.h())) {
                    l.b().a(bVar2.b());
                    return;
                }
                b.d(bVar2.h());
                b.a(bVar2.i());
                l.b().a(b);
            } else {
                return;
            }
            bVar2.b(hashCode());
            b.a(this.f).a(bVar2, false, this.d);
        }
    }

    public int[] a(com.qq.reader.readengine.kernel.b bVar, IBook iBook, long j, boolean z) {
        int[] iArr = new int[]{1, -1};
        if (this.c != null && this.c.length > 0) {
            int length;
            if ((iBook instanceof BookTxt) || (iBook instanceof BookUmd)) {
                length = this.c.length - 1;
                while (length >= 0) {
                    if (this.c[length].getStartPoint() <= j) {
                        iArr[0] = length + 1;
                        if (z) {
                            iArr[1] = 0;
                        } else {
                            iArr[1] = (int) (j - this.c[length].getStartPoint());
                        }
                        if (iArr[1] == -1) {
                            iArr[1] = (int) j;
                        }
                    } else {
                        length--;
                    }
                }
                if (iArr[1] == -1) {
                    iArr[1] = (int) j;
                }
            } else if (iBook instanceof BookEPub) {
                com.qq.reader.readengine.kernel.a.c cVar = (com.qq.reader.readengine.kernel.a.c) bVar;
                length = this.c.length - 1;
                while (length >= 0) {
                    if (this.c[length].getStartPoint() <= j) {
                        iArr[0] = length + 1;
                        if (z) {
                            iArr[1] = 0;
                        } else {
                            iArr[1] = cVar.a(this.c[length].getStartPoint(), j) * 3;
                        }
                    } else {
                        length--;
                    }
                }
            }
        }
        return iArr;
    }

    public long a(com.qq.reader.readengine.kernel.b bVar, IBook iBook, int i, int i2) {
        int i3 = i - 1;
        if (this.c == null || this.c.length <= 0) {
            return 0;
        }
        long startPoint;
        if (i3 <= this.c.length - 1) {
            startPoint = this.c[i3].getStartPoint();
        } else {
            startPoint = 0;
        }
        if ((iBook instanceof BookTxt) || (iBook instanceof BookUmd)) {
            return ((long) i2) + startPoint;
        }
        if (!(iBook instanceof BookEPub)) {
            return 0;
        }
        return ((com.qq.reader.readengine.kernel.a.c) bVar).a((int) ((startPoint >> 32) & -1), i2 / 3);
    }

    private boolean a(long j) {
        String aK = com.qq.reader.appconfig.a.d.aK(this.f);
        if (aK.length() > 0) {
            if (j > 5) {
                com.qq.reader.appconfig.a.d.r(this.f, String.valueOf(this.b.f()));
            } else if (aK.indexOf(String.valueOf(this.b.f())) != -1) {
                return true;
            }
        }
        return false;
    }

    public g b() {
        return this.b;
    }
}
