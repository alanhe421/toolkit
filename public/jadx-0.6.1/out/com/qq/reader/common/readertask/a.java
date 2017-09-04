package com.qq.reader.common.readertask;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.tencent.android.tpush.common.Constants;

/* compiled from: AutoTaskQueueDispatcher */
public class a implements com.qq.reader.common.readertask.NetworkStateForConfig.a, Runnable {
    private static final Object b = new Object();
    private g a = g.a();

    public a() {
        NetworkStateForConfig.a().a((com.qq.reader.common.readertask.NetworkStateForConfig.a) this);
    }

    public void run() {
        Thread.currentThread().setName("AutoTaskQueueDispatcher Thread");
        while (!Thread.currentThread().isInterrupted()) {
            c.a(Constants.LogTag, "try get task");
            if (!ao.e(ReaderApplication.getApplicationImp().getApplicationContext())) {
                synchronized (b) {
                    b.wait();
                }
                try {
                    c.a(Constants.LogTag, "no network wait 5 min");
                } catch (InterruptedException e) {
                    c.a(Constants.LogTag, "TaskDispatcher is interrupted for shutting down." + e);
                    c.a(Constants.LogTag, "TaskDispatcher thread is terminated.");
                    return;
                } catch (Throwable th) {
                    c.a(Constants.LogTag, "TaskDispatcher thread is terminated.");
                }
            }
            ReaderTask e2 = f.b().e();
            if (e2 != null) {
                c.a(Constants.LogTag, "task key : " + e2.getTaskKey());
                this.a.a(e2);
            }
        }
        c.a(Constants.LogTag, "--------------isInterrupted--------------");
        c.a(Constants.LogTag, "TaskDispatcher thread is terminated.");
    }

    public void a(boolean z) {
        if (z) {
            c.a(Constants.LogTag, "onNetworkConnect.....notify()");
            synchronized (b) {
                b.notify();
            }
        }
    }
}
