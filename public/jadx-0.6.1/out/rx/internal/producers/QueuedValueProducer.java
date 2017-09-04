package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.c;
import rx.e;
import rx.exceptions.OnErrorThrowable;
import rx.internal.operators.a;
import rx.internal.util.a.ad;
import rx.internal.util.a.ae;
import rx.internal.util.atomic.b;

public final class QueuedValueProducer<T> extends AtomicLong implements c {
    static final Object NULL_SENTINEL = new Object();
    private static final long serialVersionUID = 7277121710709137047L;
    final e<? super T> child;
    final Queue<Object> queue;
    final AtomicInteger wip;

    public QueuedValueProducer(e<? super T> eVar) {
        this(eVar, ae.a() ? new ad() : new b());
    }

    public QueuedValueProducer(e<? super T> eVar, Queue<Object> queue) {
        this.child = eVar;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }

    public void request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        } else if (j > 0) {
            a.a(this, j);
            drain();
        }
    }

    public boolean offer(T t) {
        if (t == null) {
            if (!this.queue.offer(NULL_SENTINEL)) {
                return false;
            }
        } else if (!this.queue.offer(t)) {
            return false;
        }
        drain();
        return true;
    }

    private void drain() {
        if (this.wip.getAndIncrement() == 0) {
            e eVar = this.child;
            Queue queue = this.queue;
            while (!eVar.isUnsubscribed()) {
                this.wip.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0) {
                    Object poll = queue.poll();
                    if (poll == null) {
                        break;
                    }
                    try {
                        if (poll == NULL_SENTINEL) {
                            eVar.onNext(null);
                        } else {
                            eVar.onNext(poll);
                        }
                        if (!eVar.isUnsubscribed()) {
                            j--;
                            j2++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        rx.exceptions.a.a(th);
                        if (poll == NULL_SENTINEL) {
                            poll = null;
                        }
                        eVar.onError(OnErrorThrowable.addValueAsLastCause(th, poll));
                        return;
                    }
                }
                if (!(j2 == 0 || get() == Long.MAX_VALUE)) {
                    addAndGet(-j2);
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }
}
