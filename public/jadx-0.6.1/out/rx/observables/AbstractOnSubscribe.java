package rx.observables;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.b.d;
import rx.c;
import rx.e;
import rx.exceptions.CompositeException;
import rx.f;

public abstract class AbstractOnSubscribe<T, S> implements rx.a.a<T> {
    private static final d<Object, Object> a = new d<Object, Object>() {
        public Object call(Object obj) {
            return null;
        }
    };

    private static final class SubscriptionCompleter<T, S> extends AtomicBoolean implements f {
        private static final long serialVersionUID = 7993888274897325004L;
        private final b<T, S> state;

        private SubscriptionCompleter(b<T, S> bVar) {
            this.state = bVar;
        }

        public boolean isUnsubscribed() {
            return get();
        }

        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.state.g();
            }
        }
    }

    private static final class a<T, S> implements c {
        final b<T, S> a;

        private a(b<T, S> bVar) {
            this.a = bVar;
        }

        public void request(long j) {
            if (j > 0 && rx.internal.operators.a.a(this.a.d, j) == 0) {
                if (j == Long.MAX_VALUE) {
                    while (!this.a.b.isUnsubscribed()) {
                        if (!a()) {
                            return;
                        }
                    }
                } else if (!this.a.b.isUnsubscribed()) {
                    while (a() && this.a.d.decrementAndGet() > 0) {
                        if (this.a.b.isUnsubscribed()) {
                            return;
                        }
                    }
                }
            }
        }

        protected boolean a() {
            if (!this.a.f()) {
                return false;
            }
            try {
                int a = this.a.a();
                this.a.a.a(this.a);
                if (!this.a.d()) {
                    throw new IllegalStateException("No event produced or stop called @ Phase: " + a + " -> " + this.a.a() + ", Calls: " + this.a.b());
                } else if (this.a.c() || this.a.e()) {
                    this.a.h();
                    return false;
                } else {
                    this.a.g = 1 + this.a.g;
                    this.a.g();
                    return true;
                }
            } catch (Throwable th) {
                this.a.h();
                this.a.b.onError(th);
            } finally {
                this.a.g();
            }
        }
    }

    public static final class b<T, S> {
        private final AbstractOnSubscribe<T, S> a;
        private final e<? super T> b;
        private final S c;
        private final AtomicLong d;
        private final AtomicInteger e;
        private int f;
        private long g;
        private T h;
        private boolean i;
        private boolean j;
        private boolean k;
        private Throwable l;

        private b(AbstractOnSubscribe<T, S> abstractOnSubscribe, e<? super T> eVar, S s) {
            this.a = abstractOnSubscribe;
            this.b = eVar;
            this.c = s;
            this.d = new AtomicLong();
            this.e = new AtomicInteger(1);
        }

        public int a() {
            return this.f;
        }

        public long b() {
            return this.g;
        }

        protected boolean c() {
            if (this.i) {
                Object obj = this.h;
                this.h = null;
                this.i = false;
                try {
                    this.b.onNext(obj);
                } catch (Throwable th) {
                    this.j = true;
                    Throwable th2 = this.l;
                    this.l = null;
                    if (th2 == null) {
                        this.b.onError(th);
                        return true;
                    }
                    this.b.onError(new CompositeException(Arrays.asList(new Throwable[]{th, th2})));
                    return true;
                }
            }
            if (!this.j) {
                return false;
            }
            Throwable th3 = this.l;
            this.l = null;
            if (th3 != null) {
                this.b.onError(th3);
                return true;
            }
            this.b.onCompleted();
            return true;
        }

        protected boolean d() {
            return this.i || this.j || this.k;
        }

        protected boolean e() {
            return this.k;
        }

        protected boolean f() {
            int i = this.e.get();
            if (i == 0) {
                return false;
            }
            if (i == 1 && this.e.compareAndSet(1, 2)) {
                return true;
            }
            throw new IllegalStateException("This is not reentrant nor threadsafe!");
        }

        protected void g() {
            if (this.e.get() > 0 && this.e.decrementAndGet() == 0) {
                this.a.a(this.c);
            }
        }

        protected void h() {
            int i;
            do {
                i = this.e.get();
                if (i <= 0) {
                    return;
                }
            } while (!this.e.compareAndSet(i, 0));
            this.a.a(this.c);
        }
    }

    protected abstract void a(b<T, S> bVar);

    public /* synthetic */ void call(Object obj) {
        b((e) obj);
    }

    protected S a(e<? super T> eVar) {
        return null;
    }

    protected void a(S s) {
    }

    public final void b(e<? super T> eVar) {
        b bVar = new b(eVar, a((e) eVar));
        eVar.a(new SubscriptionCompleter(bVar));
        eVar.a(new a(bVar));
    }
}
