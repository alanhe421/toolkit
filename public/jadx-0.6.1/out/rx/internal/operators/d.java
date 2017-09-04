package rx.internal.operators;

import rx.a.b;
import rx.e;
import rx.exceptions.OnErrorThrowable;
import rx.exceptions.a;

/* compiled from: OperatorDoOnEach */
public class d<T> implements b<T, T> {
    private final rx.b<? super T> a;

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public d(rx.b<? super T> bVar) {
        this.a = bVar;
    }

    public e<? super T> a(final e<? super T> eVar) {
        return new e<T>(this, eVar) {
            final /* synthetic */ d b;
            private boolean c = false;

            public void onCompleted() {
                if (!this.c) {
                    try {
                        this.b.a.onCompleted();
                        this.c = true;
                        eVar.onCompleted();
                    } catch (Throwable th) {
                        onError(th);
                    }
                }
            }

            public void onError(Throwable th) {
                a.a(th);
                if (!this.c) {
                    this.c = true;
                    try {
                        this.b.a.onError(th);
                        eVar.onError(th);
                    } catch (Throwable th2) {
                        eVar.onError(th2);
                    }
                }
            }

            public void onNext(T t) {
                if (!this.c) {
                    try {
                        this.b.a.onNext(t);
                        eVar.onNext(t);
                    } catch (Throwable th) {
                        onError(OnErrorThrowable.addValueAsLastCause(th, t));
                    }
                }
            }
        };
    }
}
