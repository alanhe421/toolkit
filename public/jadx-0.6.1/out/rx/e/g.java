package rx.e;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.d;
import rx.f;
import rx.f.e;

/* compiled from: TrampolineScheduler */
public final class g extends d {
    private static final g a = new g();

    /* compiled from: TrampolineScheduler */
    private static class a extends rx.d.a implements f {
        private static final AtomicIntegerFieldUpdater<a> b = AtomicIntegerFieldUpdater.newUpdater(a.class, "a");
        volatile int a;
        private final PriorityBlockingQueue<b> c;
        private final rx.f.a d;
        private final AtomicInteger e;

        private a() {
            this.c = new PriorityBlockingQueue();
            this.d = new rx.f.a();
            this.e = new AtomicInteger();
        }

        public f a(rx.b.a aVar) {
            return a(aVar, a());
        }

        public f a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            long a = a() + timeUnit.toMillis(j);
            return a(new e(aVar, this, a), a);
        }

        private f a(rx.b.a aVar, long j) {
            if (this.d.isUnsubscribed()) {
                return e.b();
            }
            final b bVar = new b(aVar, Long.valueOf(j), b.incrementAndGet(this));
            this.c.add(bVar);
            if (this.e.getAndIncrement() != 0) {
                return e.a(new rx.b.a(this) {
                    final /* synthetic */ a b;

                    public void call() {
                        this.b.c.remove(bVar);
                    }
                });
            }
            do {
                bVar = (b) this.c.poll();
                if (bVar != null) {
                    bVar.a.call();
                }
            } while (this.e.decrementAndGet() > 0);
            return e.b();
        }

        public void unsubscribe() {
            this.d.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.d.isUnsubscribed();
        }
    }

    /* compiled from: TrampolineScheduler */
    private static final class b implements Comparable<b> {
        final rx.b.a a;
        final Long b;
        final int c;

        public /* synthetic */ int compareTo(Object obj) {
            return a((b) obj);
        }

        private b(rx.b.a aVar, Long l, int i) {
            this.a = aVar;
            this.b = l;
            this.c = i;
        }

        public int a(b bVar) {
            int compareTo = this.b.compareTo(bVar.b);
            if (compareTo == 0) {
                return g.b(this.c, bVar.c);
            }
            return compareTo;
        }
    }

    public rx.d.a a() {
        return new a();
    }

    g() {
    }

    private static int b(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }
}
