package rx.c;

import rx.b;
import rx.e;

/* compiled from: SerializedSubscriber */
public class c<T> extends e<T> {
    private final b<T> a;

    public c(e<? super T> eVar) {
        this(eVar, true);
    }

    public c(e<? super T> eVar, boolean z) {
        super(eVar, z);
        this.a = new b(eVar);
    }

    public void onCompleted() {
        this.a.onCompleted();
    }

    public void onError(Throwable th) {
        this.a.onError(th);
    }

    public void onNext(T t) {
        this.a.onNext(t);
    }
}
