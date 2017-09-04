package com.qq.reader.module.comic;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.view.af;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.c.b;
import com.qrcomic.c.c;
import com.qrcomic.c.d;
import com.qrcomic.c.e;
import com.qrcomic.c.f;
import com.qrcomic.c.g;
import com.qrcomic.c.h;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: ComicHelper */
public class a {
    private static a j;
    private b a;
    private c b;
    private e c;
    private f d;
    private g e;
    private d f;
    private com.qrcomic.c.a g;
    private h h;
    private AtomicBoolean i = new AtomicBoolean(false);

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (j == null) {
                j = new a();
            }
            aVar = j;
        }
        return aVar;
    }

    private a() {
        com.qrcomic.a.f.a = com.qq.reader.appconfig.b.a;
        com.qrcomic.a.f.b = com.qq.reader.appconfig.b.f;
        com.qrcomic.f.d.a(com.qq.reader.appconfig.e.L + "v" + "6_5_3" + "/");
        this.a = new com.qq.reader.module.comic.inject.a();
        this.b = new com.qq.reader.module.comic.inject.b();
        this.c = new 1(this);
        this.d = new com.qq.reader.module.comic.inject.e();
        this.e = new 2(this);
        this.f = new 3(this);
        this.g = new com.qq.reader.module.comic.inject.c();
        this.h = new h(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
    }

    public synchronized boolean a(Context context) {
        boolean z = true;
        synchronized (this) {
            try {
                com.qrcomic.a.h b;
                if (this.i.getAndSet(true)) {
                    com.qq.reader.common.monitor.debug.c.d("ComicHelper", " ComicHelper 已经初始化过了，不能二次初始化");
                    b = com.qrcomic.manager.b.a().b();
                    if (b != null) {
                        if (b.h.d() || b.i.d()) {
                            b.a(context);
                        }
                    }
                }
                b = new com.qrcomic.a.h();
                b.a(context.getApplicationContext(), this.h);
                com.qrcomic.manager.b.a().a(b);
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        return z;
    }

    private void a(String str) {
        Mark f = i.c().f(str);
        if (f != null) {
            f.setOperateTime(System.currentTimeMillis());
            i.c().a(f, false);
        }
    }

    public void a(Context context, String str, String str2, int i, String str3) {
        a(context, str, str2, i, str3, false, 0);
    }

    public void a(Context context, String str, String str2, int i, String str3, boolean z, int i2) {
        if (TextUtils.isEmpty(str) || context == null) {
            if (context != null) {
                af.a(context, (CharSequence) "漫画ID为空，无法开始阅读", 0).a();
            }
        } else if (a(context)) {
            String f;
            int i3;
            String str4;
            a(str);
            if (z) {
                com.qrcomic.entity.e a = com.qq.reader.module.comic.e.e.a(Long.parseLong(str));
                if (a != null) {
                    str2 = a.d();
                    i = a.m();
                    f = a.f();
                    i3 = i;
                    str4 = str2;
                    QRComicReadingBaseActivity.a(context, str, str4, i3, f, 0, false, false, null, false, i2);
                }
            }
            f = str3;
            i3 = i;
            str4 = str2;
            QRComicReadingBaseActivity.a(context, str, str4, i3, f, 0, false, false, null, false, i2);
        } else {
            af.a(context, (CharSequence) "初始化漫画失败，请重试", 0).a();
        }
    }

    public void a(Context context, ComicBookMark comicBookMark) {
        if (comicBookMark != null) {
            comicBookMark.copyValue(com.qq.reader.module.comic.e.e.b(comicBookMark.getCid()));
            a(context, String.valueOf(comicBookMark.getCid()), comicBookMark.getSectionId() > 0 ? String.valueOf(comicBookMark.getSectionId()) : "", comicBookMark.getSectionIndex(), comicBookMark.getPicId() > 0 ? String.valueOf(comicBookMark.getPicId()) : "");
        }
    }

    public void a(Context context, String str) {
        a(context, str, true);
    }

    public void a(Context context, String str, boolean z) {
        String str2 = "";
        int i = 0;
        String str3 = "";
        if (z) {
            com.qrcomic.entity.e a = com.qq.reader.module.comic.e.e.a(Long.parseLong(str));
            if (a != null) {
                str2 = a.d();
                i = a.m();
                str3 = a.f();
            }
        }
        a(context, str, str2, i, str3);
    }

    public String a(long j, int i, int i2) {
        return com.qrcomic.a.i.a(j, (long) i, (long) i2);
    }
}
