package rx.internal.operators;

import rx.a.b;
import rx.b.d;
import rx.exceptions.OnErrorThrowable;
import rx.exceptions.a;

/* compiled from: OperatorMap */
public final class e<T, R> implements b<R, T> {
    private final d<? super T, ? extends R> a;

    public /* synthetic */ Object call(Object obj) {
        return a((rx.e) obj);
    }

    public e(d<? super T, ? extends R> dVar) {
        this.a = dVar;
    }

    public rx.e<? super T> a(final rx.e<? super R> eVar) {
        return new rx.e<T>(this, eVar) {
            final /* synthetic */ e b;

            public void onCompleted() {
                eVar.onCompleted();
            }

            public void onError(Throwable th) {
                eVar.onError(th);
            }

            public void onNext(T t) {
                try {
                    eVar.onNext(this.b.a.call(t));
                } catch (Throwable th) {
                    a.a(th);
                    onError(OnErrorThrowable.addValueAsLastCause(th, t));
                }
            }
        };
    }
}
