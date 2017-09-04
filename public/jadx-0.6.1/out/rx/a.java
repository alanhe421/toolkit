package rx;

import java.util.Arrays;
import rx.b.e;
import rx.d.d;
import rx.exceptions.OnErrorNotImplementedException;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.operators.OperatorZip;
import rx.internal.operators.g;
import rx.internal.util.f;

/* compiled from: Observable */
public class a<T> {
    private static final rx.d.b b = d.a().c();
    final a<T> a;

    /* compiled from: Observable */
    public interface a<T> extends rx.b.b<e<? super T>> {
    }

    /* compiled from: Observable */
    public interface b<R, T> extends rx.b.d<e<? super R>, e<? super T>> {
    }

    /* compiled from: Observable */
    private static class c<T> extends a<T> {

        /* compiled from: Observable */
        class AnonymousClass1 implements a<T> {
            final /* synthetic */ Throwable a;

            AnonymousClass1(Throwable th) {
                this.a = th;
            }

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(e<? super T> eVar) {
                eVar.onError(this.a);
            }
        }

        public c(Throwable th) {
            super(new AnonymousClass1(th));
        }
    }

    protected a(a<T> aVar) {
        this.a = aVar;
    }

    public static final <T> a<T> a(a<T> aVar) {
        return new a(b.a((a) aVar));
    }

    public final <R> a<R> a(final b<? extends R, ? super T> bVar) {
        return new a(new a<R>(this) {
            final /* synthetic */ a b;

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(e<? super R> eVar) {
                OnErrorNotImplementedException onErrorNotImplementedException;
                e eVar2;
                try {
                    eVar2 = (e) a.b.a(bVar).call(eVar);
                    eVar2.a();
                    this.b.a.call(eVar2);
                } catch (Throwable th) {
                    if (th instanceof OnErrorNotImplementedException) {
                        onErrorNotImplementedException = (OnErrorNotImplementedException) th;
                    } else {
                        eVar.onError(th);
                    }
                }
            }
        });
    }

    public static final <T> a<T> a(a<? extends a<? extends T>> aVar) {
        return aVar.a(rx.internal.operators.c.a());
    }

    public static final <T> a<T> a(a<? extends T> aVar, a<? extends T> aVar2) {
        return a(a((Object) aVar, (Object) aVar2));
    }

    public static final <T> a<T> a(a<? extends T> aVar, a<? extends T> aVar2, a<? extends T> aVar3) {
        return a(a((Object) aVar, (Object) aVar2, (Object) aVar3));
    }

    public static final <T> a<T> a(rx.b.c<a<T>> cVar) {
        return a(new rx.internal.operators.b(cVar));
    }

    public static final <T> a<T> a(Throwable th) {
        return new c(th);
    }

    public static final <T> a<T> a(Iterable<? extends T> iterable) {
        return a(new OnSubscribeFromIterable(iterable));
    }

    public static final <T> a<T> a(T t) {
        return f.b(t);
    }

    public static final <T> a<T> a(T t, T t2) {
        return a(Arrays.asList(new Object[]{t, t2}));
    }

    public static final <T> a<T> a(T t, T t2, T t3) {
        return a(Arrays.asList(new Object[]{t, t2, t3}));
    }

    public final a<a<T>> a() {
        return a((Object) this);
    }

    public static final <T1, T2, R> a<R> a(a<? extends T1> aVar, a<? extends T2> aVar2, e<? super T1, ? super T2, ? extends R> eVar) {
        return a(new a[]{aVar, aVar2}).a(new OperatorZip((e) eVar));
    }

    public static final <T1, T2, T3, R> a<R> a(a<? extends T1> aVar, a<? extends T2> aVar2, a<? extends T3> aVar3, rx.b.f<? super T1, ? super T2, ? super T3, ? extends R> fVar) {
        return a(new a[]{aVar, aVar2, aVar3}).a(new OperatorZip((rx.b.f) fVar));
    }

    public final a<T> a(final rx.b.a aVar) {
        return a(new rx.internal.operators.d(new b<T>(this) {
            final /* synthetic */ a b;

            public final void onCompleted() {
                aVar.call();
            }

            public final void onError(Throwable th) {
                aVar.call();
            }

            public final void onNext(T t) {
            }
        }));
    }

    public final <R> a<R> a(rx.b.d<? super T, ? extends R> dVar) {
        return a(new rx.internal.operators.e(dVar));
    }

    public final a<T> a(d dVar) {
        if (this instanceof f) {
            return ((f) this).c(dVar);
        }
        return a(new rx.internal.operators.f(dVar));
    }

    public final f a(final rx.b.b<? super T> bVar) {
        if (bVar != null) {
            return b(new e<T>(this) {
                final /* synthetic */ a b;

                public final void onCompleted() {
                }

                public final void onError(Throwable th) {
                    throw new OnErrorNotImplementedException(th);
                }

                public final void onNext(T t) {
                    bVar.call(t);
                }
            });
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public final f a(e<? super T> eVar) {
        try {
            eVar.a();
            b.a(this, this.a).call(eVar);
            return b.a((f) eVar);
        } catch (Throwable th) {
            rx.exceptions.a.a(th);
            try {
                eVar.onError(b.a(th));
                return rx.f.e.b();
            } catch (OnErrorNotImplementedException e) {
                throw e;
            } catch (Throwable th2) {
                b.a(new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2));
            }
        }
    }

    public final f b(e<? super T> eVar) {
        return a((e) eVar, this);
    }

    private static <T> f a(e<? super T> eVar, a<T> aVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("observer can not be null");
        } else if (aVar.a == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            f aVar2;
            eVar.a();
            if (!(eVar instanceof rx.c.a)) {
                aVar2 = new rx.c.a(eVar);
            }
            try {
                b.a(aVar, aVar.a).call(aVar2);
                return b.a(aVar2);
            } catch (Throwable th) {
                rx.exceptions.a.a(th);
                try {
                    aVar2.onError(b.a(th));
                    return rx.f.e.b();
                } catch (OnErrorNotImplementedException e) {
                    throw e;
                } catch (Throwable th2) {
                    b.a(new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2));
                }
            }
        }
    }

    public final a<T> b(d dVar) {
        if (this instanceof f) {
            return ((f) this).c(dVar);
        }
        return a().a(new g(dVar));
    }
}
