package rx.c;

import rx.e;

/* compiled from: Subscribers */
public final class d {
    public static <T> e<T> a(final e<? super T> eVar) {
        return new e<T>(eVar) {
            public void onCompleted() {
                eVar.onCompleted();
            }

            public void onError(Throwable th) {
                eVar.onError(th);
            }

            public void onNext(T t) {
                eVar.onNext(t);
            }
        };
    }
}
