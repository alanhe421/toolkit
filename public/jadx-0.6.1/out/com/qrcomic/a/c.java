package com.qrcomic.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: CallbackThreadPoolExecutor */
public class c extends ThreadPoolExecutor {
    private Handler a = new Handler(Looper.getMainLooper());

    public c(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i, i2, j, timeUnit, blockingQueue);
    }

    protected void afterExecute(final Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (runnable instanceof d) {
            ((d) runnable).f().b();
            this.a.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    ((d) runnable).f().d();
                }
            });
        }
    }

    protected void beforeExecute(Thread thread, final Runnable runnable) {
        super.beforeExecute(thread, runnable);
        if (runnable instanceof d) {
            ((d) runnable).f().a();
            this.a.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    ((d) runnable).f().c();
                }
            });
        }
    }
}
