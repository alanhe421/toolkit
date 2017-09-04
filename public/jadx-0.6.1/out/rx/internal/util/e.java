package rx.internal.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* compiled from: RxThreadFactory */
public final class e implements ThreadFactory {
    static final AtomicLongFieldUpdater<e> c = AtomicLongFieldUpdater.newUpdater(e.class, "b");
    final String a;
    volatile long b;

    public e(String str) {
        this.a = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.a + c.incrementAndGet(this));
        thread.setDaemon(true);
        return thread;
    }
}
