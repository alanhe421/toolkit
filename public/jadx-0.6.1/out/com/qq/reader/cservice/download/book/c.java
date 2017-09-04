package com.qq.reader.cservice.download.book;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.k;
import com.qq.reader.common.download.task.state.TaskActionEnum;
import com.qq.reader.common.monitor.a.b;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.readengine.model.a;
import java.io.File;
import java.io.IOException;

/* compiled from: DownloadBookWorker */
public class c extends com.qq.reader.common.download.task.c {
    public c(k kVar, g gVar, Object obj, Context context) {
        super(kVar, gVar, obj, context);
    }

    private String d(f fVar) {
        String valueOf = String.valueOf(fVar.getId());
        String bookFormat = fVar.getBookFormat();
        g gVar = new g(valueOf);
        gVar.e(b.a(valueOf));
        gVar.f(bookFormat);
        new h(this.e, gVar).run();
        if (!TextUtils.isEmpty(gVar.c())) {
            return gVar.c();
        }
        valueOf = gVar.d();
        if (TextUtils.isEmpty(valueOf) || !a.n(fVar.getFilePath())) {
            return valueOf;
        }
        Mark e = i.c().e(String.valueOf(fVar.getId()));
        if (!(e instanceof DownloadMark)) {
            return valueOf;
        }
        i.c().b(e.getId());
        fVar.setFilePath(null);
        fVar.setBookFormat(a.q(fVar.getBookFormat()));
        e.setBookName(fVar.getFullName());
        e.setId(fVar.getFilePath());
        i.c().b(fVar.getId(), fVar.getFilePath());
        i.c().a(e);
        i.c().a(e, true);
        return valueOf;
    }

    protected void a() {
        if (TextUtils.isEmpty(this.a.getObjectURI())) {
            this.a.setObjectURI(d(this.a));
        }
    }

    protected boolean b() {
        if (!c() && c(this.a)) {
            this.f.a(this.a, TaskActionEnum.Receive);
        }
        if (!(this.a instanceof DownloadBookTask) || !((DownloadBookTask) this.a).getIsOnlyDownLoadIcon()) {
            return true;
        }
        this.f.a(this.a, TaskActionEnum.Pause);
        return false;
    }

    protected void a(f fVar) {
        b(fVar);
    }

    protected void b(f fVar) {
        try {
            new d(this.f, fVar, this.e).a();
        } catch (IOException e) {
            com.qq.reader.common.monitor.f.a(h(), e.toString());
            this.f.a((g) fVar, TaskActionEnum.Err);
        }
        if (fVar instanceof DownloadBookTask) {
            Mark b = i.c().b(String.valueOf(fVar.getId()), false);
            if (((DownloadBookTask) fVar).getFromType() == 1 || b == null) {
                i.c().c(String.valueOf(fVar.getId()));
                i.c().a((DownloadBookTask) fVar);
            }
        }
    }

    protected boolean c(f fVar) {
        final DownloadBookTask downloadBookTask = (DownloadBookTask) fVar;
        com.qq.reader.common.imageloader.c.a(this.e).b(downloadBookTask.getImageURI(), new com.bumptech.glide.request.b.g<File>(this) {
            final /* synthetic */ c b;

            public void a(File file, com.bumptech.glide.request.a.c<? super File> cVar) {
            }

            public void a(Exception exception, Drawable drawable) {
                super.a(exception, drawable);
                downloadBookTask.setIconDownloadedTimes(downloadBookTask.getIconDownloadedTimes() + 1);
            }
        });
        return true;
    }
}
