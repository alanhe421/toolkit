package rx;

import java.util.concurrent.TimeUnit;
import rx.f.c;

/* compiled from: Scheduler */
public abstract class d {

    /* compiled from: Scheduler */
    public static abstract class a implements f {
        public abstract f a(rx.b.a aVar);

        public abstract f a(rx.b.a aVar, long j, TimeUnit timeUnit);

        public f a(rx.b.a aVar, long j, long j2, TimeUnit timeUnit) {
            final long toNanos = timeUnit.toNanos(j2);
            final long toNanos2 = TimeUnit.MILLISECONDS.toNanos(a()) + timeUnit.toNanos(j);
            final f cVar = new c();
            final rx.b.a aVar2 = aVar;
            rx.b.a anonymousClass1 = new rx.b.a(this) {
                long a = 0;
                final /* synthetic */ a f;

                public void call() {
                    if (!cVar.isUnsubscribed()) {
                        aVar2.call();
                        long j = toNanos2;
                        long j2 = this.a + 1;
                        this.a = j2;
                        cVar.a(this.f.a(this, (j + (j2 * toNanos)) - TimeUnit.MILLISECONDS.toNanos(this.f.a()), TimeUnit.NANOSECONDS));
                    }
                }
            };
            Object cVar2 = new c();
            cVar.a(cVar2);
            cVar2.a(a(anonymousClass1, j, timeUnit));
            return cVar;
        }

        public long a() {
            return System.currentTimeMillis();
        }
    }

    public abstract a a();

    public long b() {
        return System.currentTimeMillis();
    }
}
