package com.qq.reader.module.comic.inject;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.l;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.cloud.f;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookshelf.d;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.view.af;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.activity.reader.a;
import com.qrcomic.c.c;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.e;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;

/* compiled from: ComicBookShelf */
public class b implements c {
    private static final String a = b.class.getSimpleName();

    public boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str) || i.c().e(str) == null) {
            return false;
        }
        return true;
    }

    public void a(a aVar, Context context) {
        if (aVar.i == null || aVar.o == null || aVar.r == null || aVar.B == null || aVar.o.t != 0) {
            af.a(context, "漫画状态读取出现错误", 0).a();
            return;
        }
        String str;
        String str2 = aVar.n;
        String str3 = aVar.i.b;
        String str4 = aVar.o.b;
        int i = aVar.E;
        String str5 = aVar.o.c;
        CharSequence charSequence = aVar.B;
        int i2 = ((ComicSectionPicInfo) aVar.r.get(aVar.C)).top;
        int i3 = aVar.C;
        Mark a = a(aVar);
        if (!a(str2, context)) {
            i.c().a(a, false);
        }
        OnlineTag onlineTag = new OnlineTag(str2, "", 0);
        onlineTag.j(3);
        onlineTag.d(a.getTotalChapterCount());
        v.b().b(onlineTag);
        g gVar = new g(Long.parseLong(str2), 0, 3);
        gVar.b(Long.parseLong(str2));
        gVar.d(str3);
        gVar.d(Long.parseLong(str4));
        gVar.i(str5);
        gVar.a(i3);
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(charSequence)) {
            str = "";
        } else {
            CharSequence charSequence2 = charSequence;
        }
        stringBuilder.append(str).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i2).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(i);
        com.qq.reader.common.monitor.debug.c.d("comicshelf", "addToShelf = " + stringBuilder.toString());
        gVar.j(stringBuilder.toString());
        com.qq.reader.module.comic.e.a.b(gVar, null);
    }

    public boolean b(String str, Context context) {
        Mark e = i.c().e(str);
        if (e != null && e.getPrivateProperty() == 0) {
            return true;
        }
        return false;
    }

    public void a(String str, boolean z, final Handler handler, QRComicReadingBaseActivity qRComicReadingBaseActivity, final com.qrcomic.c.c.b bVar) {
        final Mark e = i.c().e(str);
        if (e != null) {
            if ((e.getPrivateProperty() == 0) == z) {
                return;
            }
            if (e.getPrivateProperty() == 1) {
                d.b(qRComicReadingBaseActivity, e.getBookId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ b d;

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        try {
                            e.setPrivateProperty(0);
                            com.qq.reader.common.readertask.g.a().a(new ComicBookShelf$1$1(this));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        handler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                bVar.a(false);
                                af.a(ReaderApplication.getApplicationContext(), R.string.net_not_available, 1).a();
                            }
                        });
                    }
                }, null);
            } else {
                d.a(qRComicReadingBaseActivity, e.getBookId(), new com.qq.reader.common.readertask.ordinal.c(this) {
                    final /* synthetic */ b d;

                    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                        try {
                            e.setPrivateProperty(1);
                            com.qq.reader.common.readertask.g.a().a(new ComicBookShelf$2$1(this));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                        handler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                bVar.a(true);
                                af.a(ReaderApplication.getApplicationContext(), R.string.net_not_available, 1).a();
                            }
                        });
                    }
                }, null);
            }
        }
    }

    public boolean c(String str, Context context) {
        return !com.qq.reader.appconfig.a.d.s(ReaderApplication.getApplicationImp(), com.qq.reader.common.login.c.c().c());
    }

    public boolean d(String str, Context context) {
        com.qq.reader.appconfig.a.d.a(ReaderApplication.getApplicationImp(), com.qq.reader.common.login.c.c().c(), false);
        return true;
    }

    public boolean a(String str, e eVar) {
        try {
            g b = l.b().b(Long.parseLong(str));
            if (b == null) {
                b = new g(Long.parseLong(str), 0, 3);
            }
            b.b(Long.parseLong(str));
            b.d(Long.parseLong(eVar.d()));
            b.i(eVar.e());
            b.a(eVar.g());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(eVar.f()).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(eVar.h()).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(eVar.m());
            com.qq.reader.common.monitor.debug.c.d("comicshelf", "uploadComicProgress = " + stringBuilder.toString());
            b.j(stringBuilder.toString());
            com.qq.reader.module.comic.e.a.c(b, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a(Context context, final String str, final c.a aVar) {
        Exception exception;
        g gVar = null;
        try {
            g b = l.b().b(Long.parseLong(str));
            if (b == null) {
                try {
                    gVar = new g(Long.parseLong(str), 0, 3);
                    gVar.c(0);
                } catch (Exception e) {
                    Exception exception2 = e;
                    gVar = b;
                    exception = exception2;
                    exception.printStackTrace();
                    if (gVar == null) {
                        com.qq.reader.module.comic.e.a.a(gVar, new com.qq.reader.cservice.cloud.a(this) {
                            final /* synthetic */ b c;

                            public void a(f fVar, boolean z) {
                                if (z) {
                                    String h = fVar.h();
                                    if (!TextUtils.isEmpty(h)) {
                                        String[] a = com.qq.reader.module.comic.e.e.a(h);
                                        if (a != null) {
                                            e a2 = com.qq.reader.module.comic.e.e.a(Long.parseLong(str));
                                            if (a2 != null) {
                                                int parseInt = Integer.parseInt(a[2]);
                                                if (parseInt > a2.m) {
                                                    a2.a = String.valueOf(fVar.g());
                                                    a2.c = String.valueOf(fVar.c());
                                                    a2.g = fVar.f();
                                                    a2.f = a[0];
                                                    a2.h = Integer.parseInt(a[1]);
                                                    a2.m = parseInt;
                                                    a2.e = fVar.i();
                                                    if (aVar != null) {
                                                        aVar.a(a2);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            public void a(com.qq.reader.cservice.cloud.d dVar) {
                            }
                        });
                    }
                }
            }
            gVar = b;
        } catch (Exception e2) {
            exception = e2;
            exception.printStackTrace();
            if (gVar == null) {
                com.qq.reader.module.comic.e.a.a(gVar, /* anonymous class already generated */);
            }
        }
        if (gVar == null) {
            com.qq.reader.module.comic.e.a.a(gVar, /* anonymous class already generated */);
        }
    }

    private ComicBookMark a(a aVar) {
        com.qrcomic.entity.a aVar2 = aVar.i;
        ComicBookMark comicBookMark = new ComicBookMark(Long.parseLong(aVar2.e()), aVar2.f());
        comicBookMark.setBookId(Long.parseLong(aVar2.e()));
        comicBookMark.setBookName(aVar2.f());
        comicBookMark.setTotalChapterCount(aVar2.c);
        comicBookMark.setHasNewContent(false);
        comicBookMark.setId(aVar2.e());
        comicBookMark.setLastRead(true);
        comicBookMark.setCoverUrl(ao.h(Long.parseLong(aVar2.e())));
        comicBookMark.setAuthor(aVar2.q);
        comicBookMark.setPercentStr(String.format("第%d话", new Object[]{Integer.valueOf(aVar.o.h() + 1)}));
        comicBookMark.setOperateTime(System.currentTimeMillis());
        comicBookMark.setLimitFreeEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(aVar2.t())));
        comicBookMark.setDiscount(aVar2.v());
        return comicBookMark;
    }

    public void a(com.qrcomic.entity.a aVar, Context context) {
        com.qq.reader.common.monitor.debug.c.d(a, " addReadHistory comic = " + aVar);
        if (aVar != null && aVar.a != null && aVar.b != null) {
            r.a().a(3, aVar.a, aVar.b);
        }
    }

    public void a(com.qrcomic.entity.f fVar, com.qrcomic.entity.a aVar) {
        ComicBookMark comicBookMark = (ComicBookMark) i.c().e(fVar.c());
        if (comicBookMark != null) {
            long currentTimeMillis = System.currentTimeMillis();
            comicBookMark.setLastRead(true);
            comicBookMark.setReadTime(currentTimeMillis);
            comicBookMark.setOperateTime(currentTimeMillis);
            comicBookMark.setLastReadChapterName(fVar.e());
            comicBookMark.setHasNewContent(false);
            comicBookMark.setPercentStr(String.format("第%d话", new Object[]{Integer.valueOf(fVar.h() + 1)}));
            if (aVar != null) {
                comicBookMark.setLimitFreeEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(aVar.t())));
                comicBookMark.setDiscount(aVar.v());
            }
            i.c().a(comicBookMark, false);
        }
    }

    public void b(a aVar, Context context) {
        try {
            if (!com.qq.reader.common.login.c.b() || aVar == null || !a(aVar.n, context) || l.b().b(Long.parseLong(aVar.n)) != null) {
                return;
            }
            if (a(context, aVar.n, aVar.E)) {
                com.qq.reader.common.monitor.debug.c.d("bluesky", "need add to shelf");
                a(aVar, context);
                return;
            }
            com.qq.reader.common.monitor.debug.c.d("bluesky", "not 5 chapter");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean a(Context context, String str, int i) {
        return i >= 5 && com.qq.reader.module.comic.e.e.a(context, str);
    }

    public boolean e(String str, Context context) {
        if (TextUtils.isEmpty(str) || !a(str, context)) {
            return false;
        }
        new com.qq.reader.activity.a(new com.qq.reader.activity.a.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i, Object obj) {
            }
        }).a(new ComicBookMark(Long.parseLong(str)), true);
        return true;
    }
}
