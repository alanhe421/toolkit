package rx.internal.producers;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.c;
import rx.e;
import rx.exceptions.OnErrorThrowable;
import rx.exceptions.a;

public final class SingleProducer<T> extends AtomicBoolean implements c {
    private static final long serialVersionUID = -3353584923995471404L;
    final e<? super T> child;
    final T value;

    public SingleProducer(e<? super T> eVar, T t) {
        this.child = eVar;
        this.value = t;
    }

    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0 && compareAndSet(false, true)) {
            e eVar = this.child;
            Object obj = this.value;
            if (!eVar.isUnsubscribed()) {
                try {
                    eVar.onNext(obj);
                    if (!eVar.isUnsubscribed()) {
                        eVar.onCompleted();
                    }
                } catch (Throwable th) {
                    a.a(th);
                    eVar.onError(OnErrorThrowable.addValueAsLastCause(th, obj));
                }
            }
        }
    }
}
