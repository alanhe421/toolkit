package com.qq.reader.module.comic.e;

import android.content.Context;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDownloadActivity;
import com.qq.reader.module.comic.c.a;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.module.comic.entity.o;
import com.qq.reader.module.comic.task.SectionPayTask;
import com.qrcomic.a.d;
import com.qrcomic.a.j;
import com.qrcomic.entity.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: ComicMultiHandle */
public class f {
    private Context a;
    private NativeBookStoreComicDownloadActivity b;
    private a c;
    private List<o> d;
    private SectionPayTask.a e = new SectionPayTask.a(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void a(com.qrcomic.entity.o<k> oVar) {
            if (oVar != null) {
                try {
                    k kVar = (k) oVar.e;
                    if (oVar.c == 0) {
                        this.a.c.a(kVar);
                        this.a.a();
                        return;
                    }
                    this.a.c.a(kVar, oVar.c, oVar.d);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.a.c.a(null, -1, "");
        }
    };

    public f(Context context) {
        this.a = context;
        if (this.a instanceof NativeBookStoreComicDownloadActivity) {
            this.b = (NativeBookStoreComicDownloadActivity) this.a;
        }
    }

    public void a(String str, List<String> list, int i, int i2, SectionPayTask.a aVar) {
        if (this.b.c()) {
            g.a().a(new SectionPayTask(str, list, i, i2, this.e));
        }
    }

    public synchronized void a() {
        if (this.b.c() && this.b.d()) {
            if (ao.j(this.a) || this.b.a) {
                b(this.d);
                if (this.c != null) {
                    ComicShelfInfo g = this.c.g();
                    if (g != null) {
                        e.a(this.b, g, false);
                    }
                    this.c.e();
                    this.c = null;
                }
            } else if (this.c != null) {
                this.c.f();
            }
        }
    }

    private void b(List<o> list) {
        if (list != null && !list.isEmpty()) {
            List arrayList = new ArrayList();
            for (o oVar : list) {
                com.qrcomic.entity.f fVar = new com.qrcomic.entity.f();
                fVar.a = oVar.c();
                fVar.b = oVar.d();
                fVar.f = oVar.b();
                fVar.d = oVar.g();
                arrayList.add(fVar);
            }
            Collections.sort(arrayList, new Comparator<com.qrcomic.entity.f>(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((com.qrcomic.entity.f) obj, (com.qrcomic.entity.f) obj2);
                }

                public int a(com.qrcomic.entity.f fVar, com.qrcomic.entity.f fVar2) {
                    if (Long.parseLong(fVar.b) > Long.parseLong(fVar2.b)) {
                        return 1;
                    }
                    if (Long.parseLong(fVar.b) < Long.parseLong(fVar2.b)) {
                        return -1;
                    }
                    return 0;
                }
            });
            c(arrayList);
        }
    }

    private void c(final List<com.qrcomic.entity.f> list) {
        j.a().a(new d(this) {
            final /* synthetic */ f b;

            public void run() {
                com.qrcomic.downloader.d.b().a(list, false);
            }
        }, 3, null, false);
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(List<o> list) {
        this.d = list;
    }
}
