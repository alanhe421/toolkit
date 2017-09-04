package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.b.c;
import rx.d;
import rx.e;
import rx.f;

public final class OperatorReplay<T> extends rx.observables.a<T> {
    static final c b = new c() {
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    };

    interface a<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements a<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        final NotificationLite<T> nl = NotificationLite.a();
        int size;
        Node tail;

        public BoundedReplayBuffer() {
            Node node = new Node(null);
            this.tail = node;
            set(node);
        }

        final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        final void removeFirst() {
            Node node = (Node) ((Node) get()).get();
            if (node == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(node);
        }

        final void removeSome(int i) {
            Node node = (Node) get();
            while (i > 0) {
                node = (Node) node.get();
                i--;
                this.size--;
            }
            setFirst(node);
        }

        final void setFirst(Node node) {
            set(node);
        }

        public final void next(T t) {
            addLast(new Node(enterTransform(this.nl.a((Object) t))));
            truncate();
        }

        public final void error(Throwable th) {
            addLast(new Node(enterTransform(this.nl.a(th))));
            truncateFinal();
        }

        public final void complete() {
            addLast(new Node(enterTransform(this.nl.b())));
            truncateFinal();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r12) {
            /*
            r11 = this;
            r10 = 0;
            r4 = 0;
            monitor-enter(r12);
            r0 = r12.emitting;	 Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x000d;
        L_0x0008:
            r0 = 1;
            r12.missed = r0;	 Catch:{ all -> 0x0076 }
            monitor-exit(r12);	 Catch:{ all -> 0x0076 }
        L_0x000c:
            return;
        L_0x000d:
            r0 = 1;
            r12.emitting = r0;	 Catch:{ all -> 0x0076 }
            monitor-exit(r12);	 Catch:{ all -> 0x0076 }
        L_0x0011:
            r0 = r12.isUnsubscribed();
            if (r0 != 0) goto L_0x000c;
        L_0x0017:
            r6 = r12.get();
            r0 = r12.index();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            if (r0 != 0) goto L_0x002b;
        L_0x0023:
            r0 = r11.get();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            r12.index = r0;
        L_0x002b:
            r1 = r0;
            r2 = r4;
        L_0x002d:
            r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
            if (r0 == 0) goto L_0x0084;
        L_0x0031:
            r0 = r1.get();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            if (r0 == 0) goto L_0x0084;
        L_0x0039:
            r1 = r0.value;
            r1 = r11.leaveTransform(r1);
            r8 = r11.nl;	 Catch:{ Throwable -> 0x004d }
            r9 = r12.child;	 Catch:{ Throwable -> 0x004d }
            r8 = r8.a(r9, r1);	 Catch:{ Throwable -> 0x004d }
            if (r8 == 0) goto L_0x0079;
        L_0x0049:
            r0 = 0;
            r12.index = r0;	 Catch:{ Throwable -> 0x004d }
            goto L_0x000c;
        L_0x004d:
            r0 = move-exception;
            r12.index = r10;
            rx.exceptions.a.a(r0);
            r12.unsubscribe();
            r2 = r11.nl;
            r2 = r2.c(r1);
            if (r2 != 0) goto L_0x000c;
        L_0x005e:
            r2 = r11.nl;
            r2 = r2.b(r1);
            if (r2 != 0) goto L_0x000c;
        L_0x0066:
            r2 = r12.child;
            r3 = r11.nl;
            r1 = r3.d(r1);
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r1);
            r2.onError(r0);
            goto L_0x000c;
        L_0x0076:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0076 }
            throw r0;
        L_0x0079:
            r8 = 1;
            r2 = r2 + r8;
            r1 = r12.isUnsubscribed();
            if (r1 != 0) goto L_0x000c;
        L_0x0082:
            r1 = r0;
            goto L_0x002d;
        L_0x0084:
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 == 0) goto L_0x0096;
        L_0x0088:
            r12.index = r1;
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1));
            if (r0 == 0) goto L_0x0096;
        L_0x0093:
            r12.produced(r2);
        L_0x0096:
            monitor-enter(r12);
            r0 = r12.missed;	 Catch:{ all -> 0x00a1 }
            if (r0 != 0) goto L_0x00a4;
        L_0x009b:
            r0 = 0;
            r12.emitting = r0;	 Catch:{ all -> 0x00a1 }
            monitor-exit(r12);	 Catch:{ all -> 0x00a1 }
            goto L_0x000c;
        L_0x00a1:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x00a1 }
            throw r0;
        L_0x00a4:
            r0 = 0;
            r12.missed = r0;	 Catch:{ all -> 0x00a1 }
            monitor-exit(r12);	 Catch:{ all -> 0x00a1 }
            goto L_0x0011;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.BoundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        Object enterTransform(Object obj) {
            return obj;
        }

        Object leaveTransform(Object obj) {
            return obj;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        final void collect(Collection<? super T> collection) {
            Node node = (Node) get();
            while (true) {
                node = (Node) node.get();
                if (node != null) {
                    Object leaveTransform = leaveTransform(node.value);
                    if (!this.nl.b(leaveTransform) && !this.nl.c(leaveTransform)) {
                        collection.add(this.nl.d(leaveTransform));
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        boolean hasError() {
            return this.tail.value != null && this.nl.c(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && this.nl.b(leaveTransform(this.tail.value));
        }
    }

    static final class InnerProducer<T> extends AtomicLong implements rx.c, f {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final e<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final b<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(b<T> bVar, e<? super T> eVar) {
            this.parent = bVar;
            this.child = eVar;
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
                    if (j2 < 0 || j != 0) {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = Long.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                addTotalRequested(j);
                this.parent.b();
                this.parent.a.replay(this);
            }
        }

        void addTotalRequested(long j) {
            long j2;
            long j3;
            do {
                j2 = this.totalRequested.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(j2, j3));
        }

        public long produced(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                j2 = j3 - j;
                if (j2 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j3 + ")");
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

        <U> U index() {
            return this.index;
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final Object value;

        public Node(Object obj) {
            this.value = obj;
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final d scheduler;

        public SizeAndTimeBoundReplayBuffer(int i, long j, d dVar) {
            this.scheduler = dVar;
            this.limit = i;
            this.maxAgeInMillis = j;
        }

        Object enterTransform(Object obj) {
            return new rx.e.f(this.scheduler.b(), obj);
        }

        Object leaveTransform(Object obj) {
            return ((rx.e.f) obj).b();
        }

        void truncate() {
            long b = this.scheduler.b() - this.maxAgeInMillis;
            Node node = (Node) get();
            Node node2 = node;
            int i = 0;
            Node node3 = (Node) node.get();
            while (node3 != null) {
                if (this.size <= this.limit) {
                    if (((rx.e.f) node3.value).a() > b) {
                        break;
                    }
                    i++;
                    this.size--;
                    node2 = node3;
                    node3 = (Node) node3.get();
                } else {
                    i++;
                    this.size--;
                    node2 = node3;
                    node3 = (Node) node3.get();
                }
            }
            if (i != 0) {
                setFirst(node2);
            }
        }

        void truncateFinal() {
            long b = this.scheduler.b() - this.maxAgeInMillis;
            Node node = (Node) get();
            Node node2 = node;
            int i = 0;
            Node node3 = (Node) node.get();
            while (node3 != null && this.size > 1 && ((rx.e.f) node3.value).a() <= b) {
                i++;
                this.size--;
                node2 = node3;
                node3 = (Node) node3.get();
            }
            if (i != 0) {
                setFirst(node2);
            }
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        public SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements a<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        final NotificationLite<T> nl = NotificationLite.a();
        volatile int size;

        public UnboundedReplayBuffer(int i) {
            super(i);
        }

        public void next(T t) {
            add(this.nl.a((Object) t));
            this.size++;
        }

        public void error(Throwable th) {
            add(this.nl.a(th));
            this.size++;
        }

        public void complete() {
            add(this.nl.b());
            this.size++;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r12) {
            /*
            r11 = this;
            monitor-enter(r12);
            r0 = r12.emitting;	 Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r12.missed = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r12.emitting = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
        L_0x000e:
            r0 = r12.isUnsubscribed();
            if (r0 != 0) goto L_0x0009;
        L_0x0014:
            r7 = r11.size;
            r0 = r12.index();
            r0 = (java.lang.Integer) r0;
            if (r0 == 0) goto L_0x0053;
        L_0x001e:
            r0 = r0.intValue();
        L_0x0022:
            r4 = r12.get();
            r2 = 0;
            r6 = r0;
            r0 = r2;
            r2 = r4;
        L_0x002b:
            r8 = 0;
            r8 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r8 == 0) goto L_0x007c;
        L_0x0031:
            if (r6 >= r7) goto L_0x007c;
        L_0x0033:
            r8 = r11.get(r6);
            r9 = r11.nl;	 Catch:{ Throwable -> 0x0055 }
            r10 = r12.child;	 Catch:{ Throwable -> 0x0055 }
            r8 = r9.a(r10, r8);	 Catch:{ Throwable -> 0x0055 }
            if (r8 != 0) goto L_0x0009;
        L_0x0041:
            r8 = r12.isUnsubscribed();
            if (r8 != 0) goto L_0x0009;
        L_0x0047:
            r6 = r6 + 1;
            r8 = 1;
            r2 = r2 - r8;
            r8 = 1;
            r0 = r0 + r8;
            goto L_0x002b;
        L_0x0050:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
            throw r0;
        L_0x0053:
            r0 = 0;
            goto L_0x0022;
        L_0x0055:
            r0 = move-exception;
            rx.exceptions.a.a(r0);
            r12.unsubscribe();
            r1 = r11.nl;
            r1 = r1.c(r8);
            if (r1 != 0) goto L_0x0009;
        L_0x0064:
            r1 = r11.nl;
            r1 = r1.b(r8);
            if (r1 != 0) goto L_0x0009;
        L_0x006c:
            r1 = r12.child;
            r2 = r11.nl;
            r2 = r2.d(r8);
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r2);
            r1.onError(r0);
            goto L_0x0009;
        L_0x007c:
            r2 = 0;
            r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0094;
        L_0x0082:
            r2 = java.lang.Integer.valueOf(r6);
            r12.index = r2;
            r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0094;
        L_0x0091:
            r12.produced(r0);
        L_0x0094:
            monitor-enter(r12);
            r0 = r12.missed;	 Catch:{ all -> 0x009f }
            if (r0 != 0) goto L_0x00a2;
        L_0x0099:
            r0 = 0;
            r12.emitting = r0;	 Catch:{ all -> 0x009f }
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            goto L_0x0009;
        L_0x009f:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            throw r0;
        L_0x00a2:
            r0 = 0;
            r12.missed = r0;	 Catch:{ all -> 0x009f }
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.UnboundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }
    }

    static final class b<T> extends e<T> implements f {
        static final InnerProducer[] c = new InnerProducer[0];
        static final InnerProducer[] d = new InnerProducer[0];
        final a<T> a;
        boolean b;
        final AtomicReference<InnerProducer[]> e;
        boolean f;
        boolean g;
        long h;
        long i;
        volatile rx.c j;

        void a(InnerProducer<T> innerProducer) {
            InnerProducer[] innerProducerArr;
            Object obj;
            do {
                innerProducerArr = (InnerProducer[]) this.e.get();
                if (innerProducerArr != c && innerProducerArr != d) {
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
                        obj = c;
                    } else {
                        obj = new InnerProducer[(length - 1)];
                        System.arraycopy(innerProducerArr, 0, obj, 0, i);
                        System.arraycopy(innerProducerArr, i + 1, obj, i, (length - i) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.e.compareAndSet(innerProducerArr, obj));
        }

        public void a(rx.c cVar) {
            if (this.j != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.j = cVar;
            b();
            c();
        }

        public void onNext(T t) {
            if (!this.b) {
                this.a.next(t);
                c();
            }
        }

        public void onError(Throwable th) {
            if (!this.b) {
                this.b = true;
                try {
                    this.a.error(th);
                    c();
                } finally {
                    unsubscribe();
                }
            }
        }

        public void onCompleted() {
            if (!this.b) {
                this.b = true;
                try {
                    this.a.complete();
                    c();
                } finally {
                    unsubscribe();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void b() {
            /*
            r12 = this;
            r2 = 0;
            r6 = 0;
            r0 = r12.isUnsubscribed();
            if (r0 == 0) goto L_0x000a;
        L_0x0009:
            return;
        L_0x000a:
            monitor-enter(r12);
            r0 = r12.f;	 Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x0017;
        L_0x000f:
            r0 = 1;
            r12.g = r0;	 Catch:{ all -> 0x0014 }
            monitor-exit(r12);	 Catch:{ all -> 0x0014 }
            goto L_0x0009;
        L_0x0014:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0014 }
            throw r0;
        L_0x0017:
            r0 = 1;
            r12.f = r0;	 Catch:{ all -> 0x0014 }
            monitor-exit(r12);	 Catch:{ all -> 0x0014 }
        L_0x001b:
            r0 = r12.isUnsubscribed();
            if (r0 != 0) goto L_0x0009;
        L_0x0021:
            r0 = r12.e;
            r0 = r0.get();
            r0 = (rx.internal.operators.OperatorReplay.InnerProducer[]) r0;
            r8 = r12.h;
            r3 = r0.length;
            r1 = r2;
            r4 = r6;
        L_0x002e:
            if (r1 >= r3) goto L_0x003f;
        L_0x0030:
            r10 = r0[r1];
            r10 = r10.totalRequested;
            r10 = r10.get();
            r4 = java.lang.Math.max(r4, r10);
            r1 = r1 + 1;
            goto L_0x002e;
        L_0x003f:
            r0 = r12.i;
            r3 = r12.j;
            r8 = r4 - r8;
            r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
            if (r10 == 0) goto L_0x0075;
        L_0x0049:
            r12.h = r4;
            if (r3 == 0) goto L_0x0068;
        L_0x004d:
            r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r4 == 0) goto L_0x0064;
        L_0x0051:
            r12.i = r6;
            r0 = r0 + r8;
            r3.request(r0);
        L_0x0057:
            monitor-enter(r12);
            r0 = r12.g;	 Catch:{ all -> 0x0061 }
            if (r0 != 0) goto L_0x0081;
        L_0x005c:
            r0 = 0;
            r12.f = r0;	 Catch:{ all -> 0x0061 }
            monitor-exit(r12);	 Catch:{ all -> 0x0061 }
            goto L_0x0009;
        L_0x0061:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0061 }
            throw r0;
        L_0x0064:
            r3.request(r8);
            goto L_0x0057;
        L_0x0068:
            r0 = r0 + r8;
            r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r3 >= 0) goto L_0x0072;
        L_0x006d:
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        L_0x0072:
            r12.i = r0;
            goto L_0x0057;
        L_0x0075:
            r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r4 == 0) goto L_0x0057;
        L_0x0079:
            if (r3 == 0) goto L_0x0057;
        L_0x007b:
            r12.i = r6;
            r3.request(r0);
            goto L_0x0057;
        L_0x0081:
            r0 = 0;
            r12.g = r0;	 Catch:{ all -> 0x0061 }
            monitor-exit(r12);	 Catch:{ all -> 0x0061 }
            goto L_0x001b;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.b.b():void");
        }

        void c() {
            for (InnerProducer replay : (InnerProducer[]) this.e.get()) {
                this.a.replay(replay);
            }
        }
    }
}
