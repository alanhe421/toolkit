package com.qq.reader.common.download.task;

import com.qq.reader.common.monitor.f;

/* compiled from: TaskDispatcher */
public class h implements Runnable {
    private volatile boolean a = false;
    private k b;

    public h(k kVar) {
        this.b = kVar;
    }

    public void run() {
        Thread.currentThread().setName("TaskDispatcher Thread");
        while (!this.a && !Thread.currentThread().isInterrupted()) {
            if (this.b.d()) {
                g c = this.b.c();
                if (c != null) {
                    this.b.a(c);
                }
            } else {
                try {
                    synchronized (this) {
                        wait(500);
                    }
                } catch (Exception e) {
                    f.a("TaskDispatcher", "TaskDispatcher is interrupted for shutting down.", e);
                    this.b.n();
                    this.b = null;
                    f.a("TaskDispatcher", "TaskDispatcher thread is terminated.");
                    return;
                } catch (Throwable th) {
                    this.b.n();
                    this.b = null;
                    f.a("TaskDispatcher", "TaskDispatcher thread is terminated.");
                }
            }
        }
        this.b.n();
        this.b = null;
        f.a("TaskDispatcher", "TaskDispatcher thread is terminated.");
    }

    public void a() {
        f.e("Thread = " + Thread.currentThread().getName() + " " + "DownloadTaskDispatcher", "DownloadTaskDispatcher is shutting down ...");
        this.a = true;
    }
}
