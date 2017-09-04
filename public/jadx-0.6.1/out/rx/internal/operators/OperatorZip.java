package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.b.g;
import rx.b.h;
import rx.c;
import rx.e;
import rx.exceptions.OnErrorThrowable;
import rx.f;
import rx.internal.util.d;

public final class OperatorZip<R> implements rx.a.b<R, rx.a<?>[]> {
    final g<? extends R> a;

    private static final class ZipProducer<R> extends AtomicLong implements c {
        private static final long serialVersionUID = -1216676403723546796L;
        private a<R> zipper;

        public ZipProducer(a<R> aVar) {
            this.zipper = aVar;
        }

        public void request(long j) {
            a.a(this, j);
            this.zipper.a();
        }
    }

    private static final class a<R> {
        static final AtomicLongFieldUpdater<a> b = AtomicLongFieldUpdater.newUpdater(a.class, "a");
        static final int c = ((int) (((double) d.c) * 0.7d));
        volatile long a;
        int d = 0;
        private final rx.b<? super R> e;
        private final g<? extends R> f;
        private final rx.f.b g = new rx.f.b();
        private Object[] h;
        private AtomicLong i;

        final class a extends e {
            final d a = d.b();
            final /* synthetic */ a b;

            a(a aVar) {
                this.b = aVar;
            }

            public void a() {
                a((long) d.c);
            }

            public void b(long j) {
                a(j);
            }

            public void onCompleted() {
                this.a.d();
                this.b.a();
            }

            public void onError(Throwable th) {
                this.b.e.onError(th);
            }

            public void onNext(Object obj) {
                try {
                    this.a.a(obj);
                } catch (Throwable e) {
                    onError(e);
                }
                this.b.a();
            }
        }

        public a(e<? super R> eVar, g<? extends R> gVar) {
            this.e = eVar;
            this.f = gVar;
            eVar.a(this.g);
        }

        public void a(rx.a[] aVarArr, AtomicLong atomicLong) {
            int i = 0;
            this.h = new Object[aVarArr.length];
            this.i = atomicLong;
            for (int i2 = 0; i2 < aVarArr.length; i2++) {
                f aVar = new a(this);
                this.h[i2] = aVar;
                this.g.a(aVar);
            }
            while (i < aVarArr.length) {
                aVarArr[i].a((a) this.h[i]);
                i++;
            }
        }

        void a() {
            Object[] objArr = this.h;
            if (objArr != null && b.getAndIncrement(this) == 0) {
                int length = objArr.length;
                rx.b bVar = this.e;
                AtomicLong atomicLong = this.i;
                while (true) {
                    d dVar;
                    Object obj = new Object[length];
                    int i = 1;
                    int i2 = 0;
                    while (i2 < length) {
                        int i3;
                        dVar = ((a) objArr[i2]).a;
                        Object g = dVar.g();
                        if (g == null) {
                            i3 = 0;
                        } else if (dVar.b(g)) {
                            bVar.onCompleted();
                            this.g.unsubscribe();
                            return;
                        } else {
                            obj[i2] = dVar.c(g);
                            i3 = i;
                        }
                        i2++;
                        i = i3;
                    }
                    if (atomicLong.get() > 0 && i != 0) {
                        try {
                            bVar.onNext(this.f.a(obj));
                            atomicLong.decrementAndGet();
                            this.d++;
                            for (Object obj2 : objArr) {
                                dVar = ((a) obj2).a;
                                dVar.f();
                                if (dVar.b(dVar.g())) {
                                    bVar.onCompleted();
                                    this.g.unsubscribe();
                                    return;
                                }
                            }
                            if (this.d > c) {
                                for (Object obj22 : objArr) {
                                    ((a) obj22).b((long) this.d);
                                }
                                this.d = 0;
                            }
                        } catch (Throwable th) {
                            bVar.onError(OnErrorThrowable.addValueAsLastCause(th, obj));
                            return;
                        }
                    } else if (b.decrementAndGet(this) <= 0) {
                        return;
                    }
                }
            }
        }
    }

    private final class b extends e<rx.a[]> {
        final e<? super R> a;
        final a<R> b;
        final ZipProducer<R> c;
        boolean d = false;
        final /* synthetic */ OperatorZip e;

        public /* synthetic */ void onNext(Object obj) {
            a((rx.a[]) obj);
        }

        public b(OperatorZip operatorZip, e<? super R> eVar, a<R> aVar, ZipProducer<R> zipProducer) {
            this.e = operatorZip;
            super(eVar);
            this.a = eVar;
            this.b = aVar;
            this.c = zipProducer;
        }

        public void onCompleted() {
            if (!this.d) {
                this.a.onCompleted();
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void a(rx.a[] aVarArr) {
            if (aVarArr == null || aVarArr.length == 0) {
                this.a.onCompleted();
                return;
            }
            this.d = true;
            this.b.a(aVarArr, this.c);
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public OperatorZip(rx.b.e eVar) {
        this.a = h.a(eVar);
    }

    public OperatorZip(rx.b.f fVar) {
        this.a = h.a(fVar);
    }

    public e<? super rx.a[]> a(e<? super R> eVar) {
        a aVar = new a(eVar, this.a);
        c zipProducer = new ZipProducer(aVar);
        eVar.a(zipProducer);
        return new b(this, eVar, aVar, zipProducer);
    }
}
