package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.c;
import rx.d;
import rx.e;
import rx.e.g;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.a.ae;
import rx.internal.util.a.w;
import rx.internal.util.h;

/* compiled from: OperatorObserveOn */
public final class f<T> implements rx.a.b<T, T> {
    private final d a;

    /* compiled from: OperatorObserveOn */
    private static final class a<T> extends e<T> {
        static final AtomicLongFieldUpdater<a> h = AtomicLongFieldUpdater.newUpdater(a.class, "g");
        static final AtomicLongFieldUpdater<a> j = AtomicLongFieldUpdater.newUpdater(a.class, "i");
        final e<? super T> a;
        final rx.d.a b;
        final b c;
        final NotificationLite<T> d = NotificationLite.a();
        final Queue<Object> e;
        volatile boolean f = false;
        volatile long g = 0;
        volatile long i;
        volatile Throwable k;
        final rx.b.a l = new rx.b.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void call() {
                this.a.d();
            }
        };

        public a(d dVar, e<? super T> eVar) {
            this.a = eVar;
            this.b = dVar.a();
            if (ae.a()) {
                this.e = new w(rx.internal.util.d.c);
            } else {
                this.e = new h(rx.internal.util.d.c);
            }
            this.c = new b(this.b);
        }

        void b() {
            this.a.a(this.c);
            this.a.a(new c(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void request(long j) {
                    a.a(a.h, this.a, j);
                    this.a.c();
                }
            });
            this.a.a(this.b);
            this.a.a((rx.f) this);
        }

        public void a() {
            a((long) rx.internal.util.d.c);
        }

        public void onNext(T t) {
            if (!isUnsubscribed()) {
                if (this.e.offer(this.d.a((Object) t))) {
                    c();
                } else {
                    onError(new MissingBackpressureException());
                }
            }
        }

        public void onCompleted() {
            if (!isUnsubscribed() && !this.f) {
                this.f = true;
                c();
            }
        }

        public void onError(Throwable th) {
            if (!isUnsubscribed() && !this.f) {
                this.k = th;
                unsubscribe();
                this.f = true;
                c();
            }
        }

        protected void c() {
            if (j.getAndIncrement(this) == 0) {
                this.b.a(this.l);
            }
        }

        void d() {
            int i = 0;
            do {
                this.i = 1;
                long j = this.g;
                long j2 = 0;
                while (!this.a.isUnsubscribed()) {
                    if (this.f) {
                        Throwable th = this.k;
                        if (th != null) {
                            this.e.clear();
                            this.a.onError(th);
                            return;
                        } else if (this.e.isEmpty()) {
                            this.a.onCompleted();
                            return;
                        }
                    }
                    if (j > 0) {
                        Object poll = this.e.poll();
                        if (poll != null) {
                            this.a.onNext(this.d.d(poll));
                            j--;
                            j2++;
                            i++;
                        }
                    }
                    if (j2 > 0 && this.g != Long.MAX_VALUE) {
                        h.addAndGet(this, -j2);
                    }
                }
                return;
            } while (j.decrementAndGet(this) > 0);
            if (i > 0) {
                a((long) i);
            }
        }
    }

    /* compiled from: OperatorObserveOn */
    static final class b implements rx.f {
        static final AtomicIntegerFieldUpdater<b> c = AtomicIntegerFieldUpdater.newUpdater(b.class, "b");
        final rx.d.a a;
        volatile int b;
        volatile boolean d = false;

        public b(rx.d.a aVar) {
            this.a = aVar;
        }

        public boolean isUnsubscribed() {
            return this.d;
        }

        public void unsubscribe() {
            if (c.getAndSet(this, 1) == 0) {
                this.a.a(new rx.b.a(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void call() {
                        this.a.a.unsubscribe();
                        this.a.d = true;
                    }
                });
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public f(d dVar) {
        this.a = dVar;
    }

    public e<? super T> a(e<? super T> eVar) {
        if ((this.a instanceof rx.e.b) || (this.a instanceof g)) {
            return eVar;
        }
        e aVar = new a(this.a, eVar);
        aVar.b();
        return aVar;
    }
}
