package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.e;
import rx.f;

/* compiled from: OperatorConcat */
public final class c<T> implements rx.a.b<T, rx.a<? extends T>> {

    /* compiled from: OperatorConcat */
    static class a<T> extends e<T> {
        private static final AtomicIntegerFieldUpdater<a> d = AtomicIntegerFieldUpdater.newUpdater(a.class, "c");
        private final e<T> a;
        private final c<T> b;
        private volatile int c = 0;
        private final rx.internal.producers.a e;

        public a(c<T> cVar, e<T> eVar, rx.internal.producers.a aVar) {
            this.b = cVar;
            this.a = eVar;
            this.e = aVar;
        }

        public void onNext(T t) {
            this.a.onNext(t);
            this.b.d();
            this.e.a(1);
        }

        public void onError(Throwable th) {
            if (d.compareAndSet(this, 0, 1)) {
                this.b.onError(th);
            }
        }

        public void onCompleted() {
            if (d.compareAndSet(this, 0, 1)) {
                this.b.b();
            }
        }

        public void a(rx.c cVar) {
            this.e.a(cVar);
        }
    }

    /* compiled from: OperatorConcat */
    static final class b<T> implements rx.c {
        final c<T> a;

        b(c<T> cVar) {
            this.a = cVar;
        }

        public void request(long j) {
            this.a.b(j);
        }
    }

    /* compiled from: OperatorConcat */
    static final class c<T> extends e<rx.a<? extends T>> {
        static final AtomicIntegerFieldUpdater<c> e = AtomicIntegerFieldUpdater.newUpdater(c.class, "d");
        private static final AtomicLongFieldUpdater<c> i = AtomicLongFieldUpdater.newUpdater(c.class, "h");
        final NotificationLite<rx.a<? extends T>> a = NotificationLite.a();
        final ConcurrentLinkedQueue<Object> b;
        volatile a<T> c;
        volatile int d;
        private final e<T> f;
        private final rx.f.d g;
        private volatile long h;
        private final rx.internal.producers.a j;

        public /* synthetic */ void onNext(Object obj) {
            a((rx.a) obj);
        }

        public c(e<T> eVar, rx.f.d dVar) {
            super(eVar);
            this.f = eVar;
            this.g = dVar;
            this.j = new rx.internal.producers.a();
            this.b = new ConcurrentLinkedQueue();
            a(rx.f.e.a(new rx.b.a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void call() {
                    this.a.b.clear();
                }
            }));
        }

        public void a() {
            a(2);
        }

        private void b(long j) {
            if (j > 0) {
                long a = a.a(i, this, j);
                this.j.request(j);
                if (a == 0 && this.c == null && this.d > 0) {
                    c();
                }
            }
        }

        private void d() {
            i.decrementAndGet(this);
        }

        public void a(rx.a<? extends T> aVar) {
            this.b.add(this.a.a((Object) aVar));
            if (e.getAndIncrement(this) == 0) {
                c();
            }
        }

        public void onError(Throwable th) {
            this.f.onError(th);
            unsubscribe();
        }

        public void onCompleted() {
            this.b.add(this.a.b());
            if (e.getAndIncrement(this) == 0) {
                c();
            }
        }

        void b() {
            this.c = null;
            if (e.decrementAndGet(this) > 0) {
                c();
            }
            a(1);
        }

        void c() {
            if (this.h > 0) {
                Object poll = this.b.poll();
                if (this.a.b(poll)) {
                    this.f.onCompleted();
                    return;
                } else if (poll != null) {
                    rx.a aVar = (rx.a) this.a.d(poll);
                    this.c = new a(this, this.f, this.j);
                    this.g.a(this.c);
                    aVar.a(this.c);
                    return;
                } else {
                    return;
                }
            }
            if (this.a.b(this.b.peek())) {
                this.f.onCompleted();
            }
        }
    }

    /* compiled from: OperatorConcat */
    private static final class d {
        static final c<Object> a = new c();
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public static <T> c<T> a() {
        return d.a;
    }

    private c() {
    }

    public e<? super rx.a<? extends T>> a(e<? super T> eVar) {
        e cVar = new rx.c.c(eVar);
        f dVar = new rx.f.d();
        eVar.a(dVar);
        e cVar2 = new c(cVar, dVar);
        eVar.a(new b(cVar2));
        return cVar2;
    }
}
