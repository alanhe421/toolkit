package rx.e;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.d;
import rx.f;
import rx.internal.util.e;

/* compiled from: CachedThreadScheduler */
final class a extends d {
    private static final e a = new e("RxCachedThreadScheduler-");
    private static final e b = new e("RxCachedWorkerPoolEvictor-");

    /* compiled from: CachedThreadScheduler */
    private static final class a {
        private static a d = new a(60, TimeUnit.SECONDS);
        private final long a;
        private final ConcurrentLinkedQueue<c> b = new ConcurrentLinkedQueue();
        private final ScheduledExecutorService c = Executors.newScheduledThreadPool(1, a.b);

        a(long j, TimeUnit timeUnit) {
            this.a = timeUnit.toNanos(j);
            this.c.scheduleWithFixedDelay(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b();
                }
            }, this.a, this.a, TimeUnit.NANOSECONDS);
        }

        c a() {
            while (!this.b.isEmpty()) {
                c cVar = (c) this.b.poll();
                if (cVar != null) {
                    return cVar;
                }
            }
            return new c(a.a);
        }

        void a(c cVar) {
            cVar.a(c() + this.a);
            this.b.offer(cVar);
        }

        void b() {
            if (!this.b.isEmpty()) {
                long c = c();
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (cVar.c() > c) {
                        return;
                    }
                    if (this.b.remove(cVar)) {
                        cVar.unsubscribe();
                    }
                }
            }
        }

        long c() {
            return System.nanoTime();
        }
    }

    /* compiled from: CachedThreadScheduler */
    private static final class b extends rx.d.a {
        static final AtomicIntegerFieldUpdater<b> b = AtomicIntegerFieldUpdater.newUpdater(b.class, "a");
        volatile int a;
        private final rx.f.b c = new rx.f.b();
        private final c d;

        b(c cVar) {
            this.d = cVar;
        }

        public void unsubscribe() {
            if (b.compareAndSet(this, 0, 1)) {
                a.d.a(this.d);
            }
            this.c.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.c.isUnsubscribed();
        }

        public f a(rx.b.a aVar) {
            return a(aVar, 0, null);
        }

        public f a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            if (this.c.isUnsubscribed()) {
                return rx.f.e.b();
            }
            f b = this.d.b(aVar, j, timeUnit);
            this.c.a(b);
            b.addParent(this.c);
            return b;
        }
    }

    /* compiled from: CachedThreadScheduler */
    private static final class c extends rx.internal.schedulers.b {
        private long c = 0;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        public long c() {
            return this.c;
        }

        public void a(long j) {
            this.c = j;
        }
    }

    a() {
    }

    public rx.d.a a() {
        return new b(a.d.a());
    }
}
