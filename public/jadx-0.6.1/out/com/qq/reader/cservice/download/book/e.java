package com.qq.reader.cservice.download.book;

import android.content.Context;
import com.qq.reader.common.db.handle.o;
import com.qq.reader.common.download.task.a;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;

/* compiled from: DownloadManagerDelegate */
public class e extends a {
    private a c;
    private o d = o.c();
    private final m e = new m(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            if (this.a.c != null) {
                this.a.c.b((DownloadBookTask) nVar.d());
            }
        }
    };

    public e(int i) {
        super(i);
    }

    public boolean a(g gVar) {
        boolean a = super.a(gVar);
        if (a && (gVar instanceof DownloadBookTask)) {
            b((DownloadBookTask) gVar);
        }
        return a;
    }

    private void b(DownloadBookTask downloadBookTask) {
        if (this.c != null) {
            this.c.a(downloadBookTask);
        }
    }

    public void a(DownloadBookTask downloadBookTask) {
        if (!this.b.a((g) downloadBookTask)) {
            this.b.c(downloadBookTask);
            this.d.a(downloadBookTask);
        }
    }

    public DownloadBookTask b(String str) {
        for (int i = 0; i < this.b.c(); i++) {
            DownloadBookTask downloadBookTask = (DownloadBookTask) this.b.a(i);
            if (downloadBookTask != null && downloadBookTask.getName() != null && downloadBookTask.getName().equals(str)) {
                return downloadBookTask;
            }
        }
        return null;
    }

    public DownloadBookTask a(long j) {
        for (int i = 0; i < this.b.c(); i++) {
            DownloadBookTask downloadBookTask = (DownloadBookTask) this.b.a(i);
            if (downloadBookTask != null && 0 != downloadBookTask.getId() && downloadBookTask.getId() == j) {
                return downloadBookTask;
            }
        }
        return null;
    }

    public void f(g gVar) {
        ((DownloadBookTask) gVar).setState(b(gVar.getName()).getState());
        ((DownloadBookTask) gVar).setCurrentSize(0);
        ((DownloadBookTask) gVar).setProgress(0);
        this.b.f(gVar);
        this.a.d(gVar);
        if (gVar instanceof DownloadBookTask) {
            b((DownloadBookTask) gVar);
        }
    }

    public synchronized boolean a(Context context) {
        boolean a;
        this.a.a(TaskStateEnum.Finished, this.e);
        a = super.a(context);
        if (a && this.c == null) {
            this.c = new a(context);
        }
        return a;
    }

    public synchronized void c() {
        super.c();
        this.a.b(TaskStateEnum.Finished, this.e);
    }

    public void e() {
        if (this.c != null) {
            this.c.a();
        }
    }
}
