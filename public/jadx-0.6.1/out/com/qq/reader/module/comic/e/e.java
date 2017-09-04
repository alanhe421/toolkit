package com.qq.reader.module.comic.e;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.a;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.view.af;
import com.qrcomic.a.h;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.b;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.List;

/* compiled from: ComicInfoProxy */
public class e {
    public static void a(ComicBookMark comicBookMark) {
        if (comicBookMark != null) {
            h a = a();
            if (a != null) {
                QRComicManager a2 = a(a);
                com.qrcomic.entity.e a3 = a(comicBookMark.getCid());
                if (a3 == null) {
                    a3 = new com.qrcomic.entity.e();
                }
                a3.b = a.a();
                a3.c = String.valueOf(comicBookMark.getCid());
                a3.a = String.valueOf(comicBookMark.getSectionId());
                a3.m = comicBookMark.getSectionIndex();
                a3.f = String.valueOf(comicBookMark.getPicId());
                a3.g = comicBookMark.getPicIndex();
                a3.h = comicBookMark.getPicOffset();
                a3.e = comicBookMark.getLastReadChapterName();
                a2.a(a3);
            }
        }
    }

    public static com.qrcomic.entity.e a(long j) {
        try {
            if (a.a().a(ReaderApplication.getApplicationContext())) {
                h b = b.a().b();
                return ((QRComicManager) b.a(1)).f(b.a(), String.valueOf(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean a(Context context, String str) {
        Object aK = d.aK(context);
        if (TextUtils.isEmpty(aK)) {
            return false;
        }
        return aK.contains(str);
    }

    public static void a(Context context, ComicShelfInfo comicShelfInfo, boolean z) {
        if (comicShelfInfo != null) {
            CharSequence string;
            String str = "";
            if (i.c().e(String.valueOf(comicShelfInfo.a())) == null) {
                str = comicShelfInfo.a();
                String b = comicShelfInfo.b();
                Mark comicBookMark = new ComicBookMark(Long.parseLong(str), b);
                comicBookMark.setBookId(Long.parseLong(str));
                comicBookMark.setBookName(b);
                comicBookMark.setAuthor(comicShelfInfo.c());
                comicBookMark.setHasNewContent(false);
                comicBookMark.setId(str);
                comicBookMark.setLastRead(true);
                comicBookMark.setTotalChapterCount(comicShelfInfo.d());
                comicBookMark.setCoverUrl(ao.h(Long.parseLong(str)));
                comicBookMark.setOperateTime(System.currentTimeMillis());
                if (comicShelfInfo.e() != 0) {
                    comicBookMark.setLimitFreeEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(comicShelfInfo.e())));
                    comicBookMark.setDiscount(comicShelfInfo.d());
                }
                i.c().a(comicBookMark, false);
                OnlineTag onlineTag = new OnlineTag(str, "", 0);
                onlineTag.j(3);
                onlineTag.d(comicBookMark.getTotalChapterCount());
                v.b().b(onlineTag);
                g gVar = new g(Long.parseLong(str), 0, 3);
                gVar.b(Long.parseLong(str));
                gVar.d(b);
                a.b(gVar, null);
                string = context.getString(R.string.bookshelf_add_success);
            } else {
                string = context.getString(R.string.bookshelf_had_book);
            }
            if (z) {
                af.a(context, string, 0).a();
            }
        }
    }

    public static ComicBookMark b(long j) {
        int i = 0;
        ComicBookMark comicBookMark = new ComicBookMark(j);
        com.qrcomic.entity.e a = a(j);
        if (a == null) {
            return null;
        }
        comicBookMark.setSectionId(Long.parseLong(a.d()));
        int m = a.m() > 0 ? a.m() : 0;
        if (a.g() > 0) {
            i = a.g();
        }
        comicBookMark.setSectionIndex(m);
        comicBookMark.setPicId(Long.parseLong(a.f()));
        comicBookMark.setPicIndex(i);
        comicBookMark.setPicOffset(a.h());
        return comicBookMark;
    }

    public static String[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("\\|");
        if (split.length != 3) {
            return null;
        }
        for (int i = 0; i < split.length; i++) {
            if (TextUtils.isEmpty(split[i])) {
                split[i] = "0";
            }
        }
        return split;
    }

    public static void a(long j, boolean z) {
        try {
            a(a()).a(String.valueOf(j), z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, List<String> list) {
        try {
            a(a()).c(str, (List) list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QRComicManager a(h hVar) {
        return (QRComicManager) hVar.a(1);
    }

    public static h a() {
        h b = b.a().b();
        if (b == null && a.a().a(ReaderApplication.getApplicationContext())) {
            return b.a().b();
        }
        return b;
    }
}
