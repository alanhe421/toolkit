package rx.internal.util;

import rx.d;
import rx.e;

/* compiled from: ScalarSynchronousObservable */
public final class f<T> extends rx.a<T> {
    private final T b;

    /* compiled from: ScalarSynchronousObservable */
    class AnonymousClass1 implements rx.a.a<T> {
        final /* synthetic */ Object a;

        AnonymousClass1(Object obj) {
            this.a = obj;
        }

        public /* synthetic */ void call(Object obj) {
            a((e) obj);
        }

        public void a(e<? super T> eVar) {
            eVar.onNext(this.a);
            eVar.onCompleted();
        }
    }

    /* compiled from: ScalarSynchronousObservable */
    static final class a<T> implements rx.a.a<T> {
        private final rx.internal.schedulers.a a;
        private final T b;

        public /* synthetic */ void call(Object obj) {
            a((e) obj);
        }

        a(rx.internal.schedulers.a aVar, T t) {
            this.a = aVar;
            this.b = t;
        }

        public void a(e<? super T> eVar) {
            eVar.a(this.a.a(new c(eVar, this.b)));
        }
    }

    /* compiled from: ScalarSynchronousObservable */
    static final class b<T> implements rx.a.a<T> {
        private final d a;
        private final T b;

        public /* synthetic */ void call(Object obj) {
            a((e) obj);
        }

        b(d dVar, T t) {
            this.a = dVar;
            this.b = t;
        }

        public void a(e<? super T> eVar) {
            rx.f a = this.a.a();
            eVar.a(a);
            a.a(new c(eVar, this.b));
        }
    }

    /* compiled from: ScalarSynchronousObservable */
    static final class c<T> implements rx.b.a {
        private final e<? super T> a;
        private final T b;

        private c(e<? super T> eVar, T t) {
            this.a = eVar;
            this.b = t;
        }

        public void call() {
            try {
                this.a.onNext(this.b);
                this.a.onCompleted();
            } catch (Throwable th) {
                this.a.onError(th);
            }
        }
    }

    public static final <T> f<T> b(T t) {
        return new f(t);
    }

    protected f(T t) {
        super(new AnonymousClass1(t));
        this.b = t;
    }

    public T c() {
        return this.b;
    }

    public rx.a<T> c(d dVar) {
        if (dVar instanceof rx.internal.schedulers.a) {
            return rx.a.a(new a((rx.internal.schedulers.a) dVar, this.b));
        }
        return rx.a.a(new b(dVar, this.b));
    }
}
