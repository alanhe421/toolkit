package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.a.a;
import rx.c;
import rx.e;

public final class OnSubscribeFromIterable<T> implements a<T> {
    final Iterable<? extends T> a;

    private static final class IterableProducer<T> extends AtomicLong implements c {
        private static final long serialVersionUID = -8730475647105475802L;
        private final Iterator<? extends T> it;
        private final e<? super T> o;

        private IterableProducer(e<? super T> eVar, Iterator<? extends T> it) {
            this.o = eVar;
            this.it = it;
        }

        public void request(long j) {
            if (get() != Long.MAX_VALUE) {
                if (j == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    fastpath();
                } else if (j > 0 && a.a(this, j) == 0) {
                    slowpath(j);
                }
            }
        }

        void slowpath(long j) {
            e eVar = this.o;
            Iterator it = this.it;
            do {
                long j2 = j;
                while (!eVar.isUnsubscribed()) {
                    if (it.hasNext()) {
                        j2--;
                        if (j2 >= 0) {
                            eVar.onNext(it.next());
                        } else {
                            j = addAndGet(-j);
                        }
                    } else if (!eVar.isUnsubscribed()) {
                        eVar.onCompleted();
                        return;
                    } else {
                        return;
                    }
                }
                return;
            } while (j != 0);
        }

        void fastpath() {
            e eVar = this.o;
            Iterator it = this.it;
            while (!eVar.isUnsubscribed()) {
                if (it.hasNext()) {
                    eVar.onNext(it.next());
                } else if (!eVar.isUnsubscribed()) {
                    eVar.onCompleted();
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public /* synthetic */ void call(Object obj) {
        a((e) obj);
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.a = iterable;
    }

    public void a(e<? super T> eVar) {
        Iterator it = this.a.iterator();
        if (it.hasNext() || eVar.isUnsubscribed()) {
            eVar.a(new IterableProducer(eVar, it));
        } else {
            eVar.onCompleted();
        }
    }
}
