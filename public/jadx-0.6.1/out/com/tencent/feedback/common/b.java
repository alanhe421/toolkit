package com.tencent.feedback.common;

import android.util.SparseArray;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: RQDSRC */
public abstract class b {
    private static b a;

    /* compiled from: RQDSRC */
    static class a extends b {
        private ScheduledExecutorService a;
        private SparseArray<ScheduledFuture<?>> b;

        public a() {
            this(Executors.newScheduledThreadPool(3));
        }

        public a(ScheduledExecutorService scheduledExecutorService) {
            this.a = null;
            this.b = null;
            if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
                throw new IllegalArgumentException("ScheduledExecutorService is not valiable!");
            }
            this.a = scheduledExecutorService;
            this.b = new SparseArray();
        }

        public final synchronized boolean a(Runnable runnable) {
            boolean z = false;
            synchronized (this) {
                if (!c()) {
                    e.d("rqdp{  ScheduleTaskHandlerImp was closed , should not post!}", new Object[0]);
                } else if (runnable == null) {
                    e.d("rqdp{  task runner should not be null}", new Object[0]);
                } else {
                    this.a.execute(runnable);
                    z = true;
                }
            }
            return z;
        }

        public final synchronized boolean a() {
            boolean z = false;
            synchronized (this) {
                e.a("rqdp{  stopAllScheduleTasks start}", new Object[0]);
                if (c()) {
                    e.b("rqdp{  stop All ScheduleTasks!}", new Object[0]);
                    this.a.shutdown();
                    this.a = null;
                    this.b.clear();
                    this.b = null;
                    e.a("rqdp{  stopAllScheduleTasks end}", new Object[0]);
                    z = true;
                } else {
                    e.d("rqdp{  ScheduleTaskHandlerImp was closed , should all stopped!}", new Object[0]);
                }
            }
            return z;
        }

        public final synchronized boolean a(Runnable runnable, long j) {
            boolean z = false;
            synchronized (this) {
                if (!c()) {
                    e.d("rqdp{  ScheduleTaskHandlerImp was closed , should not post!}", new Object[0]);
                } else if (runnable == null) {
                    e.d("rqdp{  task runner should not be null}", new Object[0]);
                } else {
                    if (j <= 0) {
                        j = 0;
                    }
                    this.a.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    z = true;
                }
            }
            return z;
        }

        private synchronized boolean c() {
            boolean z;
            z = (this.a == null || this.a.isShutdown()) ? false : true;
            return z;
        }
    }

    public abstract boolean a();

    public abstract boolean a(Runnable runnable);

    public abstract boolean a(Runnable runnable, long j);

    public static synchronized void a(b bVar) {
        synchronized (b.class) {
            e.a("rqdp{  AsyncTaskHandlerAbs setInstance} " + bVar, new Object[0]);
            if (!(a == null || a == bVar)) {
                a.a();
            }
            a = bVar;
            e.a("rqdp{  AsyncTaskHandlerAbs setInstance end}", new Object[0]);
        }
    }

    public static synchronized b b() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new a();
            }
            bVar = a;
        }
        return bVar;
    }

    public static b a(ScheduledExecutorService scheduledExecutorService) {
        return new a(scheduledExecutorService);
    }
}
