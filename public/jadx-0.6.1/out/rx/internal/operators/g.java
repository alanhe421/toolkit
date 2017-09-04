package rx.internal.operators;

import rx.a;
import rx.a.b;
import rx.c;
import rx.d;
import rx.e;
import rx.f;

/* compiled from: OperatorSubscribeOn */
public class g<T> implements b<T, a<T>> {
    private final d a;

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public g(d dVar) {
        this.a = dVar;
    }

    public e<? super a<T>> a(final e<? super T> eVar) {
        final f a = this.a.a();
        eVar.a(a);
        return new e<a<T>>(this, eVar) {
            final /* synthetic */ g c;

            public /* synthetic */ void onNext(Object obj) {
                a((a) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                eVar.onError(th);
            }

            public void a(final a<T> aVar) {
                a.a(new rx.b.a(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void call() {
                        final Thread currentThread = Thread.currentThread();
                        aVar.a(new e<T>(this, eVar) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void onCompleted() {
                                eVar.onCompleted();
                            }

                            public void onError(Throwable th) {
                                eVar.onError(th);
                            }

                            public void onNext(T t) {
                                eVar.onNext(t);
                            }

                            public void a(final c cVar) {
                                eVar.a(new c(this) {
                                    final /* synthetic */ AnonymousClass1 b;

                                    public void request(final long j) {
                                        if (Thread.currentThread() == currentThread) {
                                            cVar.request(j);
                                        } else {
                                            a.a(new rx.b.a(this) {
                                                final /* synthetic */ AnonymousClass1 b;

                                                public void call() {
                                                    cVar.request(j);
                                                }
                                            });
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };
    }
}
