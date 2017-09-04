package android.support.v4.content;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

abstract class ModernAsyncTask<Params, Progress, Result> {
    public static final Executor a = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, c, b);
    private static final ThreadFactory b = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ModernAsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> c = new LinkedBlockingQueue(10);
    private static volatile Executor d = a;

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }
}
