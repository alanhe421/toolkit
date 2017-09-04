package com.tencent.beacon.b;

import android.util.SparseArray;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ProGuard */
public abstract class c {
    public static boolean a = true;
    private static c b;

    /* compiled from: ProGuard */
    static class a extends c {
        private ScheduledExecutorService b;
        private SparseArray<ScheduledFuture<?>> c;

        public a() {
            this.b = null;
            this.c = null;
            this.b = Executors.newScheduledThreadPool(3, new b());
            this.c = new SparseArray();
        }

        public final synchronized void a(Runnable runnable) {
            if (runnable == null) {
                com.tencent.beacon.e.a.d("task runner should not be null", new Object[0]);
            } else {
                this.b.execute(runnable);
            }
        }

        public final synchronized void a(int i, Runnable runnable, long j, long j2) {
            long j3 = 0;
            synchronized (this) {
                if (runnable == null) {
                    com.tencent.beacon.e.a.d("task runner should not be null", new Object[0]);
                } else {
                    long j4;
                    if (j > 0) {
                        j3 = j;
                    }
                    if (a) {
                        if (j2 <= TracerConfig.LOG_FLUSH_DURATION) {
                            j2 = TracerConfig.LOG_FLUSH_DURATION;
                        }
                        j4 = j2;
                    } else {
                        j4 = j2;
                    }
                    a(i);
                    ScheduledFuture scheduleAtFixedRate = this.b.scheduleAtFixedRate(runnable, j3, j4, TimeUnit.MILLISECONDS);
                    if (scheduleAtFixedRate != null) {
                        com.tencent.beacon.e.a.b("add a new future! taskId: %d , periodTime: %d", Integer.valueOf(i), Long.valueOf(j4));
                        this.c.put(i, scheduleAtFixedRate);
                    }
                }
            }
        }

        public final synchronized void a(int i) {
            ScheduledFuture scheduledFuture = (ScheduledFuture) this.c.get(i);
            if (!(scheduledFuture == null || scheduledFuture.isCancelled())) {
                com.tencent.beacon.e.a.b("cancel a old future!", new Object[0]);
                scheduledFuture.cancel(true);
            }
            this.c.remove(i);
        }

        public final synchronized void a(Runnable runnable, long j) {
            if (runnable == null) {
                com.tencent.beacon.e.a.d("task runner should not be null", new Object[0]);
            } else {
                if (j <= 0) {
                    j = 0;
                }
                this.b.schedule(runnable, j, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* compiled from: ProGuard */
    public class b implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            try {
                return new Thread(runnable, "beacon-thread-" + this.a.getAndIncrement());
            } catch (Throwable e) {
                com.tencent.beacon.e.a.a(e);
            } catch (OutOfMemoryError e2) {
                com.tencent.beacon.e.a.d("memory not enough, create thread failed.", new Object[0]);
            }
            return null;
        }
    }

    public abstract void a(int i);

    public abstract void a(int i, Runnable runnable, long j, long j2);

    public abstract void a(Runnable runnable);

    public abstract void a(Runnable runnable, long j);

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new a();
            }
            cVar = b;
        }
        return cVar;
    }
}
