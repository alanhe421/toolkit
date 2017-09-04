package com.qq.reader.activity;

import com.hmt.analytics.UpdateManager;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.cloud.a.c;
import com.qq.reader.cservice.cloud.b;
import com.qq.reader.cservice.cloud.d;
import com.qq.reader.cservice.cloud.f;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.e.e;
import com.qq.reader.plugin.audiobook.core.SongInfo;
import com.qq.reader.plugin.audiobook.core.l;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/* compiled from: DelBookHelper */
public class a {
    private final a a;

    /* compiled from: DelBookHelper */
    public interface a {
        void a(int i, Object obj);
    }

    public a(a aVar) {
        this.a = aVar;
    }

    public void a(Mark mark, boolean z) {
        List arrayList = new ArrayList();
        arrayList.add(mark);
        a(arrayList, z);
    }

    private void a(List<Mark> list) {
        Collection q = i.c().q();
        if (q != null && q.size() > 0) {
            list.addAll(q);
        }
    }

    public void a(List<Mark> list, boolean z) {
        SongInfo o;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        SongInfo songInfo = null;
        if (l.a != null) {
            try {
                o = l.a.o();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Mark mark : list) {
                if (mark.getBookId() <= 0) {
                    arrayList2.add(mark);
                } else {
                    arrayList.add(mark);
                }
                if (mark.getType() == 8 && r1 != null && r1.e() == mark.getBookId()) {
                    l.a(ReaderApplication.getApplicationContext());
                }
            }
            g.a().a(new DelBookHelper$1(this, arrayList, z, arrayList2, list));
        }
        o = songInfo;
        for (Mark mark2 : list) {
            if (mark2.getBookId() <= 0) {
                arrayList.add(mark2);
            } else {
                arrayList2.add(mark2);
            }
            l.a(ReaderApplication.getApplicationContext());
        }
        g.a().a(new DelBookHelper$1(this, arrayList, z, arrayList2, list));
    }

    private boolean b(final List<Mark> list, final boolean z) {
        if (list == null || list.size() == 0) {
            return true;
        }
        boolean a = i.c().a((List) list);
        if (a && list.size() > 0) {
            List arrayList = new ArrayList(list.size());
            for (Mark mark : list) {
                com.qq.reader.cservice.cloud.a.a aVar = new com.qq.reader.cservice.cloud.a.a();
                aVar.a(mark.getBookId());
                aVar.a(com.qq.reader.cservice.cloud.a.a.a(mark));
                arrayList.add(aVar);
            }
            com.qq.reader.cservice.cloud.a.g cVar = new c(arrayList);
            cVar.b(hashCode());
            b.a(UpdateManager.b).a(cVar, false, new com.qq.reader.cservice.cloud.a(this) {
                final /* synthetic */ a c;

                public void a(f fVar, boolean z) {
                    if (1 == fVar.b() && "batdel".equals(fVar.a()) && z) {
                        this.c.c(list, z);
                    }
                }

                public void a(d dVar) {
                }
            });
        }
        return a;
    }

    private boolean c(List<Mark> list, boolean z) {
        if (list == null || list.size() == 0) {
            return true;
        }
        boolean b = i.c().b((List) list);
        if (b) {
            if (z) {
                i.c().c((List) list);
            }
            for (Mark mark : list) {
                if (mark.getBookId() > 0) {
                    OnlineTag a = v.b().a(String.valueOf(mark.getBookId()));
                    v.b().c(a);
                    com.qq.reader.cservice.onlineread.b.b(ReaderApplication.getApplicationContext(), a);
                }
                if (mark.getType() == 9) {
                    e.a(mark.getBookId(), z);
                }
                if (z) {
                    ao.a(new File(mark.getId()));
                    ao.a(new File(com.qq.reader.common.drm.a.a(mark.getId())));
                    ao.a(new File(com.qq.reader.common.drm.a.b(mark.getId())));
                    format.epub.common.a.a.b(mark.getId());
                    if (mark.getId().toLowerCase(Locale.CHINA).endsWith(".chm")) {
                        File file = new File(com.qq.reader.common.c.a.bb + mark.getBookName() + "/");
                        if (file.exists()) {
                            ao.c(file);
                        }
                    }
                }
                if (mark.getType() == 8) {
                    com.qq.reader.module.bookstore.search.g.a(ReaderApplication.getApplicationImp()).a(mark.getBookName(), 6);
                } else {
                    com.qq.reader.module.bookstore.search.g.a(ReaderApplication.getApplicationImp()).a(mark.getBookName(), 5);
                }
            }
        }
        return b;
    }
}
