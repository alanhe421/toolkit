package com.qq.reader.module.question.loader;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import com.qq.reader.module.question.data.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FamousAuthorSayDataLoader extends b {
    private static volatile FamousAuthorSayDataLoader a = null;

    public class DataLoadDiskDataTask extends ReaderDBTask {
        private c mDataPackage;
        private a mLoadListener = null;

        public DataLoadDiskDataTask(c cVar) {
            this.mDataPackage = cVar;
        }

        public void setLoadListener(a aVar) {
            this.mLoadListener = aVar;
        }

        public void run() {
            Object obj = null;
            int c = this.mDataPackage.c();
            long b = this.mDataPackage.b();
            com.qq.reader.module.question.a.a e = this.mDataPackage.e();
            if (e != null) {
                try {
                    ArrayList arrayList = new ArrayList();
                    switch (c) {
                        case 1:
                            arrayList = com.qq.reader.module.question.data.b.b().a(b, 10);
                            break;
                        case 2:
                            arrayList = com.qq.reader.module.question.data.b.b().a(10);
                            break;
                    }
                    e.a(arrayList);
                    obj = 1;
                } catch (Exception e2) {
                }
            }
            if (this.mLoadListener == null) {
                return;
            }
            if (obj != null) {
                this.mLoadListener.onLoadSucess(this.mDataPackage);
            } else {
                this.mLoadListener.onLoadFailed(this.mDataPackage);
            }
        }
    }

    public static FamousAuthorSayDataLoader b() {
        if (a == null) {
            synchronized (FamousAuthorSayDataLoader.class) {
                if (a == null) {
                    a = new FamousAuthorSayDataLoader();
                }
            }
        }
        return a;
    }

    private FamousAuthorSayDataLoader() {
    }

    public void a(c cVar, Handler handler) {
        if (cVar != null && handler != null) {
            WeakReference weakReference = new WeakReference(handler);
            if (cVar.c() == 2) {
                a(weakReference, cVar);
            } else if (cVar.c() == 1) {
                a(weakReference, cVar);
            } else {
                e(weakReference, cVar);
            }
        }
    }

    private void a(final WeakReference<Handler> weakReference, final c cVar) {
        ReaderTask dataLoadDiskDataTask = new DataLoadDiskDataTask(cVar);
        dataLoadDiskDataTask.setLoadListener(new a(this) {
            final /* synthetic */ FamousAuthorSayDataLoader c;

            public void onLoadSucess(Object obj) {
                if (cVar.c() == 1) {
                    if (cVar.g() || cVar.d() < 10) {
                        this.c.e(weakReference, new c(cVar.b(), 1, cVar.e().b()));
                        return;
                    }
                    this.c.f(weakReference, cVar);
                } else if (cVar.c() == 2) {
                    this.c.f(weakReference, cVar);
                    c cVar = new c(0, 0, cVar.e().b());
                    cVar.a(cVar.e().e());
                    this.c.a(weakReference);
                    this.c.e(weakReference, cVar);
                } else {
                    this.c.f(weakReference, cVar);
                }
            }

            public void onLoadFailed(Object obj) {
                this.c.a(weakReference, cVar, -1);
            }
        });
        g.a().a(dataLoadDiskDataTask);
    }

    private void b(WeakReference<Handler> weakReference, c cVar) {
        int d = cVar.d();
        switch (cVar.c()) {
            case 0:
                if (d == 0) {
                    f(weakReference, cVar);
                    return;
                } else {
                    c(weakReference, cVar);
                    return;
                }
            case 1:
                if (d == 0) {
                    cVar.a(true);
                    f(weakReference, cVar);
                    return;
                }
                d(weakReference, cVar);
                return;
            default:
                return;
        }
    }

    private void c(WeakReference<Handler> weakReference, c cVar) {
        final com.qq.reader.module.question.a.a e = cVar.e();
        final long f = e.f();
        final c cVar2 = cVar;
        final WeakReference<Handler> weakReference2 = weakReference;
        g.a().a(new ReaderDBTask() {
            public void run() {
                if (e.c() || com.qq.reader.module.question.data.b.b().b(f)) {
                    com.qq.reader.module.question.data.b.b().a(cVar2);
                } else {
                    com.qq.reader.module.question.data.b.b().a(f);
                    com.qq.reader.module.question.data.b.b().a(cVar2);
                }
                FamousAuthorSayDataLoader.this.f(weakReference2, cVar2);
            }
        });
    }

    private void d(final WeakReference<Handler> weakReference, final c cVar) {
        g.a().a(new ReaderDBTask() {
            public void run() {
                com.qq.reader.module.question.a.a e = cVar.e();
                com.qq.reader.module.question.data.b.b().a(cVar);
                if (com.qq.reader.module.question.data.b.b().a(e)) {
                    try {
                        ArrayList a = com.qq.reader.module.question.data.b.b().a(cVar.b(), 10);
                        e.d();
                        e.a(a);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (cVar.d() < 10) {
                    cVar.a(true);
                }
                FamousAuthorSayDataLoader.this.f(weakReference, cVar);
            }
        });
    }

    private void e(final WeakReference<Handler> weakReference, final c cVar) {
        ReaderTask famousAuthorSayObtainTask = new FamousAuthorSayObtainTask(cVar);
        famousAuthorSayObtainTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ FamousAuthorSayDataLoader c;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.module.question.a.a e = cVar.e();
                if (e != null) {
                    e.a(str);
                    this.c.b(weakReference, cVar);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.c.a(weakReference, cVar, -1);
            }
        });
        g.a().a(famousAuthorSayObtainTask);
    }

    private synchronized void f(WeakReference<Handler> weakReference, c cVar) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 11000501;
            obtain.obj = cVar;
            obtain.arg1 = cVar.d();
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference, c cVar, int i) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 11000502;
            obtain.obj = cVar;
            obtain.arg1 = i;
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 11000505;
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    public void a() {
        synchronized (FamousAuthorSayDataLoader.class) {
            a = null;
        }
    }
}
