package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import rx.d;
import rx.f;
import rx.internal.util.e;
import rx.internal.util.g;

/* compiled from: EventLoopsScheduler */
public class a extends d {
    static final int a;
    private static final e c = new e("RxComputationThreadPool-");
    final b b = new b();

    /* compiled from: EventLoopsScheduler */
    private static class a extends rx.d.a {
        private final g a = new g();
        private final rx.f.b b = new rx.f.b();
        private final g c = new g(this.a, this.b);
        private final c d;

        a(c cVar) {
            this.d = cVar;
        }

        public void unsubscribe() {
            this.c.unsubscribe();
        }

        public boolean isUnsubscribed() {
            return this.c.isUnsubscribed();
        }

        public f a(rx.b.a aVar) {
            if (isUnsubscribed()) {
                return rx.f.e.b();
            }
            return this.d.a(aVar, 0, null, this.a);
        }

        public f a(rx.b.a aVar, long j, TimeUnit timeUnit) {
            if (isUnsubscribed()) {
                return rx.f.e.b();
            }
            return this.d.a(aVar, j, timeUnit, this.b);
        }
    }

    /* compiled from: EventLoopsScheduler */
    static final class b {
        final int a = a.a;
        final c[] b = new c[this.a];
        long c;

        b() {
            for (int i = 0; i < this.a; i++) {
                this.b[i] = new c(a.c);
            }
        }

        public c a() {
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % ((long) this.a))];
        }
    }

    /* compiled from: EventLoopsScheduler */
    private static final class c extends b {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        a = intValue;
    }

    public rx.d.a a() {
        return new a(this.b.a());
    }

    public f a(rx.b.a aVar) {
        return this.b.a().b(aVar, -1, TimeUnit.NANOSECONDS);
    }
}
