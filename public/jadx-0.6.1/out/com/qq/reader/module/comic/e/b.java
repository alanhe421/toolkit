package com.qq.reader.module.comic.e;

import android.content.Context;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.entity.h;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;

/* compiled from: ComicDetailHandle */
public class b {
    public static void a(Context context, h hVar) {
        if (hVar != null) {
            if (i.c().e(String.valueOf(hVar.a())) == null) {
                String a = hVar.a();
                String b = hVar.b();
                Mark comicBookMark = new ComicBookMark(Long.parseLong(a), b);
                comicBookMark.setBookId(Long.parseLong(a));
                comicBookMark.setBookName(b);
                comicBookMark.setAuthor(hVar.d());
                comicBookMark.setHasNewContent(false);
                comicBookMark.setId(a);
                comicBookMark.setLastRead(true);
                comicBookMark.setTotalChapterCount(hVar.a);
                comicBookMark.setCoverUrl(ao.h(Long.parseLong(a)));
                comicBookMark.setOperateTime(System.currentTimeMillis());
                comicBookMark.setLimitFreeEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(hVar.l().b())));
                comicBookMark.setDiscount(hVar.l().a());
                i.c().a(comicBookMark, false);
                OnlineTag onlineTag = new OnlineTag(a, "", 0);
                onlineTag.d(comicBookMark.getTotalChapterCount());
                v.b().b(onlineTag);
                g gVar = new g(Long.parseLong(a), 0, 3);
                gVar.b(Long.parseLong(a));
                gVar.d(b);
                a.b(gVar, null);
                af.a(context, context.getString(R.string.bookshelf_add_success), 0).a();
                return;
            }
            af.a(context, context.getString(R.string.bookshelf_had_book), 0).a();
        }
    }
}
