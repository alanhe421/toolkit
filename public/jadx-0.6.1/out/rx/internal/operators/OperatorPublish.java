package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.c;
import rx.e;
import rx.exceptions.MissingBackpressureException;
import rx.f;
import rx.internal.util.d;

public final class OperatorPublish<T> extends rx.observables.a<T> {

    static final class InnerProducer<T> extends AtomicLong implements c, f {
        static final long NOT_REQUESTED = -4611686018427387904L;
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final e<? super T> child;
        final a<T> parent;

        public InnerProducer(a<T> aVar, e<? super T> eVar) {
            this.parent = aVar;
            this.child = eVar;
            lazySet(NOT_REQUESTED);
        }

        public void request(long j) {
            if (j >= 0) {
                long j2;
                long j3;
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    if (j2 >= 0 && j == 0) {
                        return;
                    }
                    if (j2 == NOT_REQUESTED) {
                        j3 = j;
                    } else {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = Long.MAX_VALUE;
                        }
                    }
                } while (!compareAndSet(j2, j3));
                this.parent.b();
            }
        }

        public long produced(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 == NOT_REQUESTED) {
                    throw new IllegalStateException("Produced without request");
                } else if (j3 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                } else {
                    j2 = j3 - j;
                    if (j2 < 0) {
                        throw new IllegalStateException("More produced (" + j + ") than requested (" + j3 + ")");
                    }
                }
            } while (!compareAndSet(j3, j2));
            return j2;
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.a(this);
                this.parent.b();
            }
        }
    }

    static final class a<T> extends e<T> implements f {
        static final InnerProducer[] e = new InnerProducer[0];
        static final InnerProducer[] f = new InnerProducer[0];
        final Queue<Object> a;
        final NotificationLite<T> b;
        final AtomicReference<a<T>> c;
        volatile Object d;
        final AtomicReference<InnerProducer[]> g;
        boolean h;
        boolean i;

        public void a() {
            a((long) d.c);
        }

        public void onNext(T t) {
            if (this.a.offer(this.b.a((Object) t))) {
                b();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.d == null) {
                this.d = this.b.a(th);
                b();
            }
        }

        public void onCompleted() {
            if (this.d == null) {
                this.d = this.b.b();
                b();
            }
        }

        void a(InnerProducer<T> innerProducer) {
            InnerProducer[] innerProducerArr;
            Object obj;
            do {
                innerProducerArr = (InnerProducer[]) this.g.get();
                if (innerProducerArr != e && innerProducerArr != f) {
                    int i = -1;
                    int length = innerProducerArr.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (innerProducerArr[i2].equals(innerProducer)) {
                            i = i2;
                            break;
                        }
                    }
                    if (i < 0) {
                        return;
                    }
                    if (length == 1) {
                        obj = e;
                    } else {
                        obj = new InnerProducer[(length - 1)];
                        System.arraycopy(innerProducerArr, 0, obj, 0, i);
                        System.arraycopy(innerProducerArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.g.compareAndSet(innerProducerArr, obj));
        }

        boolean a(Object obj, boolean z) {
            int i = 0;
            if (obj != null) {
                InnerProducer[] innerProducerArr;
                if (!this.b.b(obj)) {
                    Throwable e = this.b.e(obj);
                    this.c.compareAndSet(this, null);
                    try {
                        innerProducerArr = (InnerProducer[]) this.g.getAndSet(f);
                        int length = innerProducerArr.length;
                        while (i < length) {
                            innerProducerArr[i].child.onError(e);
                            i++;
                        }
                        return true;
                    } finally {
                        unsubscribe();
                    }
                } else if (z) {
                    this.c.compareAndSet(this, null);
                    try {
                        innerProducerArr = (InnerProducer[]) this.g.getAndSet(f);
                        int length2 = innerProducerArr.length;
                        while (i < length2) {
                            innerProducerArr[i].child.onCompleted();
                            i++;
                        }
                        return true;
                    } finally {
                        unsubscribe();
                    }
                }
            }
            return false;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void b() {
            /*
            r19 = this;
            monitor-enter(r19);
            r0 = r19;
            r2 = r0.h;	 Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x000e;
        L_0x0007:
            r2 = 1;
            r0 = r19;
            r0.i = r2;	 Catch:{ all -> 0x005e }
            monitor-exit(r19);	 Catch:{ all -> 0x005e }
        L_0x000d:
            return;
        L_0x000e:
            r2 = 1;
            r0 = r19;
            r0.h = r2;	 Catch:{ all -> 0x005e }
            r2 = 0;
            r0 = r19;
            r0.i = r2;	 Catch:{ all -> 0x005e }
            monitor-exit(r19);	 Catch:{ all -> 0x005e }
            r6 = 0;
        L_0x001a:
            r0 = r19;
            r2 = r0.d;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r3 = r0.a;	 Catch:{ all -> 0x008b }
            r7 = r3.isEmpty();	 Catch:{ all -> 0x008b }
            r0 = r19;
            r2 = r0.a(r2, r7);	 Catch:{ all -> 0x008b }
            if (r2 != 0) goto L_0x000d;
        L_0x002e:
            if (r7 != 0) goto L_0x00ca;
        L_0x0030:
            r0 = r19;
            r2 = r0.g;	 Catch:{ all -> 0x008b }
            r2 = r2.get();	 Catch:{ all -> 0x008b }
            r2 = (rx.internal.operators.OperatorPublish.InnerProducer[]) r2;	 Catch:{ all -> 0x008b }
            r10 = r2.length;	 Catch:{ all -> 0x008b }
            r8 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r4 = 0;
            r11 = r2.length;	 Catch:{ all -> 0x008b }
            r3 = 0;
            r18 = r3;
            r3 = r4;
            r4 = r8;
            r8 = r18;
        L_0x0049:
            if (r8 >= r11) goto L_0x006a;
        L_0x004b:
            r9 = r2[r8];	 Catch:{ all -> 0x008b }
            r12 = r9.get();	 Catch:{ all -> 0x008b }
            r14 = 0;
            r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r9 < 0) goto L_0x0061;
        L_0x0057:
            r4 = java.lang.Math.min(r4, r12);	 Catch:{ all -> 0x008b }
        L_0x005b:
            r8 = r8 + 1;
            goto L_0x0049;
        L_0x005e:
            r2 = move-exception;
            monitor-exit(r19);	 Catch:{ all -> 0x005e }
            throw r2;
        L_0x0061:
            r14 = -9223372036854775808;
            r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
            if (r9 != 0) goto L_0x005b;
        L_0x0067:
            r3 = r3 + 1;
            goto L_0x005b;
        L_0x006a:
            if (r10 != r3) goto L_0x0098;
        L_0x006c:
            r0 = r19;
            r3 = r0.d;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r2 = r0.a;	 Catch:{ all -> 0x008b }
            r2 = r2.poll();	 Catch:{ all -> 0x008b }
            if (r2 != 0) goto L_0x0096;
        L_0x007a:
            r2 = 1;
        L_0x007b:
            r0 = r19;
            r2 = r0.a(r3, r2);	 Catch:{ all -> 0x008b }
            if (r2 != 0) goto L_0x000d;
        L_0x0083:
            r2 = 1;
            r0 = r19;
            r0.a(r2);	 Catch:{ all -> 0x008b }
            goto L_0x001a;
        L_0x008b:
            r2 = move-exception;
        L_0x008c:
            if (r6 != 0) goto L_0x0095;
        L_0x008e:
            monitor-enter(r19);
            r3 = 0;
            r0 = r19;
            r0.h = r3;	 Catch:{ all -> 0x0123 }
            monitor-exit(r19);	 Catch:{ all -> 0x0123 }
        L_0x0095:
            throw r2;
        L_0x0096:
            r2 = 0;
            goto L_0x007b;
        L_0x0098:
            r3 = 0;
            r8 = r3;
            r3 = r7;
        L_0x009b:
            r10 = (long) r8;
            r7 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
            if (r7 >= 0) goto L_0x0126;
        L_0x00a0:
            r0 = r19;
            r3 = r0.d;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r7 = r0.a;	 Catch:{ all -> 0x008b }
            r9 = r7.poll();	 Catch:{ all -> 0x008b }
            if (r9 != 0) goto L_0x00e0;
        L_0x00ae:
            r7 = 1;
        L_0x00af:
            r0 = r19;
            r3 = r0.a(r3, r7);	 Catch:{ all -> 0x008b }
            if (r3 != 0) goto L_0x000d;
        L_0x00b7:
            if (r7 == 0) goto L_0x00e2;
        L_0x00b9:
            r2 = r7;
        L_0x00ba:
            if (r8 <= 0) goto L_0x00c2;
        L_0x00bc:
            r8 = (long) r8;	 Catch:{ all -> 0x008b }
            r0 = r19;
            r0.a(r8);	 Catch:{ all -> 0x008b }
        L_0x00c2:
            r8 = 0;
            r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
            if (r3 == 0) goto L_0x00ca;
        L_0x00c8:
            if (r2 == 0) goto L_0x001a;
        L_0x00ca:
            monitor-enter(r19);	 Catch:{ all -> 0x008b }
            r0 = r19;
            r2 = r0.i;	 Catch:{ all -> 0x0120 }
            if (r2 != 0) goto L_0x0118;
        L_0x00d1:
            r2 = 0;
            r0 = r19;
            r0.h = r2;	 Catch:{ all -> 0x0120 }
            r3 = 1;
            monitor-exit(r19);	 Catch:{ all -> 0x00da }
            goto L_0x000d;
        L_0x00da:
            r2 = move-exception;
        L_0x00db:
            monitor-exit(r19);	 Catch:{ all -> 0x00da }
            throw r2;	 Catch:{ all -> 0x00dd }
        L_0x00dd:
            r2 = move-exception;
            r6 = r3;
            goto L_0x008c;
        L_0x00e0:
            r7 = 0;
            goto L_0x00af;
        L_0x00e2:
            r0 = r19;
            r3 = r0.b;	 Catch:{ all -> 0x008b }
            r10 = r3.d(r9);	 Catch:{ all -> 0x008b }
            r11 = r2.length;	 Catch:{ all -> 0x008b }
            r3 = 0;
            r9 = r3;
        L_0x00ed:
            if (r9 >= r11) goto L_0x0113;
        L_0x00ef:
            r12 = r2[r9];	 Catch:{ all -> 0x008b }
            r14 = r12.get();	 Catch:{ all -> 0x008b }
            r16 = 0;
            r3 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
            if (r3 <= 0) goto L_0x0105;
        L_0x00fb:
            r3 = r12.child;	 Catch:{ Throwable -> 0x0109 }
            r3.onNext(r10);	 Catch:{ Throwable -> 0x0109 }
            r14 = 1;
            r12.produced(r14);	 Catch:{ all -> 0x008b }
        L_0x0105:
            r3 = r9 + 1;
            r9 = r3;
            goto L_0x00ed;
        L_0x0109:
            r3 = move-exception;
            r12.unsubscribe();	 Catch:{ all -> 0x008b }
            r12 = r12.child;	 Catch:{ all -> 0x008b }
            r12.onError(r3);	 Catch:{ all -> 0x008b }
            goto L_0x0105;
        L_0x0113:
            r3 = r8 + 1;
            r8 = r3;
            r3 = r7;
            goto L_0x009b;
        L_0x0118:
            r2 = 0;
            r0 = r19;
            r0.i = r2;	 Catch:{ all -> 0x0120 }
            monitor-exit(r19);	 Catch:{ all -> 0x0120 }
            goto L_0x001a;
        L_0x0120:
            r2 = move-exception;
            r3 = r6;
            goto L_0x00db;
        L_0x0123:
            r2 = move-exception;
            monitor-exit(r19);	 Catch:{ all -> 0x0123 }
            throw r2;
        L_0x0126:
            r2 = r3;
            goto L_0x00ba;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorPublish.a.b():void");
        }
    }
}
