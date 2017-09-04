package rx.internal.producers;

import java.util.concurrent.atomic.AtomicInteger;
import rx.c;
import rx.e;
import rx.exceptions.OnErrorThrowable;
import rx.exceptions.a;

public final class SingleDelayedProducer<T> extends AtomicInteger implements c {
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 2;
    static final int NO_REQUEST_HAS_VALUE = 1;
    static final int NO_REQUEST_NO_VALUE = 0;
    private static final long serialVersionUID = -2873467947112093874L;
    final e<? super T> child;
    T value;

    public SingleDelayedProducer(e<? super T> eVar) {
        this.child = eVar;
    }

    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j != 0) {
            int i;
            while (true) {
                i = get();
                if (i != 0) {
                    break;
                } else if (compareAndSet(0, 2)) {
                    return;
                }
            }
            if (i == 1 && compareAndSet(1, 3)) {
                emit(this.child, this.value);
            }
        }
    }

    public void setValue(T t) {
        do {
            int i = get();
            if (i == 0) {
                this.value = t;
            } else if (i == 2 && compareAndSet(2, 3)) {
                emit(this.child, t);
                return;
            } else {
                return;
            }
        } while (!compareAndSet(0, 1));
    }

    private static <T> void emit(e<? super T> eVar, T t) {
        if (!eVar.isUnsubscribed()) {
            try {
                eVar.onNext(t);
                if (!eVar.isUnsubscribed()) {
                    eVar.onCompleted();
                }
            } catch (Throwable th) {
                a.a(th);
                eVar.onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }
    }
}
