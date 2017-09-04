package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.b;
import rx.c;
import rx.e;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.operators.a;
import rx.internal.util.a.ad;
import rx.internal.util.a.ae;

public final class QueuedProducer<T> extends AtomicLong implements b<T>, c {
    static final Object NULL_SENTINEL = new Object();
    private static final long serialVersionUID = 7277121710709137047L;
    final e<? super T> child;
    volatile boolean done;
    Throwable error;
    final Queue<Object> queue;
    final AtomicInteger wip;

    public QueuedProducer(e<? super T> eVar) {
        this(eVar, ae.a() ? new ad() : new rx.internal.util.atomic.b());
    }

    public QueuedProducer(e<? super T> eVar, Queue<Object> queue) {
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

    public void onNext(T t) {
        if (!offer(t)) {
            onError(new MissingBackpressureException());
        }
    }

    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    public void onCompleted() {
        this.done = true;
        drain();
    }

    private boolean checkTerminated(boolean z, boolean z2) {
        if (this.child.isUnsubscribed()) {
            return true;
        }
        if (z) {
            Throwable th = this.error;
            if (th != null) {
                this.queue.clear();
                this.child.onError(th);
                return true;
            } else if (z2) {
                this.child.onCompleted();
                return true;
            }
        }
        return false;
    }

    private void drain() {
        if (this.wip.getAndIncrement() == 0) {
            e eVar = this.child;
            Queue queue = this.queue;
            while (!checkTerminated(this.done, queue.isEmpty())) {
                this.wip.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0) {
                    boolean z = this.done;
                    Object poll = queue.poll();
                    if (!checkTerminated(z, poll == null)) {
                        if (poll == null) {
                            break;
                        }
                        try {
                            if (poll == NULL_SENTINEL) {
                                eVar.onNext(null);
                            } else {
                                eVar.onNext(poll);
                            }
                            j--;
                            j2 = 1 + j2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            rx.exceptions.a.a(th2);
                            eVar.onError(OnErrorThrowable.addValueAsLastCause(th2, poll != NULL_SENTINEL ? poll : null));
                            return;
                        }
                    }
                    return;
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
