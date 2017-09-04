package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.a.b;
import rx.b.c;
import rx.b.d;
import rx.e;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.f;
import rx.internal.util.a.ae;
import rx.internal.util.a.w;

public final class OperatorMapNotification<T, R> implements b<R, T> {
    private final d<? super T, ? extends R> a;
    private final d<? super Throwable, ? extends R> b;
    private final c<? extends R> c;

    static final class SingleEmitter<T> extends AtomicLong implements rx.c, f {
        private static final long serialVersionUID = -249869671366010660L;
        final f cancel;
        final e<? super T> child;
        volatile boolean complete;
        boolean emitting;
        boolean missed;
        final NotificationLite<T> nl;
        final rx.c producer;
        final Queue<Object> queue;

        public SingleEmitter(e<? super T> eVar, rx.c cVar, f fVar) {
            this.child = eVar;
            this.producer = cVar;
            this.cancel = fVar;
            this.queue = ae.a() ? new w(2) : new ConcurrentLinkedQueue();
            this.nl = NotificationLite.a();
        }

        public void request(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 >= 0) {
                    j3 = j2 + j;
                    if (j3 < 0) {
                        j3 = Long.MAX_VALUE;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(j2, j3));
            this.producer.request(j);
            drain();
        }

        void produced(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 >= 0) {
                    j3 = j2 - j;
                    if (j3 < 0) {
                        throw new IllegalStateException("More produced (" + j + ") than requested (" + j2 + ")");
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(j2, j3));
        }

        public void offer(T t) {
            if (this.queue.offer(t)) {
                drain();
                return;
            }
            this.child.onError(new MissingBackpressureException());
            unsubscribe();
        }

        public void offerAndComplete(T t) {
            if (this.queue.offer(t)) {
                this.complete = true;
                drain();
                return;
            }
            this.child.onError(new MissingBackpressureException());
            unsubscribe();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drain() {
            /*
            r8 = this;
            r1 = 1;
            r2 = 0;
            monitor-enter(r8);
            r0 = r8.emitting;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x000c;
        L_0x0007:
            r0 = 1;
            r8.missed = r0;	 Catch:{ all -> 0x0032 }
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
        L_0x000b:
            return;
        L_0x000c:
            r0 = 1;
            r8.emitting = r0;	 Catch:{ all -> 0x0032 }
            r0 = 0;
            r8.missed = r0;	 Catch:{ all -> 0x0032 }
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
        L_0x0013:
            r4 = r8.get();	 Catch:{ all -> 0x0029 }
            r0 = r8.complete;	 Catch:{ all -> 0x0029 }
            r3 = r8.queue;	 Catch:{ all -> 0x0029 }
            r3 = r3.isEmpty();	 Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0035;
        L_0x0021:
            if (r3 == 0) goto L_0x0035;
        L_0x0023:
            r0 = r8.child;	 Catch:{ all -> 0x0029 }
            r0.onCompleted();	 Catch:{ all -> 0x0029 }
            goto L_0x000b;
        L_0x0029:
            r0 = move-exception;
        L_0x002a:
            if (r2 != 0) goto L_0x0031;
        L_0x002c:
            monitor-enter(r8);
            r1 = 0;
            r8.emitting = r1;	 Catch:{ all -> 0x0073 }
            monitor-exit(r8);	 Catch:{ all -> 0x0073 }
        L_0x0031:
            throw r0;
        L_0x0032:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
            throw r0;
        L_0x0035:
            r6 = 0;
            r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r3 <= 0) goto L_0x0053;
        L_0x003b:
            r3 = r8.queue;	 Catch:{ all -> 0x0029 }
            r3 = r3.poll();	 Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x0063;
        L_0x0043:
            r0 = r8.child;	 Catch:{ all -> 0x0029 }
            r4 = r8.nl;	 Catch:{ all -> 0x0029 }
            r3 = r4.d(r3);	 Catch:{ all -> 0x0029 }
            r0.onNext(r3);	 Catch:{ all -> 0x0029 }
            r4 = 1;
            r8.produced(r4);	 Catch:{ all -> 0x0029 }
        L_0x0053:
            monitor-enter(r8);	 Catch:{ all -> 0x0029 }
            r0 = r8.missed;	 Catch:{ all -> 0x0070 }
            if (r0 != 0) goto L_0x006b;
        L_0x0058:
            r0 = 0;
            r8.emitting = r0;	 Catch:{ all -> 0x005d }
            monitor-exit(r8);	 Catch:{ all -> 0x005d }
            goto L_0x000b;
        L_0x005d:
            r0 = move-exception;
        L_0x005e:
            monitor-exit(r8);	 Catch:{ all -> 0x005d }
            throw r0;	 Catch:{ all -> 0x0060 }
        L_0x0060:
            r0 = move-exception;
            r2 = r1;
            goto L_0x002a;
        L_0x0063:
            if (r0 == 0) goto L_0x0053;
        L_0x0065:
            r0 = r8.child;	 Catch:{ all -> 0x0029 }
            r0.onCompleted();	 Catch:{ all -> 0x0029 }
            goto L_0x000b;
        L_0x006b:
            r0 = 0;
            r8.missed = r0;	 Catch:{ all -> 0x0070 }
            monitor-exit(r8);	 Catch:{ all -> 0x0070 }
            goto L_0x0013;
        L_0x0070:
            r0 = move-exception;
            r1 = r2;
            goto L_0x005e;
        L_0x0073:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0073 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMapNotification.SingleEmitter.drain():void");
        }

        public boolean isUnsubscribed() {
            return get() < 0;
        }

        public void unsubscribe() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.cancel.unsubscribe();
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public e<? super T> a(final e<? super R> eVar) {
        f anonymousClass1 = new e<T>(this) {
            SingleEmitter<R> a;
            final /* synthetic */ OperatorMapNotification c;

            public void a(rx.c cVar) {
                this.a = new SingleEmitter(eVar, cVar, this);
                eVar.a(this.a);
            }

            public void onCompleted() {
                try {
                    this.a.offerAndComplete(this.c.c.call());
                } catch (Throwable th) {
                    eVar.onError(th);
                }
            }

            public void onError(Throwable th) {
                try {
                    this.a.offerAndComplete(this.c.b.call(th));
                } catch (Throwable th2) {
                    eVar.onError(th);
                }
            }

            public void onNext(T t) {
                try {
                    this.a.offer(this.c.a.call(t));
                } catch (Throwable th) {
                    eVar.onError(OnErrorThrowable.addValueAsLastCause(th, t));
                }
            }
        };
        eVar.a(anonymousClass1);
        return anonymousClass1;
    }
}
