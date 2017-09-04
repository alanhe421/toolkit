package com.qq.reader.common.readertask;

import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ReaderTaskFailedManager */
public class f extends b {
    private static f a = null;
    private b b = b.a();
    private c c = c.a();
    private Thread d = new Thread(this.e);
    private a e = new a();

    private f() {
        f();
        c();
    }

    public static synchronized f b() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                synchronized (f.class) {
                    if (a == null) {
                        a = new f();
                    }
                }
            }
            fVar = a;
        }
        return fVar;
    }

    private synchronized void f() {
        ArrayList b = this.b.b();
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                ReaderTask readerTask = (ReaderTask) it.next();
                this.c.a(readerTask);
                c.a(Constants.LogTag, readerTask.getTaskKey());
            }
        }
    }

    public void c() {
        this.d.start();
    }

    public void d() {
        NetworkStateForConfig.a().b(this.e);
        this.d.interrupt();
        c.a(Constants.LogTag, "-----stop mautoTaskQueueDispatcher-----");
    }

    public boolean a(ReaderTask readerTask) {
        c.a("cache", " save task " + readerTask.getTaskKey());
        if (readerTask.getFailedType() != 2) {
            return this.c.a(readerTask);
        }
        if (this.b.a(readerTask)) {
            return this.c.a(readerTask);
        }
        return false;
    }

    public boolean a(String str) {
        c.a("cache", " remove task " + str);
        return this.b.a(str);
    }

    public void b(ReaderTask readerTask) {
        c.a(Constants.LogTag, "onTaskSuccess  task : " + readerTask.getTaskKey());
        this.c.b(readerTask);
    }

    public ReaderTask b(String str) {
        return this.c.a(str);
    }

    public ArrayList<ReaderTask> c(ReaderTask readerTask) {
        return this.c.c(readerTask);
    }

    protected ReaderTask e() throws InterruptedException {
        return this.c.b();
    }

    public void a() {
        synchronized (f.class) {
            this.b.c();
            this.c.c();
            a = null;
        }
    }
}
