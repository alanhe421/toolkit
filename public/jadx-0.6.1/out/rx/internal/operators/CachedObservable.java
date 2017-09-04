package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.b;
import rx.c;
import rx.e;
import rx.f;
import rx.f.d;

public final class CachedObservable<T> extends rx.a<T> {

    static final class CachedSubscribe<T> extends AtomicBoolean implements rx.a.a<T> {
        private static final long serialVersionUID = -2817751667698696782L;
        final a<T> state;

        public CachedSubscribe(a<T> aVar) {
            this.state = aVar;
        }

        public void call(e<? super T> eVar) {
            c replayProducer = new ReplayProducer(eVar, this.state);
            this.state.a(replayProducer);
            eVar.a((f) replayProducer);
            eVar.a(replayProducer);
            if (!get() && compareAndSet(false, true)) {
                this.state.a();
            }
        }
    }

    static final class ReplayProducer<T> extends AtomicLong implements c, f {
        private static final long serialVersionUID = -2557562030197141021L;
        final e<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        boolean emitting;
        int index;
        boolean missed;
        final a<T> state;

        public ReplayProducer(e<? super T> eVar, a<T> aVar) {
            this.child = eVar;
            this.state = aVar;
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
            replay();
        }

        public long produced(long j) {
            return addAndGet(-j);
        }

        public boolean isUnsubscribed() {
            return get() < 0;
        }

