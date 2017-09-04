package com.qq.reader.common.imageloader.a.a.b;

import android.content.Context;
import com.qq.reader.common.imageloader.a.a.a.a.c;
import com.qq.reader.common.imageloader.a.a.b;
import com.qq.reader.common.imageloader.core.assist.QueueProcessingType;
import com.qq.reader.common.imageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: DefaultConfigurationFactory */
public class a {

    /* compiled from: DefaultConfigurationFactory */
    private static class a implements ThreadFactory {
        private static final AtomicInteger a = new AtomicInteger(1);
        private final ThreadGroup b;
        private final AtomicInteger c = new AtomicInteger(1);
        private final String d;
        private final int e;

        a(int i, String str) {
            this.e = i;
            SecurityManager securityManager = System.getSecurityManager();
            this.b = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.d = str + a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.e);
            return thread;
        }
    }

    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (queueProcessingType == QueueProcessingType.LIFO ? 1 : null) != null ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue(), a(i2, "uil-pool-"));
    }

    public static b a() {
        return new c();
    }

    public static b b() {
        return new d();
    }

    public static b a(Context context, b bVar, long j, int i, String str) throws IOException {
        File a = com.qq.reader.common.imageloader.d.b.a(context, str);
        if (a == null) {
            throw new IOException("no create ExternalStorage. by ivanyang");
        } else if (j > 0 || i > 0) {
            return new c(a, bVar, j, i);
        } else {
            return new com.qq.reader.common.imageloader.a.a.a.b(a, bVar);
        }
    }

    private static ThreadFactory a(int i, String str) {
        return new a(i, str);
    }
}
