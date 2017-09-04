package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.a.b;
import rx.c;
import rx.e;
import rx.f;

public final class OperatorOnBackpressureLatest<T> implements b<T, T> {

    static final class LatestEmitter<T> extends AtomicLong implements rx.b<T>, c, f {
        static final Object EMPTY = new Object();
        static final long NOT_REQUESTED = -4611686018427387904L;
        private static final long serialVersionUID = -1364393685005146274L;
        final e<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        a<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value = new AtomicReference(EMPTY);

        public LatestEmitter(e<? super T> eVar) {
            this.child = eVar;
            lazySet(NOT_REQUESTED);
        }

        public void request(long j) {
            if (j >= 0) {
                long j2;
                long j3;
                do {
                    j2 = get();
                    if (j2 != Long.MIN_VALUE) {
                        if (j2 == NOT_REQUESTED) {
                            j3 = j;
                        } else {
                            j3 = j2 + j;
                            if (j3 < 0) {
                                j3 = Long.MAX_VALUE;
                            }
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                if (j2 == NOT_REQUESTED) {
                    this.parent.b(Long.MAX_VALUE);
                }
                emit();
            }
        }

        long produced(long j) {
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 < 0) {
                    return j3;
                }
                j2 = j3 - j;
            } while (!compareAndSet(j3, j2));
            return j2;
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        public void onNext(T t) {
            this.value.lazySet(t);
            emit();
        }

        public void onError(Throwable th) {
            this.terminal = th;
            this.done = true;
            emit();
        }

        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void emit() {
            /*
            r8 = this;
            r1 = 1;
            r2 = 0;
            monitor-enter(r8);
            r0 = r8.emitting;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x000c;
        L_0x0007:
            r0 = 1;
            r8.missed = r0;	 Catch:{ all -> 0x0028 }
            monitor-exit(r8);	 Catch:{ all -> 0x0028 }
        L_0x000b:
            return;
        L_0x000c:
            r0 = 1;
            r8.emitting = r0;	 Catch:{ all -> 0x0028 }
            r0 = 0;
            r8.missed = r0;	 Catch:{ all -> 0x0028 }
            monitor-exit(r8);	 Catch:{ all -> 0x0028 }
        L_0x0013:
            r4 = r8.get();	 Catch:{ all -> 0x007c }
            r6 = -9223372036854775808;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 != 0) goto L_0x002b;
        L_0x001d:
            if (r1 != 0) goto L_0x000b;
        L_0x001f:
            monitor-enter(r8);
            r0 = 0;
            r8.emitting = r0;	 Catch:{ all -> 0x0025 }
            monitor-exit(r8);	 Catch:{ all -> 0x0025 }
            goto L_0x000b;
        L_0x0025:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0025 }
            throw r0;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0028 }
            throw r0;
        L_0x002b:
            r0 = r8.value;	 Catch:{ all -> 0x007c }
            r0 = r0.get();	 Catch:{ all -> 0x007c }
            r6 = 0;
            r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r3 <= 0) goto L_0x004e;
        L_0x0037:
            r3 = EMPTY;	 Catch:{ all -> 0x007c }
            if (r0 == r3) goto L_0x004e;
        L_0x003b:
            r3 = r8.child;	 Catch:{ all -> 0x007c }
            r3.onNext(r0);	 Catch:{ all -> 0x007c }
            r3 = r8.value;	 Catch:{ all -> 0x007c }
            r4 = EMPTY;	 Catch:{ all -> 0x007c }
            r3.compareAndSet(r0, r4);	 Catch:{ all -> 0x007c }
            r4 = 1;
            r8.produced(r4);	 Catch:{ all -> 0x007c }
            r0 = EMPTY;	 Catch:{ all -> 0x007c }
        L_0x004e:
            r3 = EMPTY;	 Catch:{ all -> 0x007c }
            if (r0 != r3) goto L_0x005f;
        L_0x0052:
            r0 = r8.done;	 Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x005f;
        L_0x0056:
            r0 = r8.terminal;	 Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x0076;
        L_0x005a:
            r3 = r8.child;	 Catch:{ all -> 0x007c }
            r3.onError(r0);	 Catch:{ all -> 0x007c }
        L_0x005f:
            monitor-enter(r8);	 Catch:{ all -> 0x007c }
            r0 = r8.missed;	 Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x007e;
        L_0x0064:
            r0 = 0;
            r8.emitting = r0;	 Catch:{ all -> 0x0083 }
            monitor-exit(r8);	 Catch:{ all -> 0x0069 }
            goto L_0x001d;
        L_0x0069:
            r0 = move-exception;
        L_0x006a:
            monitor-exit(r8);	 Catch:{ all -> 0x0069 }
            throw r0;	 Catch:{ all -> 0x006c }
        L_0x006c:
            r0 = move-exception;
            r2 = r1;
        L_0x006e:
            if (r2 != 0) goto L_0x0075;
        L_0x0070:
            monitor-enter(r8);
            r1 = 0;
            r8.emitting = r1;	 Catch:{ all -> 0x0086 }
            monitor-exit(r8);	 Catch:{ all -> 0x0086 }
        L_0x0075:
            throw r0;
        L_0x0076:
            r0 = r8.child;	 Catch:{ all -> 0x007c }
            r0.onCompleted();	 Catch:{ all -> 0x007c }
            goto L_0x005f;
        L_0x007c:
            r0 = move-exception;
            goto L_0x006e;
        L_0x007e:
            r0 = 0;
            r8.missed = r0;	 Catch:{ all -> 0x0083 }
            monitor-exit(r8);	 Catch:{ all -> 0x0083 }
            goto L_0x0013;
        L_0x0083:
            r0 = move-exception;
            r1 = r2;
            goto L_0x006a;
        L_0x0086:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0086 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.emit():void");
        }
    }

    static final class a<T> extends e<T> {
        private final LatestEmitter<T> a;

        private a(LatestEmitter<T> latestEmitter) {
            this.a = latestEmitter;
        }

        public void a() {
            a(0);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onCompleted() {
            this.a.onCompleted();
        }

        void b(long j) {
            a(j);
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((e) obj);
    }

    public e<? super T> a(e<? super T> eVar) {
        c latestEmitter = new LatestEmitter(eVar);
        f aVar = new a(latestEmitter);
        latestEmitter.parent = aVar;
        eVar.a(aVar);
        eVar.a((f) latestEmitter);
        eVar.a(latestEmitter);
        return aVar;
    }
}