        public void unsubscribe() {
            if (get() >= 0 && getAndSet(-1) >= 0) {
                this.state.b(this);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay() {
            /*
            r15 = this;
            monitor-enter(r15);
            r0 = r15.emitting;	 Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r15.missed = r0;	 Catch:{ all -> 0x0059 }
            monitor-exit(r15);	 Catch:{ all -> 0x0059 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r15.emitting = r0;	 Catch:{ all -> 0x0059 }
            monitor-exit(r15);	 Catch:{ all -> 0x0059 }
            r1 = 0;
            r0 = r15.state;	 Catch:{ all -> 0x0050 }
            r8 = r0.e;	 Catch:{ all -> 0x0050 }
            r9 = r15.child;	 Catch:{ all -> 0x0050 }
        L_0x0015:
            r6 = r15.get();	 Catch:{ all -> 0x0050 }
            r2 = 0;
            r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0009;
        L_0x001f:
            r0 = r15.state;	 Catch:{ all -> 0x0050 }
            r10 = r0.d();	 Catch:{ all -> 0x0050 }
            if (r10 == 0) goto L_0x00de;
        L_0x0027:
            r4 = r15.currentBuffer;	 Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0033;
        L_0x002b:
            r0 = r15.state;	 Catch:{ all -> 0x0050 }
            r4 = r0.c();	 Catch:{ all -> 0x0050 }
            r15.currentBuffer = r4;	 Catch:{ all -> 0x0050 }
        L_0x0033:
            r0 = r4.length;	 Catch:{ all -> 0x0050 }
            r11 = r0 + -1;
            r3 = r15.index;	 Catch:{ all -> 0x0050 }
            r2 = r15.currentIndexInBuffer;	 Catch:{ all -> 0x0050 }
            r12 = 0;
            r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r0 != 0) goto L_0x006e;
        L_0x0040:
            r0 = r4[r2];	 Catch:{ all -> 0x0050 }
            r2 = r8.b(r0);	 Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x005c;
        L_0x0048:
            r9.onCompleted();	 Catch:{ all -> 0x0050 }
            r1 = 1;
            r15.unsubscribe();	 Catch:{ all -> 0x0050 }
            goto L_0x0009;
        L_0x0050:
            r0 = move-exception;
            if (r1 != 0) goto L_0x0058;
        L_0x0053:
            monitor-enter(r15);
            r1 = 0;
            r15.emitting = r1;	 Catch:{ all -> 0x00f3 }
            monitor-exit(r15);	 Catch:{ all -> 0x00f3 }
        L_0x0058:
            throw r0;
        L_0x0059:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x0059 }
            throw r0;
        L_0x005c:
            r2 = r8.c(r0);	 Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x00de;
        L_0x0062:
            r0 = r8.e(r0);	 Catch:{ all -> 0x0050 }
            r9.onError(r0);	 Catch:{ all -> 0x0050 }
            r1 = 1;
            r15.unsubscribe();	 Catch:{ all -> 0x0050 }
            goto L_0x0009;
        L_0x006e:
            r12 = 0;
            r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r0 <= 0) goto L_0x00de;
        L_0x0074:
            r0 = 0;
            r5 = r3;
            r3 = r0;
            r0 = r2;
            r2 = r4;
        L_0x0079:
            if (r5 >= r10) goto L_0x00ce;
        L_0x007b:
            r12 = 0;
            r4 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
            if (r4 <= 0) goto L_0x00ce;
        L_0x0081:
            r4 = r9.isUnsubscribed();	 Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0009;
        L_0x0087:
            if (r0 != r11) goto L_0x0093;
        L_0x0089:
            r0 = r2[r11];	 Catch:{ all -> 0x0050 }
            r0 = (java.lang.Object[]) r0;	 Catch:{ all -> 0x0050 }
            r0 = (java.lang.Object[]) r0;	 Catch:{ all -> 0x0050 }
            r2 = 0;
            r14 = r2;
            r2 = r0;
            r0 = r14;
        L_0x0093:
            r4 = r2[r0];	 Catch:{ all -> 0x0050 }
            r12 = r8.a(r9, r4);	 Catch:{ Throwable -> 0x00a1 }
            if (r12 == 0) goto L_0x00c2;
        L_0x009b:
            r1 = 1;
            r15.unsubscribe();	 Catch:{ Throwable -> 0x00a1 }
            goto L_0x0009;
        L_0x00a1:
            r0 = move-exception;
            rx.exceptions.a.a(r0);	 Catch:{ all -> 0x0050 }
            r1 = 1;
            r15.unsubscribe();	 Catch:{ all -> 0x0050 }
            r2 = r8.c(r4);	 Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0009;
        L_0x00af:
            r2 = r8.b(r4);	 Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0009;
        L_0x00b5:
            r2 = r8.d(r4);	 Catch:{ all -> 0x0050 }
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r2);	 Catch:{ all -> 0x0050 }
            r9.onError(r0);	 Catch:{ all -> 0x0050 }
            goto L_0x0009;
        L_0x00c2:
            r4 = r0 + 1;
            r5 = r5 + 1;
            r12 = 1;
            r6 = r6 - r12;
            r0 = r3 + 1;
            r3 = r0;
            r0 = r4;
            goto L_0x0079;
        L_0x00ce:
            r4 = r9.isUnsubscribed();	 Catch:{ all -> 0x0050 }
            if (r4 != 0) goto L_0x0009;
        L_0x00d4:
            r15.index = r5;	 Catch:{ all -> 0x0050 }
            r15.currentIndexInBuffer = r0;	 Catch:{ all -> 0x0050 }
            r15.currentBuffer = r2;	 Catch:{ all -> 0x0050 }
            r2 = (long) r3;	 Catch:{ all -> 0x0050 }
            r15.produced(r2);	 Catch:{ all -> 0x0050 }
        L_0x00de:
            monitor-enter(r15);	 Catch:{ all -> 0x0050 }
            r0 = r15.missed;	 Catch:{ all -> 0x00ea }
            if (r0 != 0) goto L_0x00ed;
        L_0x00e3:
            r0 = 0;
            r15.emitting = r0;	 Catch:{ all -> 0x00ea }
            r1 = 1;
            monitor-exit(r15);	 Catch:{ all -> 0x00ea }
            goto L_0x0009;
        L_0x00ea:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x00ea }
            throw r0;	 Catch:{ all -> 0x0050 }
        L_0x00ed:
            r0 = 0;
            r15.missed = r0;	 Catch:{ all -> 0x00ea }
            monitor-exit(r15);	 Catch:{ all -> 0x00ea }
            goto L_0x0015;
        L_0x00f3:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x00f3 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.CachedObservable.ReplayProducer.replay():void");
        }
    }

    static final class a<T> extends rx.internal.util.a implements b<T> {
        static final ReplayProducer<?>[] d = new ReplayProducer[0];
        final rx.a<? extends T> a;
        final d b;
        volatile ReplayProducer<?>[] c;
        final NotificationLite<T> e;
        volatile boolean f;
        boolean g;

        public void a(ReplayProducer<T> replayProducer) {
            synchronized (this.b) {
                Object obj = this.c;
                int length = obj.length;
                Object obj2 = new ReplayProducer[(length + 1)];
                System.arraycopy(obj, 0, obj2, 0, length);
                obj2[length] = replayProducer;
                this.c = obj2;
            }
        }

        public void b(ReplayProducer<T> replayProducer) {
            int i = 0;
            synchronized (this.b) {
                Object obj = this.c;
                int length = obj.length;
                while (i < length) {
                    if (obj[i].equals(replayProducer)) {
                        break;
                    }
                    i++;
                }
                i = -1;
                if (i < 0) {
                } else if (length == 1) {
                    this.c = d;
                } else {
                    Object obj2 = new ReplayProducer[(length - 1)];
                    System.arraycopy(obj, 0, obj2, 0, i);
                    System.arraycopy(obj, i + 1, obj2, i, (length - i) - 1);
                    this.c = obj2;
                }
            }
        }

        public void a() {
            e anonymousClass1 = new e<T>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
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
            };
            this.b.a(anonymousClass1);
            this.a.a(anonymousClass1);
            this.f = true;
        }

        public void onNext(T t) {
            if (!this.g) {
                a(this.e.a((Object) t));
                b();
            }
        }

        public void onError(Throwable th) {
            if (!this.g) {
                this.g = true;
                a(this.e.a(th));
                this.b.unsubscribe();
                b();
            }
        }

        public void onCompleted() {
            if (!this.g) {
                this.g = true;
                a(this.e.b());
                this.b.unsubscribe();
                b();
            }
        }

        void b() {
            for (ReplayProducer replay : this.c) {
                replay.replay();
            }
        }
    }
}
