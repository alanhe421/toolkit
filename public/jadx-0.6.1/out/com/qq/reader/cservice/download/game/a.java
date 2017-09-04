package com.qq.reader.cservice.download.game;

import android.content.Context;
import android.os.Handler;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.List;

/* compiled from: DownloadGameManagerDelegate */
public class a extends com.qq.reader.common.download.task.a {
    public static final String c = a.class.getSimpleName();
    private b d = new b();
    private Handler e = new Handler();
    private final m f = new m(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            this.a.d.a((d) nVar.d());
        }
    };
    private final m g = new m(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            this.a.a((d) nVar.d());
        }
    };
    private final m h = new m(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            final d dVar = (d) nVar.d();
            this.a.e.postDelayed(new Runnable(this) {
                final /* synthetic */ AnonymousClass3 b;

                public void run() {
                    this.b.a.d.b(dVar);
                }
            }, 200);
        }
    };
    private final m i = new m(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            final d dVar = (d) nVar.d();
            this.a.e.postDelayed(new Runnable(this) {
                final /* synthetic */ AnonymousClass4 b;

                public void run() {
                    this.b.a.d.c(dVar);
                    af.a(ReaderApplication.getApplicationImp(), (int) R.string.download_game_failed, 0).a();
                }
            }, 200);
        }
    };
    private final m j = new m(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            this.a.d.d((d) nVar.d());
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.finish_download_task, 0).a();
        }
    };
    private final m k = new m(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(n nVar) {
            g.a().a(new DownloadGameManagerDelegate$6$1(this, nVar));
        }
    };

    public a() {
        super(1006);
    }

    private void a(d dVar) {
        this.d.a(dVar, f(dVar));
    }

    public void d(com.qq.reader.common.download.task.g gVar) {
        super.d(gVar);
        this.d.d((d) gVar);
        if (gVar.getProgress() != 100) {
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.delete_download_task, 0).a();
        }
    }

    public boolean a(com.qq.reader.common.download.task.g gVar) {
        try {
            Object a = a(a(), ((d) gVar).getId());
            if (a != null) {
                TaskStateEnum state = a.getState();
                if (state.ordinal() == TaskStateEnum.Finished.ordinal() || state.ordinal() == TaskStateEnum.Installing.ordinal()) {
                    d(a);
                    com.qq.reader.common.db.handle.n.a().b(a.getId());
                }
            }
        } catch (Exception e) {
        }
        boolean a2 = super.a(gVar);
        if (a2) {
            a((d) gVar);
        } else if (this.b.e(gVar) != null) {
            af.a(ReaderApplication.getApplicationImp(), (int) R.string.download_task_exist, 0).a();
            e(this.b.e(gVar));
        }
        return a2;
    }

    public void c(com.qq.reader.common.download.task.g gVar) {
        super.c(gVar);
    }

    public void e(com.qq.reader.common.download.task.g gVar) {
        super.e(gVar);
    }

    public synchronized void c() {
        this.a.b(TaskStateEnum.Started, this.f);
        this.a.b(TaskStateEnum.Paused, this.h);
        this.a.b(TaskStateEnum.Prepared, this.g);
        this.a.b(TaskStateEnum.Finished, this.j);
        this.a.b(TaskStateEnum.Failed, this.i);
        this.a.b(TaskStateEnum.DeactiveStarted, this.i);
        this.a.b(TaskStateEnum.DeactivePrepared, this.i);
        this.a.b(TaskStateEnum.Removed, this.k);
        this.d.a();
        super.c();
    }

    public synchronized boolean a(Context context) {
        boolean z;
        if (d()) {
            if (ao.f(ReaderApplication.getApplicationImp())) {
                f();
            }
            z = false;
        } else {
            z = super.a(context);
            this.a.a(TaskStateEnum.Started, this.f);
            this.a.a(TaskStateEnum.Paused, this.h);
            this.a.a(TaskStateEnum.Prepared, this.g);
            this.a.a(TaskStateEnum.Finished, this.j);
            this.a.a(TaskStateEnum.Failed, this.i);
            this.a.a(TaskStateEnum.DeactiveStarted, this.i);
            this.a.a(TaskStateEnum.DeactivePrepared, this.i);
            this.a.a(TaskStateEnum.Removed, this.k);
            if (ao.f(ReaderApplication.getApplicationImp())) {
                f();
            }
        }
        return z;
    }

    private void f() {
        int i = 0;
        for (com.qq.reader.common.download.task.g state : a()) {
            int i2;
            if (state.getState().ordinal() <= TaskStateEnum.Finished.ordinal()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        if (i > 0) {
            this.d.a(i);
        }
    }

    public d a(List<com.qq.reader.common.download.task.g> list, long j) {
        if (list == null) {
            return null;
        }
        for (com.qq.reader.common.download.task.g gVar : list) {
            d dVar = (d) gVar;
            if (dVar.getId() == j) {
                return dVar;
            }
        }
        return null;
    }

    public void e() {
        try {
            List a = com.qq.reader.common.db.handle.n.a().a(ReaderApplication.getInstance().getApplication());
            List<com.qq.reader.common.download.task.g> a2 = a();
            if (a2 != null) {
                for (com.qq.reader.common.download.task.g gVar : a2) {
                    if (a.contains(Long.valueOf(((d) gVar).getId()))) {
                        d(gVar);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private int f(com.qq.reader.common.download.task.g gVar) {
        List<com.qq.reader.common.download.task.g> a = a();
        if (a == null) {
            return R.string.begin_download_task;
        }
        int i = R.string.begin_download_task;
        for (com.qq.reader.common.download.task.g gVar2 : a) {
            int i2 = (gVar2.getState() != TaskStateEnum.Started || gVar2.getName().equals(gVar.getName())) ? i : R.string.begin_download_add_task;
            i = i2;
        }
        return i;
    }
}
