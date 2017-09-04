package com.qq.reader.module.qmessage.loader;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.module.qmessage.data.MessageDelTask;
import com.qq.reader.module.qmessage.data.MessageLoadDiskDataTask;
import com.qq.reader.module.qmessage.data.MessageObtainTask;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MessageDataLoader */
public class a extends b {
    private static volatile a a = null;

    public static a b() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private a() {
    }

    public void a(long j, Handler handler) {
        a(new WeakReference(handler), j);
    }

    private void a(WeakReference<Handler> weakReference, long j) {
        g.a().a(new MessageDataLoader$1(this, j));
        b((WeakReference) weakReference, j);
    }

    private void b(final WeakReference<Handler> weakReference, long j) {
        ReaderTask messageDelTask = new MessageDelTask(j);
        messageDelTask.registerNetTaskListener(new c(this) {
            final /* synthetic */ a b;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (weakReference.get() != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 8000003;
                    ((Handler) weakReference.get()).sendMessage(obtain);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (weakReference.get() != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 8000004;
                    ((Handler) weakReference.get()).sendMessage(obtain);
                }
            }
        });
        g.a().a(messageDelTask);
    }

    public void a(com.qq.reader.module.qmessage.data.c cVar, Handler handler) {
        if (cVar != null && handler != null) {
            WeakReference weakReference = new WeakReference(handler);
            if (cVar.c() == 2) {
                a(weakReference, cVar);
            } else if (cVar.c() == 0) {
                a(weakReference, cVar);
            } else {
                e(weakReference, cVar);
            }
        }
    }

    private void a(final WeakReference<Handler> weakReference, final com.qq.reader.module.qmessage.data.c cVar) {
        ReaderTask messageLoadDiskDataTask = new MessageLoadDiskDataTask(cVar);
        messageLoadDiskDataTask.setLoadListener(new com.qq.reader.module.bookstore.qnative.storage.disk.a(this) {
            final /* synthetic */ a c;

            public void onLoadSucess(Object obj) {
                com.qq.reader.module.qmessage.data.c cVar;
                if (cVar.c() == 0) {
                    if (cVar.e() < 20) {
                        cVar = new com.qq.reader.module.qmessage.data.c(cVar.b(), 0, cVar.f().b());
                        cVar.a(cVar.d());
                        this.c.e(weakReference, cVar);
                        return;
                    }
                    this.c.f(weakReference, cVar);
                } else if (cVar.c() == 2 && d.z(ReaderApplication.getApplicationImp(), cVar.d())) {
                    this.c.f(weakReference, cVar);
                    cVar = new com.qq.reader.module.qmessage.data.c(0, 1, cVar.f().b());
                    cVar.a(cVar.f().c());
                    cVar.a(cVar.d());
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
        g.a().a(messageLoadDiskDataTask);
    }

    private void b(WeakReference<Handler> weakReference, com.qq.reader.module.qmessage.data.c cVar) {
        int e = cVar.e();
        switch (cVar.c()) {
            case 0:
                if (e < 20) {
                    cVar.a(true);
                    if (e == 0) {
                        f(weakReference, cVar);
                        return;
                    }
                }
                d(weakReference, cVar);
                return;
            case 1:
                if (e == 0) {
                    f(weakReference, cVar);
                    return;
                } else {
                    c(weakReference, cVar);
                    return;
                }
            default:
                return;
        }
    }

    private void c(WeakReference<Handler> weakReference, com.qq.reader.module.qmessage.data.c cVar) {
        com.qq.reader.module.qmessage.data.d f = cVar.f();
        g.a().a(new MessageDataLoader$4(this, f.d(), cVar, weakReference, f));
    }

    private void d(WeakReference<Handler> weakReference, com.qq.reader.module.qmessage.data.c cVar) {
        g.a().a(new MessageDataLoader$5(this, cVar, weakReference));
    }

    private void e(final WeakReference<Handler> weakReference, final com.qq.reader.module.qmessage.data.c cVar) {
        ReaderTask messageObtainTask = new MessageObtainTask(cVar);
        messageObtainTask.registerNetTaskListener(new c(this) {
            final /* synthetic */ a c;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                d.y(ReaderApplication.getApplicationImp(), cVar.d());
                com.qq.reader.module.qmessage.data.d f = cVar.f();
                if (f != null) {
                    f.a(str);
                    this.c.b(weakReference, cVar);
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.c.a(weakReference, cVar, -1);
            }
        });
        g.a().a(messageObtainTask);
    }

    private void a(int i) {
        long c = d.c(ReaderApplication.getApplicationImp());
        if (c > 0 && c < System.currentTimeMillis()) {
            Map hashMap = new HashMap();
            hashMap.put("B1", String.valueOf(i));
            hashMap.put("B2", String.valueOf(System.currentTimeMillis() - c));
            StatisticsManager.a().a("MESSAGE_EXPOSURE_EVENT", hashMap, 111, false);
            d.a(ReaderApplication.getApplicationImp(), 0);
        }
    }

    private synchronized void f(WeakReference<Handler> weakReference, com.qq.reader.module.qmessage.data.c cVar) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 8000001;
            obtain.obj = cVar;
            obtain.arg1 = cVar.e();
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference, com.qq.reader.module.qmessage.data.c cVar, int i) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 8000002;
            obtain.obj = cVar;
            obtain.arg1 = i;
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference) {
        if (weakReference.get() != null) {
            Message obtain = Message.obtain();
            obtain.what = 8000005;
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    public void a() {
        synchronized (a.class) {
            a = null;
        }
    }
}
